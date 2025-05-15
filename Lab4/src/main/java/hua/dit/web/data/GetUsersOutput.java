package hua.dit.web.data;

import java.util.List;

import hua.dit.web.db.User;

public class GetUsersOutput {

	final int recordsNumber;
	final List<User> userList;
	
	public GetUsersOutput(int recordsNumber, List<User> userList) {
		this.recordsNumber = recordsNumber;
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "GetUsersOutput [recordsNumber=" + recordsNumber + ", userList=" + userList + "]";
	}
	
}
