package com.models.ShoppingApp;

public class Login {
private String user;
private String pass;
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public Login(String user, String pass) {
	super();
	this.user = user;
	this.pass = pass;
}

}
