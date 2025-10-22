package client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import model.Usuario;

public class ChatView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Usuario user;
	private JLabel lblUsername, lblSetIP, lblSetPuerto;
	private JTextField textFieldMessage, textFieldNewUser, textFieldSetPuerto, textFieldSetIP;
	private JComboBox<String> comboBoxUsers;
	private JButton btnSendMessage, btnDisconnect, btnCreateUser;
	private JScrollPane scrollPaneMessages;
	private JLabel lblMessage;

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
		
		scrollPaneMessages = new JScrollPane();
		scrollPaneMessages.setEnabled(false);
		scrollPaneMessages.setBounds(27, 127, 579, 197);
		contentPane.add(scrollPaneMessages);
		
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

	}
}
