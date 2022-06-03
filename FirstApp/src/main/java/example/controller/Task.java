package example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.TaskDao;
import example.model.TaskModel;

/**
 * Servlet implementation class Task
 */

public class Task extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskDao taskDao = new TaskDao();
	private TaskModel taskModel = new TaskModel();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/TaskShowEditForm":
				showEditForm(request,response);
				break;

			case "/addTask":
				insertTask(request, response);
				break;

			case "/deleteTask":
				deleteTask(request, response);
				break;

			case "/updateTask":
				updateTask(request, response);
				break;
			case "/getTask":
				getTaskById(request, response);
				break;
			case "/getTasksByStatus":
				getTaskByStatus(request, response);
				break;	
			case "/addTaskToEmployee":
				addTaskToEmployee(request, response);
				break;	
			case "/getTasksByEmployee":
				getTaskByEmployee(request, response);
				break;	
			default:
				listTask(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		taskModel=taskDao.getById(id);
		request.setAttribute("task", taskModel);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
		
	}

	private void getTaskByEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("userName") == null) {
			throw new IllegalStateException("null");

		}
		Set<TaskModel> tasks=taskDao.getTaskByEmployee(request.getParameter("userName") );
		request.setAttribute("listTask", tasks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
		
		
	}

	private void addTaskToEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("id") == null||request.getParameter("userName") == null) {
			throw new IllegalStateException("null");

		}
		Integer id =Integer.parseInt(request.getParameter("id"));
		String userName=request.getParameter("userName");
		

		
		taskDao.assignTaskToEmployee(id, userName);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
		
	}

	private void getTaskByStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("status") == null) {
			throw new IllegalStateException("status is null");

		}
		Set<TaskModel> tasks=taskDao.getTaskByStatus(request.getParameter("status") );
		request.setAttribute("listTask", tasks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
	}

	private void getTaskById(HttpServletRequest request, HttpServletResponse response)
			throws NumberFormatException, SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("id") == null) {
			throw new IllegalStateException("id is null");

		}
		taskModel = taskDao.getById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("task", taskModel);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
	}

	private void updateTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		if (request.getParameter("description") == null || request.getParameter("status") == null
				|| request.getParameter("attachment") == null || request.getParameter("id") == null) {
			String errorMessage = "you shoud fill al the boxes";
			request.setAttribute("erorrMessage", errorMessage);
			dispatcher.forward(request, response);
		}
		Integer id = Integer.parseInt(request.getParameter("id"));
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String attachment = request.getParameter("attachment");
		taskModel.setAttachment(attachment);
		taskModel.setDescription(description);
		taskModel.setStatus(status);
		taskDao.updateTask(taskModel, id);

		dispatcher.forward(request, response);

	}

	private void deleteTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		if (request.getParameter("id") == null) {
			throw new IllegalStateException("id is null");

		}
		taskDao.deleteTask(Integer.parseInt(request.getParameter("id")));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);

	}

	private void insertTask(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
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

		dispatcher.forward(request, response);

	}

	private void listTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		Set<TaskModel> listTask = taskDao.getTasks();
		request.setAttribute("listTask", listTask);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
