package client;

import java.awt.*;
import java.io.*;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.*;

import model.Usuario;


public class ChatView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Usuario user;

	private final int PORT = 5000;
	private final String IP = "127.0.0.1";

	public void iniciar() {
		Socket cli = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;

		try {
			// Connect to server
			cli = new Socket(IP, PORT);
			System.out.println("Conexión realizada con servidor");
			salida = new ObjectOutputStream(cli.getOutputStream());
			entrada = new ObjectInputStream(cli.getInputStream());
			String mensaje = (String) entrada.readObject();
			System.out.println(mensaje);

			// Login

			// Is the login valid?
			mensaje = (String) entrada.readObject();
			System.out.println(mensaje);

			boolean valid = (boolean) entrada.readObject();
			if (valid) {

			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (entrada != null) {
					entrada.close();
				}
				if (salida != null) {
					salida.close();
				}
				if (cli != null) {
					cli.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Fin cliente");
		}
	}

	public ChatView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(28, 34, 189, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(349, 307, 84, 20);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(47, 308, 278, 18);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 87, 479, 197);
		contentPane.add(scrollPane);
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
