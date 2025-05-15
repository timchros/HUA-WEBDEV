package hua.dit.web.data;

public class CreateUserInputData {

	private final String username;
	private final String password;
	
	public CreateUserInputData(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "CreateUserInputData [username=" + username + ", password=" + password + "]";
	}
	
}
