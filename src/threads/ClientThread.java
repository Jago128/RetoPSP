package threads;

import java.io.*;
import java.net.Socket;

import javax.swing.*;

import model.*;

public class ClientThread implements Runnable {

	private Mensaje message;
	private Socket client;
	public Thread threadClient;
	private JList<String> messageList;

	public void start() {
		threadClient = new Thread(this, "Client");
		threadClient.start();
	}

	@Override
	public void run() {
		if (message.getUser2() != null) {
			publicChat(message);
		} else {
			privateChat(message);
		}
	}

	synchronized void publicChat(Mensaje message) {
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
				messages.addElement(error);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			while (messages.contains("[PH]")) {
				messages.removeElement("[PH]");
			}
			
			while (messages.contains("[PH]")) {
				messages.removeElement("[PH]");
			}
			
			messages.addElement(message.getMessage());
			SwingUtilities.invokeLater(() -> messageList.setModel(messages));
		}
	}

	synchronized void privateChat(Mensaje message) {
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
				messages.addElement(error);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			while (messages.contains("[PH]")) {
				messages.removeElement("[PH]");
			}

			while (messages.contains("[PH]")) {
				messages.removeElement("[PH]");
			}

			messages.addElement(message.getMessage());
			SwingUtilities.invokeLater(() -> messageList.setModel(messages));
		}
	}

	public ClientThread(Socket client, JList<String> messageList) {
		this.client = client;
		this.messageList = messageList;
	}

	public Mensaje getMessage() {
		return message;
	}

	public void setMessage(Mensaje message) {
		this.message = message;
	}
}
