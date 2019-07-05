package com.appsdeveloperblog.app.ws.ui.model.response;

// This class is used to convert the Java object into outgoing JSON response
// This class contains user information that is being sent back as a confirmation that the user's details have been stored to the database and this is what we can return back
// UI layer -> user facing layer
public class UserRest {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
