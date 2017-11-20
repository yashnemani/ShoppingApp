package com.dao.ShoppingApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.models.ShoppingApp.Product;

public class ProductDao{

	Datasource ds = new Datasource();
	Connection con = DBconnection.getConnection(ds);
	PreparedStatement stm = null;
	public List<Product> getAllProducts() {
		String sql = "select * from products";
		List<Product> productList = new ArrayList<>();
		try{
			stm = con.prepareStatement(sql);
		try {
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
			 product.setProduct(rs.getString(2));
			 product.setMake(rs.getString(4));
			 product.setName(rs.getString(3));
			 product.setCategory(rs.getString(5));
			 product.setQty(rs.getInt(7));
			 product.setPrice(rs.getInt(6));
			 product.setDescription(rs.getString(8));
			 productList.add(product);
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
	
	public Product getProduct(int prodId) {
		String sql = "SELECT * FROM products WHERE product_id = ?";
		try{
			stm = con.prepareStatement(sql);
			stm.setInt(1, prodId);
		try {
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
			 product.setProduct(rs.getString(2));
			 product.setMake(rs.getString(4));
			 product.setName(rs.getString(3));
			 product.setCategory(rs.getString(5));
			 product.setQty(rs.getInt(7));
			 product.setPrice(rs.getInt(6));
			 product.setDescription(rs.getString(8));
			 return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean editProduct(Product product) {
		String sql = "update products set name=?,product=?,make=?,category=?, price=?,qty=?,info=? WHERE product_id = ?";
		try{
			stm = con.prepareStatement(sql);
			stm.setInt(8, product.getId());
			stm.setString(1, product.getName());
			stm.setString(2, product.getProduct());
			stm.setString(3, product.getMake());
			stm.setString(4, product.getCategory());
			stm.setFloat(5, product.getPrice());
			stm.setInt(6, product.getQty());
			stm.setString(7, product.getDescription());
		try {
			int rowsAffected = stm.executeUpdate();
		if(rowsAffected>0){
			return true;
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
	public boolean addProduct(Product product) {
		String sql = "insert into products(name,product,make,category,price,qty,info) values(?,?,?,?,?,?,?)";
		try{
			stm = con.prepareStatement(sql);

			stm.setString(1, product.getName());
			stm.setString(2, product.getProduct());
			stm.setString(3, product.getMake());
			stm.setString(4, product.getCategory());
			stm.setFloat(5, product.getPrice());
			stm.setInt(6, product.getQty());
			stm.setString(7, product.getDescription());
		try {
			int rowsAffected = stm.executeUpdate();
		if(rowsAffected>0){
			return true;
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
	public boolean deleteProduct(int id) {
		String sql = "delete from products where product_id=?";
		try{
			stm = con.prepareStatement(sql);
			stm.setInt(1, id);
		try {
			int rowsAffected = stm.executeUpdate();
		if(rowsAffected>0){
			return true;
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
	public List<Product> searchProduct(String q) {
		String sql = "select name,product_id from products where name like ?";
		List<Product> hints = new ArrayList<>();
		try{
			stm = con.prepareStatement(sql);
			stm.setString(1, q);
		try {
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(2));
				product.setName(rs.getString(1));
				hints.add(product);
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

	public Product getProduct1(String name) {
		String sql = "SELECT * FROM products WHERE name = ?";
		try{
			stm = con.prepareStatement(sql);
			stm.setString(1, name);
		try {
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt(1));
			 product.setProduct(rs.getString(2));
			 product.setMake(rs.getString(4));
			 product.setName(rs.getString(3));
			 product.setCategory(rs.getString(5));
			 product.setQty(rs.getInt(7));
			 product.setPrice(rs.getInt(6));
			 product.setDescription(rs.getString(8));
			 return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
