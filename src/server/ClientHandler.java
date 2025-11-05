package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Mensaje;
import model.Usuario;

public class ClientHandler extends Thread {
    private Socket cliente;
    private ArrayList<Usuario> users;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Usuario usuario;
    
    public ClientHandler(Socket cliente, ArrayList<Usuario> users) {
        this.cliente = cliente;
        this.users = users;
    }
    
    @Override
    public void run() {
        try {
            salida = new ObjectOutputStream(cliente.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(cliente.getInputStream());
            
            // 1. Autenticación
            usuario = (Usuario) entrada.readObject();
            
            boolean servidorLleno;
            synchronized(users) {
                if (users.size() < 11) {
                    users.add(usuario);
                    servidorLleno = false;
                } else {
                    servidorLleno = true;
                }
            }
            
                salida.writeObject(servidorLleno);
            
            
            System.out.println("Cliente " + usuario.getUsername() + " conectado.");
            salida.writeObject("Bienvenid@ " + usuario.getUsername() + "!");
            
            // 2. Bucle principal para múltiples mensajes
            while (cliente.isConnected() && !cliente.isClosed()) {
                Mensaje msg = (Mensaje) entrada.readObject();
                System.out.println("Mensaje recibido de " + usuario.getUsername() + ": " + msg.getMessage());
                
                // Verificar si el usuario existe
                boolean usuarioExiste;
                synchronized(users) {
                    usuarioExiste = cLIuser(msg, users);
                }
                
                if (usuarioExiste) {
                    salida.writeObject(msg);
                } else {
                    salida.writeObject("ERROR: no existe ese usuario");
                }
            }
            
        } catch (EOFException e) {
            System.out.println("Cliente " + (usuario != null ? usuario.getUsername() : "desconocido") + " desconectado");
        } catch (SocketException e) {
            System.out.println("Conexión con " + (usuario != null ? usuario.getUsername() : "desconocido") + " cerrada");
        } catch (Exception e) {
            System.out.println("Error con cliente " + (usuario != null ? usuario.getUsername() : "desconocido") + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
    }
    private boolean cLIuser(Mensaje msg, ArrayList<Usuario> users) {
    	boolean itis=false;
        for (Usuario user : users) {
            if (msg.getUser2().equals(user)) {
            	itis=false;
            }
        }
        return itis;
    }
    private void closeConnection() {
        // Remover usuario de la lista de forma sincronizada
        if (usuario != null) {
            synchronized(users) {
                users.remove(usuario);
            }
            System.out.println("Usuario " + usuario.getUsername() + " removido");
        }
        
        try {
            if (entrada != null) entrada.close();
            if (salida != null) salida.close();
            if (cliente != null) cliente.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
