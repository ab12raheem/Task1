package example.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.EmployeeDao;
import dao.RoleDao;
import example.model.EmployeeModel;
import example.model.RoleModel;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao=new EmployeeDao();
    private RoleDao roleDao = new RoleDao();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		//System.out.println(userName);
		//System.out.println(password);
		RequestDispatcher dispatcher;
		
		try {
			EmployeeModel employee=employeeDao.getByUserName(userName);
			if(password.equals(employee.getPassword())) {
				
				RoleModel role = employee.getRole();
				switch(role.getName()) {
				case "Manager":
					dispatcher = request.getRequestDispatcher("/AdminRole");
					dispatcher.forward(request, response);
					
					break;
				case "TeamLeader": 
					dispatcher = request.getRequestDispatcher("/LeaderRole");
					dispatcher.forward(request, response);
					break;
				case "Developer":
					dispatcher = request.getRequestDispatcher("/DeveloperRole");
					dispatcher.forward(request, response);
					break;
				default:
					throw new IllegalStateException("this user has no role");
					
				}
				 
				///FirstApp/src/main/java/example/controller/Login.java
				
			}else {
				 dispatcher = request.getRequestDispatcher("/log");
				dispatcher.forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	      
	}

}
