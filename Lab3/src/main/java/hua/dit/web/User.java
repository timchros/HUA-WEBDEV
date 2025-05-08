package hua.dit.web;

public class User {

	private int id;
	private String username;
	private String password_hash;
	
	public User(int id, String username, String password_hash) {
		this.id = id;
		this.username = username;
		this.password_hash = password_hash;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return password_hash;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password_hash=" + password_hash + "]";
	}
	
}
