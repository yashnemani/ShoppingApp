package com.dao.ShoppingApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.models.ShoppingApp.Product;

public class CartDao{

	Datasource ds = new Datasource();
	Connection con = DBconnection.getConnection(ds);
	PreparedStatement stm = null;
	PreparedStatement stmt = null;
	public List<Product> getAllProducts(String user) {
		String sql = "select * from products where product_id in (select prod_id from cart where user_name=?)";
		String q = "select qty from cart where user_name=? and prod_id=?";
		int count = 0;
		List<Product> productList = new ArrayList<>();
		try{
			stm = con.prepareStatement(sql);
			stm.setString(1, user);
			stmt = con.prepareStatement(q);
			stmt.setString(1, user);
		try {
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				stmt.setInt(2, rs.getInt(1));
				ResultSet rs1 =  stmt.executeQuery();
				while(rs1.next()){
					count=rs1.getInt(1);
				}
				Product product = new Product();
				product.setId(rs.getInt(1));
			 product.setProduct(rs.getString(2));
			 product.setMake(rs.getString(4));
			 product.setName(rs.getString(3));
			 product.setCategory(rs.getString(5));
			 product.setQty(count);
			 product.setPrice(rs.getInt(6));
			 product.setDescription(rs.getString(8));
			 productList.add(product); 
			/* while(count>0){
				 productList.add(product); 
				 count = count-1;
			 }*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	public boolean addToCart(int prodId, String user, int qt) {
		String q = "select * from cart where prod_id=? and user_name=?";
		String sql = "insert into cart(prod_id, user_name,qty) values(?,?,?)";
		String up = "update cart set qty=qty+"+qt+" where prod_id=? and user_name=?";
		try{
			stm = con.prepareStatement(sql);
			stmt= con.prepareStatement(q);
			stm.setInt(1, prodId);
			stm.setString(2,user);
			stm.setInt(3, qt);
			stmt.setInt(1, prodId);
			stmt.setString(2,user);
		try {
			ResultSet rs = stmt.executeQuery();
			boolean empty = true;
			while(rs.next()){
				empty=false;
			}
			if(empty==true){
				stm.executeUpdate();
			}
			else if(empty==false){
				stm = con.prepareStatement(up);
				stm.setInt(1, prodId);
				stm.setString(2, user);
				stm.executeUpdate();
			}
			return true;
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean removeFromCart(int prodId, String user, int qt) {
		String q = "select qty from cart where prod_id=? and user_name=?";
		String sql = "update cart set qty=qty-"+qt+" where prod_id=? and user_name=?";
		String s = "delete from cart where prod_id=? and user_name=?";
		int qty = 0;
		try{
			stm = con.prepareStatement(q);
			stm.setInt(1, prodId);
			stm.setString(2,user);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, prodId);
			stmt.setString(2,user);
		try {
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				qty = rs.getInt(1);
			}
			if(qty==qt| qty<qt){
				stm = con.prepareStatement(s);
				stm.setInt(1, prodId);
				stm.setString(2,user);
				int rows = stm.executeUpdate();
				if(rows>0)
					return true;
			}
			else {
				int rowsAffected = stmt.executeUpdate();
				if(rowsAffected>0){
					return true;	
				}
			}
			
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean emptyCart(String user) {
		String q = "select * from cart where user_name=?";
		String sql = "delete from cart where user_name=?";
		String s = "insert into checkout(user_name,prod_id,qty) values(?,?,?) ";
		try{
			stm = con.prepareStatement(q);
			stm.setString(1,user);
			stmt = con.prepareStatement(s);
		try {
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				stmt.setString(1, rs.getString(2));
				stmt.setInt(2, rs.getInt(3));
				stmt.setInt(3, rs.getInt(4));
				int rows = stmt.executeUpdate();
				if(rows==0){
					return false;
				}
			}
			stm = con.prepareStatement(sql);
			stm.setString(1, user);
			int row = stm.executeUpdate();
			if(row==0){
				return false;
			}
				return true;	
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}