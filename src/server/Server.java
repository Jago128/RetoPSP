package server;

import java.io.*;
import java.net.*;

public class Server {
	private final int PUERTO = 5000;

	public void iniciar() {
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
					salida.writeObject("Hola! Introduzca tu nombre de usuario para acceder al chat.");
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

	public static void main(String[] args) {
		Server s = new Server();
		s.iniciar();
	}
}
