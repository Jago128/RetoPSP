package threads;

import java.io.*;
import java.net.Socket;

import javax.swing.*;

import model.Usuario;

public class ClientThread implements Runnable {

	private Usuario user1;
	private Usuario user2;
	private String message;
	private Socket client;
	public Thread threadClient;
	private JList<String> messageList;

	public void start() {
		threadClient = new Thread(this, "Client");
		threadClient.start();
	}

	@Override
	public void run() {
		if (user2 != null) {
			publicChat(user1, message);
		} else {
			privateChat(user1, user2, message);
		}
	}

	synchronized void publicChat(Usuario user, String message) {
		boolean sent = true;
		ObjectOutputStream salida;
		ObjectInputStream entrada;
		DefaultListModel<String> messages = (DefaultListModel<String>) messageList.getModel();

		try {
			salida = new ObjectOutputStream(client.getOutputStream());
			salida.writeObject(message);
		} catch (IOException e) {
			sent = false;
		}

		if (!sent) {
			try {
				entrada = new ObjectInputStream(client.getInputStream());
				String error = (String) entrada.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			while (messages.contains("[PH]")) {
				messages.removeElement("[PH]");
			}
			messages.addElement(message);
			SwingUtilities.invokeLater(() -> messageList.setModel(messages));
		}
	}

	synchronized void privateChat(Usuario user1, Usuario user2, String message) {
		boolean sent = true;
		ObjectOutputStream salida;
		ObjectInputStream entrada;
		DefaultListModel<String> messages = (DefaultListModel<String>) messageList.getModel();

		try {
			salida = new ObjectOutputStream(client.getOutputStream());
			salida.writeObject(message);
		} catch (IOException e) {
			sent = false;
		}

		if (!sent) {
			try {
				entrada = new ObjectInputStream(client.getInputStream());
				String error = (String) entrada.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			while (messages.contains("[PH]")) {
				messages.removeElement("[PH]");
			}
			messages.addElement(message);
			SwingUtilities.invokeLater(() -> messageList.setModel(messages));
		}
	}

	public ClientThread(Usuario user, Socket client, JList<String> messageList) {
		this.user1 = user;
		this.message = "";
		this.client = client;
		this.messageList = messageList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Usuario getUser2() {
		return user2;
	}

	public void setUser2(Usuario user2) {
		this.user2 = user2;
	}
}
