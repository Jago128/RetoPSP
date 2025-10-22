package threads;

import java.net.Socket;

import model.Usuario;

public class ClientThread implements Runnable {

	private Usuario user1;
	private Usuario user2;
	private String message;
	private boolean sent;
	private Socket client;
	public Thread threadClient;

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
		
		if (sent) {
			
		} else {
			
		}
	}
	
	synchronized void privateChat(Usuario user1, Usuario user2, String message) {
		
	}
	
	public ClientThread(Usuario user, String message, Socket client) {
		this.user1 = user;
		this.message = message;
		this.sent = false;
		this.client = client;
	}

	public ClientThread(Usuario user1, Usuario user2, String message, Socket client) {
		this.user1 = user1;
		this.user2 = user2;
		this.message = message;
		this.sent = false;
		this.client = client;
	}
}
