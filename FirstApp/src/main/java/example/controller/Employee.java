package example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import dao.EmployeeDao;
import dao.RoleDao;
import example.model.EmployeeModel;
import example.model.RoleModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Employee
 */

public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private EmployeeDao employeeDao=new EmployeeDao();
       private RoleDao roleDao=new RoleDao();
       String first_name,last_name,email,password,user_name;
       Integer id,role_id;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
			switch (action) {
			/*case "/editEmployee":
				showEditForm(request,response);
				break;
*/
			case "/addEmployee":
				insertEmployee(request, response);
				break;

			case "/deleteEmployee":
				deleteEmployee(request, response);
				break;

			/*case "/updateEmployee":
				updateEmployee(request, response);
				break;*/
			case "/getEmployee":
				getEmployeeByUserName(request, response);
				break;
			case "/getTeam":
				getTeam(request, response);
				break;	
		
			default:
				listEmployees(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void getTeam(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("id")==null) {
			throw new IllegalStateException("id is null");
		}
		id=Integer.parseInt(request.getParameter("id"));
		Set<EmployeeModel> team=employeeDao.getTeam(id);
		request.setAttribute("team", team);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
		
		
	}

	private void getEmployeeByUserName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("user_name")==null) {
			throw new IllegalStateException("fill all the fields");
		}
		EmployeeModel employee=employeeDao.getByUserName(request.getParameter("user_name"));
		request.setAttribute("employee", employee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		if(request.getParameter("user_name")==null) {
			throw new IllegalStateException("fill all the fields");
		}
		
		
		employeeDao.deleteEmployee(request.getParameter("user_name"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
		
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		if(request.getParameter("first_name")==null||request.getParameter("last_name")==null||request.getParameter("role_id")==null
				||request.getParameter("email")==null||request.getParameter("password")==null||request.getParameter("user_name")==null) {
			throw new IllegalStateException("fill all the fields");
			
		}
		first_name=request.getParameter("first_name");
		last_name=request.getParameter("last_name");
		role_id=Integer.parseInt(request.getParameter("role_id"));
		RoleModel role=roleDao.getById(role_id);
		email=request.getParameter("email");
		password=request.getParameter("password");
		user_name=request.getParameter("username");
		EmployeeModel employee=new EmployeeModel(first_name, last_name, email, password, user_name,role);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
		
		
	}

	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Set<EmployeeModel> employees=employeeDao.getEmployees();
		request.setAttribute("listEmployee", employees);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
