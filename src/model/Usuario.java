package model;

public class Usuario {
	private String username;

	public Usuario(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Usuario [username=" + username + "]";
	}
}
