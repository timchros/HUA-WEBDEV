package test;

public class UserData {

	private final String username;
	private final String sex;
	private final Integer age;
	
	public UserData(String username, String sex, 
			Integer age) {
		super();
		this.username = username;
		this.sex = sex;
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public String getSex() {
		return sex;
	}

	public Integer getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "UserData [" + 
	              "username=" + username + ", " + 
				  "sex=" + sex + ", " + 
	              "age=" + age + 
			   "]";
	}
	
}

