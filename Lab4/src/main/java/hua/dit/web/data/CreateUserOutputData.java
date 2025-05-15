package hua.dit.web.data;

public class CreateUserOutputData {

	final boolean insert_flag;

	public CreateUserOutputData(boolean insert_flag) {
		this.insert_flag = insert_flag;
	}

	@Override
	public String toString() {
		return "CreateUserOutputData [insert_flag=" + insert_flag + "]";
	}
	
}
