package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import model.Mensaje;
import model.Usuario;

public class Server {
	private final int PUERTO = 5000;
	private CheckTrhead Cthread;
	public void iniciar() {
		ArrayList<Usuario> users = new ArrayList<>();
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
	
		boolean active = true;
		try (ServerSocket servidor = new ServerSocket(PUERTO)) {
			users.add(new Usuario("Public"));
			Cthread = new CheckTrhead(users);
			Cthread.start();
			while (active) {
				
				System.out.println("Esperando conexiones del cliente...");
				try {
					
					cliente = servidor.accept();
					salida = new ObjectOutputStream(cliente.getOutputStream());
					 salida.flush();
					entrada = new ObjectInputStream(cliente.getInputStream());
					Usuario user = (Usuario) entrada.readObject();
					ClientHandler clientHandler = new ClientHandler(cliente, users);
	                clientHandler.start();
					
					if (users.size() < 11) {
						users.add(user);
						salida.writeObject(false);
					} else {
						salida.writeObject(true);
					}
					
					System.out.println("Cliente "+user.getUsername()+" conectado.");
					
					
					
					
					
					salida.writeObject("Bienvenid@ a usuario [PH]!");

					 System.out.println("Esperando conexiones del cliente...");
		                
		                
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		Server s = new Server();
		s.iniciar();
	}
}
