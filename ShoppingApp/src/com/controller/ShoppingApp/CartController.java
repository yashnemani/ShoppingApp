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
import com.google.gson.Gson;
import com.models.ShoppingApp.Product;
@WebServlet("/addCart")
public class CartController extends HttpServlet{

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		String act = request.getParameter("action");
		HttpSession session = request.getSession(false);
		String user = session.getAttribute("username").toString();
		String csize = session.getAttribute("cartSize").toString();
		/*System.out.println(user);*/
		if(act.equals("cart")){
			CartDao dao = new CartDao();
			List<Product> products = new ArrayList<>();
			products = dao.getAllProducts(user);
			request.setAttribute("products", products);
			RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
			view.forward(request,  response);
		}
		else if(act.equals("size")){
			response.setContentType("text");
			response.getWriter().write(csize);
		}
		else if(act.equals("remove")){
			String pId = request.getParameter("id");
			int prodId = Integer.parseInt(pId);
			String qty = request.getParameter("qt");
			System.out.println(qty);
			int qt = Integer.parseInt(qty);
			CartDao dao = new CartDao();
			boolean bool = dao.removeFromCart(prodId, user, qt);
			response.setContentType("application/json");
			if(bool==true){
				int cs = Integer.parseInt(csize);
				cs=cs-qt;
				session.setAttribute("cartSize", cs);
				List<Product> products = new ArrayList<>();
				products = dao.getAllProducts(user);
				Gson gson = new Gson();
				response.getWriter().print(gson.toJson(products));
			}
			if(bool==false){
				response.getWriter().write("Failure");
			}
		}
		else if(act.equals("add")){
			String pId = request.getParameter("id");
			String qty = request.getParameter("qt");
			System.out.println(qty);
			int qt = Integer.parseInt(qty);
			int prodId = Integer.parseInt(pId);
			System.out.println("id "+prodId+" user "+user);
			CartDao dao = new CartDao();
			boolean bool = dao.addToCart(prodId, user,qt);
			response.setContentType("text");
			if(bool==true){
				int cs = Integer.parseInt(csize);
				cs=cs+qt;
				csize = String.valueOf(cs);
				session.setAttribute("cartSize", cs);
				response.getWriter().write(csize);
			}
			if(bool==false){
				response.getWriter().write(csize);
			}
		}
		else if(act.equals("checkout")){
			CartDao dao = new CartDao();
			boolean bool = dao.emptyCart(user);
			if(bool==true){
				int cs = Integer.parseInt(csize);
				cs=0;
				session.setAttribute("cartSize", cs);
				List<Product> products = new ArrayList<>();
				request.setAttribute("products", products);
				RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
				view.forward(request,  response);
			}
			if(bool==false){
				List<Product> products = new ArrayList<>();
				request.setAttribute("products", products);
				RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
				view.forward(request,  response);
			}
		}
			
	}
}
