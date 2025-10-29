package client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.regex.*;

import javax.swing.*;
import javax.swing.border.*;

import model.Usuario;
import threads.ClientThread;

public class ChatView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Usuario user, user2;
	private JLabel lblUsername, lblSetIP, lblSetPuerto;
	private JTextField textFieldMessage, textFieldNewUser, textFieldSetPuerto, textFieldSetIP;
	private JComboBox<String> comboBoxUsers;
	private JButton btnSendMessage, btnDisconnect, btnCreateUser;
	private JLabel lblMessage;
	private Socket client;
	private ClientThread thread;
	private JScrollPane scrollPaneMessages;
	private JList<String> messageList;

	public ChatView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(495, 3, 111, 27);
		contentPane.add(btnDisconnect);

		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(27, 10, 111, 15);
		contentPane.add(lblUsername);

		textFieldNewUser = new JTextField();
		textFieldNewUser.setColumns(10);
		textFieldNewUser.setBounds(27, 35, 458, 35);
		contentPane.add(textFieldNewUser);

		btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(495, 34, 111, 36);
		contentPane.add(btnCreateUser);

		comboBoxUsers = new JComboBox<>();
		comboBoxUsers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxUsers.setModel(new DefaultComboBoxModel(new String[] {"Public"}));
		comboBoxUsers.setEnabled(false);
		comboBoxUsers.setBounds(27, 80, 256, 27);
		contentPane.add(comboBoxUsers);

		lblSetIP = new JLabel("IP:");
		lblSetIP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSetIP.setBounds(293, 85, 15, 15);
		contentPane.add(lblSetIP);

		textFieldSetIP = new JTextField();
		textFieldSetIP.setColumns(10);
		textFieldSetIP.setBounds(315, 80, 141, 27);
		contentPane.add(textFieldSetIP);

		lblSetPuerto = new JLabel("Puerto:");
		lblSetPuerto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSetPuerto.setBounds(466, 85, 49, 15);
		contentPane.add(lblSetPuerto);

		textFieldSetPuerto = new JTextField();
		textFieldSetPuerto.setColumns(10);
		textFieldSetPuerto.setBounds(526, 80, 80, 27);
		contentPane.add(textFieldSetPuerto);

		lblMessage = new JLabel("Message:");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMessage.setBounds(27, 334, 111, 15);
		contentPane.add(lblMessage);

		textFieldMessage = new JTextField();
		textFieldMessage.setEnabled(false);
		textFieldMessage.setBounds(27, 359, 458, 35);
		contentPane.add(textFieldMessage);
		textFieldMessage.setColumns(10);

		btnSendMessage = new JButton("Send Message");
		btnSendMessage.setEnabled(false);
		btnSendMessage.setBounds(495, 358, 111, 36);
		contentPane.add(btnSendMessage);
		
		messageList = new JList<>();
		messageList.setBounds(27, 129, 579, 193);
		DefaultListModel<String> messages = new DefaultListModel<String>();
		messageList.setModel(messages);
		
		scrollPaneMessages = new JScrollPane();
		scrollPaneMessages.setBounds(27, 129, 579, 193);
		contentPane.add(scrollPaneMessages, BorderLayout.WEST);
		scrollPaneMessages.add(messageList);

		btnSendMessage.addActionListener(this);
		btnCreateUser.addActionListener(this);
		btnDisconnect.addActionListener(this);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatView frame = new ChatView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCreateUser) {
			createUser();
		} else if (e.getSource() == btnSendMessage) {
			sendMessage();
		} else if (e.getSource() == btnDisconnect) {

		}
	}

	public void createUser() {
		if (ipPortCheck()) {
			ObjectOutputStream oos;
			ObjectInputStream ois;
			boolean full = false;
			user = new Usuario(textFieldNewUser.getText());
			try {
				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());
				oos.writeObject(user); // Sends user to server so that it can check if array is full
				full = (boolean) ois.readObject();

				if (!full) {
					client = new Socket(textFieldSetIP.getText(), Integer.parseInt(textFieldSetPuerto.getText()));
					thread = new ClientThread(user, client, messageList);
					thread.start();
					oos.writeObject(user);
					// e
				} else {
					JOptionPane.showMessageDialog(this, "No hay espacio para añadir mas usuarios.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean ipPortCheck() {
		boolean check = true;
		Pattern ip = Pattern.compile(
				"^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$"),
				port = Pattern.compile(
						"^([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$");
		Matcher ipMatcher = ip.matcher(textFieldSetIP.getText());
		if (!ipMatcher.matches()) {
			check = false;
		}

		Matcher portMatcher = port.matcher(textFieldSetIP.getText());
		if (!portMatcher.matches()) {
			check = false;
		}
		return check;
	}

	public void sendMessage() {
		thread.setMessage(textFieldMessage.getText());
		if (comboBoxUsers.getSelectedItem().equals("Public")) {
			thread.run();
		} else {
			user2 = new Usuario((String) comboBoxUsers.getSelectedItem());
			thread.setUser2(user2);
			thread.run();
		}
	}
}
