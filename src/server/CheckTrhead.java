package server;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Usuario;

public class CheckTrhead extends Thread{
	private long serverStart;
	private ArrayList<Usuario> users;
	
	public CheckTrhead (ArrayList<Usuario> users) {
		this.users =users;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(30000);
				
				activity();
				
			} catch (InterruptedException e) {
				// TODO: handle exception
				System.err.println("[Log ERROR]"+ e.getMessage());
				
			}
		}
	}

	private void activity() {
		// TODO Auto-generated method stub
		String log = " \n Connected clients: "+(users.size()-1);
		System.out.println(log);
	}
}
