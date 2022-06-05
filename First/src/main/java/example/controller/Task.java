package example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.TaskDao;
import example.model.TaskModel;

/**
 * Servlet implementation class Task
 */

public class Task extends Action {
	
	private TaskDao taskDao = new TaskDao();
	private TaskModel taskModel = new TaskModel();



	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/TaskShowEditForm.do":
				request=showEditForm(mapping,form,request,response);
				return mapping.findForward("Admin");

			case "/addTask.do":
				request=insertTask(mapping,form,request,response);
				return mapping.findForward("Admin");
				

			case "/deleteTask.do":
				request=deleteTask(mapping,form,request,response);
				return mapping.findForward("Admin");

			case "/updateTask.do":
				request=updateTask(mapping,form,request,response);
				return mapping.findForward("Admin");
			case "/getTask.do":
				request=getTaskById(mapping,form,request,response);
				return mapping.findForward("Admin");
			case "/getTasksByStatus.do":
				request=getTaskByStatus(mapping,form,request,response);
				return mapping.findForward("Admin");
			case "/addTaskToEmployee.do":
				request=addTaskToEmployee(mapping,form,request,response);
				return mapping.findForward("Admin");
			case "/getTasksByEmployee.do":
				request=getTaskByEmployee(mapping,form,request,response);
				return mapping.findForward("Admin");
			default:
				request=listTask(mapping,form,request,response);
				return mapping.findForward("Admin");
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		// TODO Auto-generated method stub
		
	}


	private HttpServletRequest  showEditForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		taskModel=taskDao.getById(id);
		request.setAttribute("task", taskModel);
		return request;
		
	}

	private HttpServletRequest getTaskByEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("userName") == null) {
			throw new IllegalStateException("null");

		}
		Set<TaskModel> tasks=taskDao.getTaskByEmployee(request.getParameter("userName") );
		request.setAttribute("listTask", tasks);
		return request;
		
		
	}

	private HttpServletRequest addTaskToEmployee(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("id") == null||request.getParameter("userName") == null) {
			throw new IllegalStateException("null");

		}
		Integer id =Integer.parseInt(request.getParameter("id"));
		String userName=request.getParameter("userName");
		

		
		taskDao.assignTaskToEmployee(id, userName);
		return request;
		
	}

	private HttpServletRequest getTaskByStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("status") == null) {
			throw new IllegalStateException("status is null");

		}
		Set<TaskModel> tasks=taskDao.getTaskByStatus(request.getParameter("status") );
		request.setAttribute("listTask", tasks);
		return request;
	}

	private HttpServletRequest getTaskById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws NumberFormatException, SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("id") == null) {
			throw new IllegalStateException("id is null");

		}
		taskModel = taskDao.getById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("task", taskModel);
		return request;
	}

	private HttpServletRequest updateTask(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		
		if (request.getParameter("description") == null || request.getParameter("status") == null
				|| request.getParameter("attachment") == null || request.getParameter("id") == null) {
			String errorMessage = "you shoud fill al the boxes";
			request.setAttribute("erorrMessage", errorMessage);
			return request;
		}
		Integer id = Integer.parseInt(request.getParameter("id"));
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String attachment = request.getParameter("attachment");
		taskModel.setAttachment(attachment);
		taskModel.setDescription(description);
		taskModel.setStatus(status);
		taskDao.updateTask(taskModel, id);

		return request;

	}

	private HttpServletRequest deleteTask(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		if (request.getParameter("id") == null) {
			throw new IllegalStateException("id is null");

		}
		taskDao.deleteTask(Integer.parseInt(request.getParameter("id")));
		return request;

	}

	private HttpServletRequest insertTask(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		if (request.getParameter("description") == null || request.getParameter("status") == null
				|| request.getParameter("attachment") == null) {
			/*String errorMessage = "you shoud fill al the boxes";
			request.setAttribute("erorrMessage", errorMessage);
			dispatcher.forward(request, response);*/
			throw new IllegalStateException("id is null");
		}
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String attachment = request.getParameter("attachment");
		taskModel.setAttachment(attachment);
		taskModel.setDescription(description);
		taskModel.setStatus(status);
		taskDao.addTask(taskModel);

		return request;

	}

	private HttpServletRequest listTask(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		Set<TaskModel> listTask = taskDao.getTasks();
		request.setAttribute("listTask", listTask);
		return request;
	}

}
