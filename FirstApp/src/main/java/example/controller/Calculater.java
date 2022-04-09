package example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import example.model.CalcModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




public class Calculater extends HttpServlet {
	
	public Calculater() {
		super();
		
		
		// TODO Auto-generated constructor stub
	}



	/**
	 * 
	 * /FirstApp/src/main/java/example/controller/Calculater.java
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 PrintWriter out = response.getWriter();
		
		 double x,y;
		 if(request.getParameter("x")==null||request.getParameter("y")==null) {
			 
			 out.println("<br> invalid inputs " );
			 return;

		 }
		 else {
			 try {
				  x=Double.parseDouble(request.getParameter("x"));
				  y=Double.parseDouble(request.getParameter("y"));
				  double z= x+y;
				  
				  out.println("<h1>"+z+"</h1>");
				  
				 
				 
			 }
			 catch(NumberFormatException e) {
				 out.println("<br> invalid inputs " );
				 
			 }
		 }
		 
		 
	}

}
