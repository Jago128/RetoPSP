package client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import model.Usuario;

public class ChatView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Usuario user;
	private JTextField textFieldMessage, textFieldNewUser, textFieldSetPuerto, textFieldSetIP;

	public ChatView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setEnabled(false);
		comboBox.setBounds(27, 80, 256, 27);
		contentPane.add(comboBox);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.setEnabled(false);
		btnSendMessage.setBounds(495, 347, 111, 36);
		contentPane.add(btnSendMessage);
		
		textFieldMessage = new JTextField();
		textFieldMessage.setEnabled(false);
		textFieldMessage.setBounds(27, 348, 458, 35);
		contentPane.add(textFieldMessage);
		textFieldMessage.setColumns(10);
		
		JScrollPane scrollPaneMessages = new JScrollPane();
		scrollPaneMessages.setEnabled(false);
		scrollPaneMessages.setBounds(27, 127, 579, 197);
		contentPane.add(scrollPaneMessages);
		
		textFieldNewUser = new JTextField();
		textFieldNewUser.setColumns(10);
		textFieldNewUser.setBounds(27, 35, 458, 35);
		contentPane.add(textFieldNewUser);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(495, 34, 111, 36);
		contentPane.add(btnCreateUser);
		
		textFieldSetPuerto = new JTextField();
		textFieldSetPuerto.setColumns(10);
		textFieldSetPuerto.setBounds(526, 80, 80, 27);
		contentPane.add(textFieldSetPuerto);
		
		textFieldSetIP = new JTextField();
		textFieldSetIP.setColumns(10);
		textFieldSetIP.setBounds(315, 80, 141, 27);
		contentPane.add(textFieldSetIP);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(27, 10, 111, 15);
		contentPane.add(lblUsername);
		
		JLabel lblSetIP = new JLabel("IP:");
		lblSetIP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSetIP.setBounds(293, 85, 15, 15);
		contentPane.add(lblSetIP);
		
		JLabel lblSetPuerto = new JLabel("Puerto:");
		lblSetPuerto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSetPuerto.setBounds(466, 85, 49, 15);
		contentPane.add(lblSetPuerto);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(495, 3, 111, 27);
		contentPane.add(btnDisconnect);
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
}
