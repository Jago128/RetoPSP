package client;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import model.Usuario;

public class ChatView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMessage, textFieldNewUser;
	private Usuario user;

	public ChatView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setEnabled(false);
		comboBox.setBounds(27, 90, 348, 27);
		contentPane.add(comboBox);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.setEnabled(false);
		btnSendMessage.setBounds(396, 347, 111, 36);
		contentPane.add(btnSendMessage);
		
		textFieldMessage = new JTextField();
		textFieldMessage.setEnabled(false);
		textFieldMessage.setBounds(27, 348, 348, 35);
		contentPane.add(textFieldMessage);
		textFieldMessage.setColumns(10);
		
		JScrollPane scrollPaneMessages = new JScrollPane();
		scrollPaneMessages.setEnabled(false);
		scrollPaneMessages.setBounds(27, 127, 496, 197);
		contentPane.add(scrollPaneMessages);
		
		textFieldNewUser = new JTextField();
		textFieldNewUser.setColumns(10);
		textFieldNewUser.setBounds(27, 32, 348, 35);
		contentPane.add(textFieldNewUser);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(396, 31, 111, 36);
		contentPane.add(btnCreateUser);
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
