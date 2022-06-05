package example.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDao;
import dao.RoleDao;
import example.model.EmployeeModel;
import example.model.RoleModel;

/**
 * Servlet implementation class Login
 */

public class Login extends Action {

	private EmployeeDao employeeDao = new EmployeeDao();
	private RoleDao roleDao = new RoleDao();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		try {
			EmployeeModel employee = employeeDao.getByUserName(userName);
			if (password.equals(employee.getPassword())) {

				RoleModel role = employee.getRole();
				switch (role.getName()) {
				case "Manager":
					return mapping.findForward("Admin");

				case "TeamLeader":
					return mapping.findForward("Leader");

				case "Developer":

					return mapping.findForward("Developer");

				default:
					throw new IllegalStateException("this user has no role");

				}

			} else {
				return mapping.findForward("failure");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
