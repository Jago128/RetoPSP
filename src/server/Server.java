package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import model.Mensaje;
import model.Usuario;

public class Server {
	private final int PUERTO = 5000;

	public void iniciar() {
		ArrayList<Usuario> users = new ArrayList<>();
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
		boolean active = true;
		try (ServerSocket servidor = new ServerSocket(PUERTO)) {
			while (active) {

				System.out.println("Esperando conexiones del cliente...");
				try {
					cliente = servidor.accept();
					System.out.println("Cliente conectado.");
					salida = new ObjectOutputStream(cliente.getOutputStream());
					entrada = new ObjectInputStream(cliente.getInputStream());
					users.add(new Usuario("Public"));
					Usuario user = (Usuario) entrada.readObject();
					if (users.size() < 11) {
						users.add(user);
						salida.writeObject(false);
					} else {
						salida.writeObject(true);
					}

					salida.writeObject("Bienvenid@ a usuario [PH]!");

					Mensaje msg = (Mensaje) entrada.readObject();
					boolean logged = cLIuser(msg, users);
					if (logged) {
						salida.writeObject(msg);
						}else {
						salida.writeObject("ERROR no existe ese usuario");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					try {
						if (entrada != null)
							entrada.close();
						if (salida != null)
							salida.close();
						if (cliente != null)
							cliente.close();
					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean cLIuser(Mensaje msg, ArrayList<Usuario> users) {
		// TODO Auto-generated method stub
		boolean itis = false;
		for (int i = 0; i < users.size(); i++) {
			if (msg.getUser2().equals(users.get(i))) {
				itis = true;
			}
		}
		return itis;
	}

	public static void main(String[] args) {
		Server s = new Server();
		s.iniciar();
	}
}
