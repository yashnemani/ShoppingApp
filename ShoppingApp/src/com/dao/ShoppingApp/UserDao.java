package com.dao.ShoppingApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.models.ShoppingApp.CheckLog;
import com.models.ShoppingApp.Login;
import com.models.ShoppingApp.Product;
import com.models.ShoppingApp.User;

public class UserDao{
	Datasource ds = new Datasource();
	Connection conn = DBconnection.getConnection(ds);
	PreparedStatement stmt = null;
	//Method to register a user with given details
	public boolean register(User user) {
		String sql = "INSERT INTO user_details(name, user, password, role) VALUES(?,?,?,?)";
		boolean bool = false;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());
stmt.setString(2, user.getUser());
stmt.setString(3, user.getPass());
stmt.setString(4, user.getRole());
			try{
				stmt.executeUpdate();
				bool = true;
			}
			 catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
	System.out.println("result set sql exception");
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
		}
		catch (SQLException e) {
		  System.out.println("Sql Exception Caught");
		}
		return bool;
	}
	//Method to verify User Login Details
	public boolean verifylogin(Login log) {
		String sql = "select password from user_details where user=?";
		String user = log.getUser();
		String pass = null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			try{
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
				pass = rs.getString( "password") ;
			 }
			rs.close();
			}
			 catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
	System.out.println("result set sql exception");
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
		}
		catch (SQLException e) {
		  System.out.println("Sql Exception Caught");
		}
	  	if(pass==null){
	  		System.out.println("Username does not exist");
			return false;
		}
		else if(pass.equals(log.getPass())) {
			return true;
		}
		return false;
	}
	
	//Method to get User role
	public String getRole(String user){
		String sql = "select role from user_details where user=?";
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			try{
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					String role = rs.getString(1);
					return role;
				}
				
			}
			 catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
	System.out.println("result set sql exception");
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
		}
		catch (SQLException e) {
		  System.out.println("Sql Exception Caught");
		}
		return null;
	}
	
	//Method to get User Name
	public String getName(String user){
		String sql = "select name from user_details where user=?";
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			try{
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					String role = rs.getString(1);
					return role;
				}
				
			}
			 catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
	System.out.println("result set sql exception");
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
		}
		catch (SQLException e) {
		  System.out.println("Sql Exception Caught");
		}
		return null;
	}
	public List<CheckLog> checkout(int start, int size){
		String sql ="select * from checkout limit ?,?";
		List<CheckLog> checks = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, start);
			stmt.setInt(2, size);
			try{
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					CheckLog check = new CheckLog();
					check.setUser(rs.getString(2));
					check.setProdId(rs.getInt(3));
					check.setQty(rs.getInt(4));
					check.setDateTime(rs.getTimestamp(5).toString());
					checks.add(check);
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checks;
	}
	public int countCheck(){
		String sql ="select count(*) from checkout";
		int count = 0;
		try{
			stmt = conn.prepareStatement(sql);
			try{
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					count = rs.getInt(1);
				}
				return count;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public List<String> search(String q) {
		String sql = "select user_name from checkout where user_name like ?";
		List<String> hints = new ArrayList<>();
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, q);
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				if(hints.contains(rs.getString(1))){
					continue;
				}
				hints.add(rs.getString(1));
			}
			return hints;
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return hints;
	}
	public List<CheckLog> name(String name, int start, int size){
		String sql ="select * from checkout where user_name=? limit ?,?";
		List<CheckLog> checks = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, start);
			stmt.setInt(3, size);
			try{
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					CheckLog check = new CheckLog();
					check.setUser(rs.getString(2));
					check.setProdId(rs.getInt(3));
					check.setQty(rs.getInt(4));
					check.setDateTime(rs.getTimestamp(5).toString());
					checks.add(check);
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checks;
	}
}
