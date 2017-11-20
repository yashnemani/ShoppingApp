package com.models.ShoppingApp;

public class User {
private String name;
private String user;
private String pass;
private String role = "enduser";

public String getName() {
	return name;
}
public User(String name, String user, String pass) {
	super();
	this.name = name;
	this.user = user;
	this.pass = pass;
}
public void setName(String name) {
	this.name = name;
}
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
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

}
