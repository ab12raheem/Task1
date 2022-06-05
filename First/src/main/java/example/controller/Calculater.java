package example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.model.CalcModel;
import example.model.EmployeeModel;
import example.model.RoleModel;
import example.model.TaskModel;
import example.model.TeamModel;

import javax.sql.*;

import dao.EmployeeDao;
import dao.TaskDao;

import java.sql.*;




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
		RoleModel role = new RoleModel();
		role.setId(1);
		EmployeeModel employee=new EmployeeModel("hala", "zreak","zreak@Hotmail.com",
				 "123456", "zreak",
				role);
		
				/*Set<EmployeeModel> employees;
		 * 
		 * 
		 */
		TaskModel task= new TaskModel( "graduating project","done", "22.txt");
		TaskDao taskDao=new TaskDao();
		//EmployeeDao ee= new EmployeeDao();
		
		try {
			EmployeeDao ee= new EmployeeDao();
			//ee.addEmployee(employee);

			
			//EmployeeModel emplo=ee.getByUserName("ali");
			//out.append("<div>"+emplo.toString()+"</div>");
			//taskDao.addTask(task);
			//taskDao.assignTaskToEmployee(5, "zreak");
			TaskModel getTask=taskDao.getById(5);
			/*Set<TaskModel> byEmployee=taskDao.getTaskByEmployee("ali");
			Set<TaskModel> taskDone=taskDao.getTaskDone();
			Set<TaskModel> byEmployeeDone=taskDao.getTaskDoneByEmployee("ali");
			Set<TaskModel> byEmployeeProgress=taskDao.getTaskInProgressByEmployee("ali");
			Set<TaskModel> inProgress=taskDao.getTaskInProgress();
			Set<TaskModel> all=taskDao.getTasks();
			//employees = ee.getTeam(2);
			for(TaskModel taskModel:byEmployee) {
				
				out.append("<div> byEmployee:::"+taskModel.toString()+"</div>");
			}
			
for(TaskModel taskModel:taskDone) {
				
				out.append("<div> taskDone:::"+taskModel.toString()+"</div>");
			}
for(TaskModel taskModel:byEmployeeDone) {
	
	out.append("<div> byEmployeeDone:::"+taskModel.toString()+"</div>");
}

for(TaskModel taskModel:byEmployeeProgress) {
	
	out.append("<div> byEmployeeProgress:::"+taskModel.toString()+"</div>");
}

for(TaskModel taskModel:inProgress) {
	
	out.append("<div> inProgress:::"+taskModel.toString()+"</div>");
}

for(TaskModel taskModel:all) {
	
	out.append("<div> all:::"+taskModel.toString()+"</div>");
}*/
out.append("<div> congratulations hala :"+getTask.getDescription()+"</div>");
out.append("<div>"+getTask.getStatus()+"</div>");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			Context ctx = new InitialContext();
			 DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/postgres");
			 System.out.print(ds.toString());
			 Connection conn = ds.getConnection();
			 Statement stmt = conn.createStatement();
             ResultSet rst = stmt.executeQuery("select * from public.task ;");
             if(rst.next()) {
         	    out.print("<span>"+rst.getString("description")+"</span>");
         	    }
             conn.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*String url,user,passwordu,z;
        url="jdbc:postgresql://localhost:5432/EmployeeManagment";
     user="postgres";
    passwordu="123456";
    Connection con;
   
	try {
        Class.forName("org.postgresql.Driver");

		con = DriverManager.getConnection(url, user, passwordu);
		z="select * from public.task ;";
		Statement stmt=con.createStatement();
	    ResultSet rs= stmt.executeQuery(z);
	    if(rs.next()) {
	    out.print("<span>"+rs.getString("description")+"</span>");
	    }
	    con.close();
	}catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

		//JOptionPane.showMessageDialog(null,"user name existed");
		

   }*/ 
		
	/*
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
				  double z= calcModel.calc(x, y);
				  HttpSession session=request.getSession(false);
				  if(session==null) {
					  session=request.getSession();
					  session.putValue("history", String.format("%f", z)+"\n");
					  
					  
				  }else {
					  out.append("<span>"+session.getValue("history")+"</span><br>");
					  String s=(String)session.getValue("history");
					  
					  s=s+String.format(
			                   "%f", z)+"\n";
					  
					  
					  
					  session.putValue("history", s);
				  }
				 
				  
				  out.append("<span>"+z+"</span>");
				  
				 
				 
			 }
			 catch(NumberFormatException e) {
				 out.println("<br> invalid inputs " );
				 
			 }
		 }*/
		 
		 
	}

}
