package example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import dao.RoleDao;
import example.model.EmployeeModel;
import example.model.RoleModel;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Employee extends Action {

	private EmployeeDao employeeDao = new EmployeeDao();
	private RoleDao roleDao = new RoleDao();
	String first_name, last_name, email, password, user_name;
	Integer id, role_id;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = request.getServletPath();
		try {
			switch (action) {
			/*
			 * case "/editEmployee": showEditForm(request,response); break;
			 */
			case "/addEmployee.do":
				request = insertEmployee(mapping, form, request, response);
				return mapping.findForward("Admin");

			case "/deleteEmployee.do":
				request = deleteEmployee(mapping, form, request, response);
				return mapping.findForward("Admin");

			/*
			 * case "/updateEmployee": updateEmployee(mapping,form,request, response);
			 * break;
			 */
			case "/getEmployee.do":
				request = getEmployeeByUserName(mapping, form, request, response);
				return mapping.findForward("Admin");
			case "/getTeam.do":
				request = getTeam(mapping, form, request, response);
				return mapping.findForward("Admin");

			default:
				request = listEmployees(mapping, form, request, response);
				return mapping.findForward("Admin");
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

		// TODO Auto-generated method stub

	}

	private HttpServletRequest getTeam(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getParameter("id") == null) {
			throw new IllegalStateException("id is null");
		}
		id = Integer.parseInt(request.getParameter("id"));
		Set<EmployeeModel> team = employeeDao.getTeam(id);
		request.setAttribute("team", team);
		return request;

	}

	private HttpServletRequest getEmployeeByUserName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("user_name") == null) {
			throw new IllegalStateException("fill all the fields");
		}
		EmployeeModel employee = employeeDao.getByUserName(request.getParameter("user_name"));
		request.setAttribute("employee", employee);
		return request;
	}

	private HttpServletRequest deleteEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		if (request.getParameter("user_name") == null) {
			throw new IllegalStateException("fill all the fields");
		}

		employeeDao.deleteEmployee(request.getParameter("user_name"));
		return request;

	}

	private HttpServletRequest insertEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getParameter("first_name") == null || request.getParameter("last_name") == null
				|| request.getParameter("role_id") == null || request.getParameter("email") == null
				|| request.getParameter("password") == null || request.getParameter("user_name") == null) {
			throw new IllegalStateException("fill all the fields");

		}
		first_name = request.getParameter("first_name");
		last_name = request.getParameter("last_name");
		role_id = Integer.parseInt(request.getParameter("role_id"));
		RoleModel role = roleDao.getById(role_id);
		email = request.getParameter("email");
		password = request.getParameter("password");
		user_name = request.getParameter("username");
		EmployeeModel employee = new EmployeeModel(first_name, last_name, email, password, user_name, role);
		return request;

	}

	private HttpServletRequest listEmployees(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Set<EmployeeModel> employees = employeeDao.getEmployees();
		request.setAttribute("listEmployee", employees);
		return request;

	}

}
