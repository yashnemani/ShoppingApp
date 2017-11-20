package com.controller.ShoppingApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.models.ShoppingApp.Product;
import com.dao.ShoppingApp.CartDao;
import com.dao.ShoppingApp.ProductDao;
import com.google.gson.Gson;;
@WebServlet("/productInfo")
public class ProductController extends HttpServlet{
public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException{
	HttpSession session = request.getSession(false);
	String role = session.getAttribute("role").toString();
	if(request.getParameter("action").equals("all")){
		List<Product> productList = new ArrayList<>();
		 ProductDao pro = new  ProductDao();
		productList = pro.getAllProducts();
		request.setAttribute("Products",productList);
		RequestDispatcher view = request.getRequestDispatcher("products.jsp");
		view.forward(request,  response);
	}
	else if(request.getParameter("action").equals("info")){
		String pId = request.getParameter("id");
		int prodId = Integer.parseInt(pId);
		session.setAttribute("id", pId);
		Product prod = new Product();
		ProductDao dao = new ProductDao();
		prod = dao.getProduct(prodId);
		request.setAttribute("product", prod);
		RequestDispatcher view = request.getRequestDispatcher("ProductInfo.jsp");
		view.forward(request,  response);
	}
	else if(request.getParameter("action").equals("edit")){
		if(role.equals("enduser")){
			RequestDispatcher view = request.getRequestDispatcher("403.jsp");
			view.forward(request,  response);
		}
		String pId = request.getParameter("id");
		int prodId = Integer.parseInt(pId);
		Product prod = new Product();
		ProductDao dao = new ProductDao();
		prod = dao.getProduct(prodId);
		request.setAttribute("product", prod);
		RequestDispatcher view = request.getRequestDispatcher("EditProduct.jsp");
		view.forward(request,  response);
	}
	else if(request.getParameter("action").equals("delete")){
		if(role.equals("enduser")){
			RequestDispatcher view = request.getRequestDispatcher("403.jsp");
			view.forward(request,  response);
		}
		String pId = request.getParameter("id");
		int prodId = Integer.parseInt(pId);
		Product prod = new Product();
		ProductDao dao = new ProductDao();
		boolean bool = dao.deleteProduct(prodId);
		if(bool==true){
			List<Product> productList = new ArrayList<>();
			 ProductDao pro = new  ProductDao();
			productList = pro.getAllProducts();
			request.setAttribute("Products",productList);
			RequestDispatcher view = request.getRequestDispatcher("products.jsp");
			view.forward(request,  response);
		}
		else{
			prod = dao.getProduct(prodId);
			request.setAttribute("product", prod);
			RequestDispatcher view = request.getRequestDispatcher("ProductInfo.jsp");
			view.forward(request,  response);
		}
	}
	else if(request.getParameter("action").equals("search")){
		String q = request.getParameter("q");
		q = "%"+q+"%";
		ProductDao dao = new ProductDao();
		List<Product> hints = new ArrayList<>();
		hints = dao.searchProduct(q);
		response.setContentType("application/json");
		Gson gson = new Gson();
		response.getWriter().print(gson.toJson(hints));
	}
}
public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException{
	HttpSession session = request.getSession(false);
	String role = session.getAttribute("role").toString();
	 if(request.getParameter("action").equals("change")){
			if(role.equals("enduser")){
				RequestDispatcher view = request.getRequestDispatcher("403.jsp");
				view.forward(request,  response);
			}
			String pId = request.getParameter("id");
			int prodId = Integer.parseInt(pId);
			int qty = Integer.parseInt(request.getParameter("qty"));
			float price = Float.parseFloat(request.getParameter("price"));
			Product prod = new Product();
			ProductDao dao = new ProductDao();
			prod.setName(request.getParameter("name"));
			prod.setProduct(request.getParameter("product"));
			prod.setMake(request.getParameter("make"));
			prod.setCategory(request.getParameter("category"));
			prod.setPrice(price);
			prod.setQty(qty);
			prod.setId(prodId);
			prod.setDescription(request.getParameter("info"));
			boolean bool = dao.editProduct(prod);
			if(bool==true){
				prod = dao.getProduct(prodId);
				request.setAttribute("product", prod);
				RequestDispatcher view = request.getRequestDispatcher("ProductInfo.jsp");
				view.forward(request,  response);
			}
			if(bool==false){
				request.setAttribute("product", prod);
				RequestDispatcher view = request.getRequestDispatcher("EditProduct.jsp");
				view.forward(request,  response);
			}
		}
		else if(request.getParameter("action").equals("add")){
			if(role.equals("enduser")){
				RequestDispatcher view = request.getRequestDispatcher("403.jsp");
				view.forward(request,  response);
			}
			int qty = Integer.parseInt(request.getParameter("qty"));
			float price = Float.parseFloat(request.getParameter("price"));
			Product prod = new Product();
			ProductDao dao = new ProductDao();
			prod.setName(request.getParameter("name"));
			prod.setProduct(request.getParameter("product"));
			prod.setMake(request.getParameter("make"));
			prod.setCategory(request.getParameter("category"));
			prod.setPrice(price);
			prod.setQty(qty);
			prod.setDescription(request.getParameter("info"));
			boolean bool = dao.addProduct(prod);
			if(bool==true){
				List<Product> productList = new ArrayList<>();
				 ProductDao pro = new  ProductDao();
				productList = pro.getAllProducts();
				request.setAttribute("Products",productList);
				RequestDispatcher view = request.getRequestDispatcher("products.jsp");
				view.forward(request,  response);
			}
			if(bool==false){
				request.setAttribute("product", prod);
				RequestDispatcher view = request.getRequestDispatcher("EditProduct.jsp");
				view.forward(request,  response);
			}
		}
		else if(request.getParameter("action").equals("name")){
			String name = request.getParameter("search");
			Product prod = new Product();
			ProductDao dao = new ProductDao();
			prod = dao.getProduct1(name);
			request.setAttribute("product", prod);
			RequestDispatcher view = request.getRequestDispatcher("ProductInfo.jsp");
			view.forward(request,  response);
			
		}
}
}
