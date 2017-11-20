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

import com.dao.ShoppingApp.CartDao;
import com.dao.ShoppingApp.ProductDao;
import com.dao.ShoppingApp.UserDao;
import com.google.gson.Gson;
import com.models.ShoppingApp.CheckLog;
import com.models.ShoppingApp.Login;
import com.models.ShoppingApp.Product;
import com.models.ShoppingApp.User;
@WebServlet("/shopping")
public class LoginController extends HttpServlet{
public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException{
	String action = request.getParameter("action");
	if(action.equals("logout"))
	{
		HttpSession session = request.getSession(false);
		session.invalidate();
		request.setAttribute("value","logout");
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request,  response);
	}
	else if(action.equals("checkout")){
		String page = request.getParameter("page");
		int pg = Integer.parseInt(page);
		int size = 10;
		int st = (pg-1)*size;
		UserDao verify = new UserDao();
		List<CheckLog> checks = new ArrayList<>();
		checks = verify.checkout(st, size);
		int count = verify.countCheck();
		count = count/size;
		if(count%size!=0){
			count = count+1;
		}
		request.setAttribute("checks", checks);
		request.setAttribute("count", count);
		RequestDispatcher view = request.getRequestDispatcher("CheckLog.jsp");
		view.forward(request,  response);
	}
	else if(request.getParameter("action").equals("search")){
		String q = request.getParameter("q");
		q = "%"+q+"%";
		UserDao verify = new UserDao();
		List<String> checks = new ArrayList<>();
	checks = verify.search(q);
		response.setContentType("application/json");
		Gson gson = new Gson();
		response.getWriter().print(gson.toJson(checks));
	}
	else if(request.getParameter("action").equals("name")){
		String name = request.getParameter("name");
		int pg = 1;
		int size = 10;
		int st = (pg-1)*size;
		UserDao verify = new UserDao();
		List<CheckLog> checks = new ArrayList<>();
		checks = verify.name(name, st, size);
		int count = 0;
		request.setAttribute("checks", checks);
		request.setAttribute("count", count);
		RequestDispatcher view = request.getRequestDispatcher("CheckLog.jsp");
		view.forward(request,  response);
		
	}
}

public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException{
	String action = request.getParameter("action");
	if(action.equals("login")){
		Login log = new Login(request.getParameter("user"),request.getParameter("pass"));
		UserDao verify = new UserDao();
		boolean bool = verify.verifylogin(log);
		if(bool==true){
			HttpSession session = request.getSession();
			List<Product> productList = new ArrayList<>();
			 ProductDao pro = new  ProductDao();
			productList = pro.getAllProducts();
			CartDao dao = new CartDao();
			List<Product> products = new ArrayList<>();
			products = dao.getAllProducts(log.getUser());
			int p = 0;
			p=products.stream().mapToInt(Product::getQty).sum();
			session.setAttribute("cartSize", p);
			session.setAttribute("username",log.getUser());
			request.setAttribute("Products",productList);
			session.setAttribute("name",verify.getName(log.getUser()));
	session.setAttribute("role",verify.getRole(log.getUser()));
			RequestDispatcher view = request.getRequestDispatcher("products.jsp");
			view.forward(request,  response);
		}
		else if(bool==false){
			request.setAttribute("username",log.getUser());
			request.setAttribute("value","failed");
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request,  response);
		}
		
	}
	if(action.equals("register")){
	User user = new User(request.getParameter("name"), request.getParameter("user"),request.getParameter("pass"));
	UserDao reg = new UserDao();
	boolean bool = reg.register(user);
	if(bool==true){
		request.setAttribute("name",user.getName());
		request.setAttribute("value","register");
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request,  response);
	}
	if(bool==false){
		request.setAttribute("name",user.getName());
		request.setAttribute("value","failed");
		RequestDispatcher view = request.getRequestDispatcher("register.jsp");
		view.forward(request,  response);
	}
	}
}
}
