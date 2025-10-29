package model;

public class Mensaje {
	private String message;
	private Usuario user1;
	private Usuario user2;
	private boolean chatType;
	
	public Mensaje(String message, Usuario user1) {
		this.message = message;
		this.user1 = user1;
		this.user2 = new Usuario ("Public");
		this.chatType = false;
	}

	public Mensaje(String message, Usuario user1, Usuario user2) {
		this.message = message;
		this.user1 = user1;
		this.user2 = user2;
		this.chatType = true;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Usuario getUser1() {
		return user1;
	}

	public void setUser1(Usuario user1) {
		this.user1 = user1;
	}

	public Usuario getUser2() {
		return user2;
	}

	public void setUser2(Usuario user2) {
		this.user2 = user2;
	}

	public boolean isChatType() {
		return chatType;
	}

	public void setChatType(boolean chatType) {
		this.chatType = chatType;
	}
}
