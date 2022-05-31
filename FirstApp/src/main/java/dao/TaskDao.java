package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import example.model.EmployeeModel;
import example.model.RoleModel;
import example.model.TaskModel;

public class TaskDao {
	private MyConnection myConnection = MyConnection.getInstance();
	private final EmployeeDao employeeDao = new EmployeeDao();
	
	public Set<TaskModel> getTasks() throws SQLException{
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		 String z="select * from task;";
		 ResultSet rs=statement.executeQuery(z);
		 Set<TaskModel> tasks=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 TaskModel task=new TaskModel();
			 
			 task.setId(rs.getInt("id"));
			 task.setAttachment(rs.getString("attachment"));
			 task.setDescription(rs.getString("description"));
			 task.setStatus(rs.getString("status"));
			
			 
			 tasks.add(task);
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		
		conn.close();
		return tasks;
		
		
		
		
	}
	public Set<TaskModel> getTaskByEmployee(String userName) throws SQLException{
		EmployeeModel employee=employeeDao.getByUserName(userName);
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		 String z="select * from task where id in(select task_id from task_enrolled where employee_id="+employee.getId()+");";
		 ResultSet rs=statement.executeQuery(z);
		 Set<TaskModel> tasks=new HashSet();
		 while(rs.next()) {
			 TaskModel task=new TaskModel();
			 
			 task.setId(rs.getInt("id"));
			 task.setAttachment(rs.getString("attachment"));
			 task.setDescription(rs.getString("description"));
			 task.setStatus(rs.getString("status"));
			
			 
			 tasks.add(task);
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		
		conn.close();
		return tasks;
	}
	
	
	public Set<TaskModel> getTaskInProgress() throws SQLException{
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		String status="inProgress";
		 String z="select * from task where status='"+status+"';";
		 ResultSet rs=statement.executeQuery(z);
		 Set<TaskModel> tasks=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 TaskModel task=new TaskModel();
			 
			 task.setId(rs.getInt("id"));
			 task.setAttachment(rs.getString("attachment"));
			 task.setDescription(rs.getString("description"));
			 task.setStatus(rs.getString("status"));
			
			 
			 tasks.add(task);
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		
		conn.close();
		return tasks;
		
		
	}
	public Set<TaskModel>getTaskDone() throws SQLException{
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		String status="done";
		 String z="select * from task where status='"+status+"';";
		 ResultSet rs=statement.executeQuery(z);
		 Set<TaskModel> tasks=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 TaskModel task=new TaskModel();
			 
			 task.setId(rs.getInt("id"));
			 task.setAttachment(rs.getString("attachment"));
			 task.setDescription(rs.getString("description"));
			 task.setStatus(rs.getString("status"));
			
			 
			 tasks.add(task);
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		
		conn.close();
		return tasks;
		
		
		
		
	}
	public Set<TaskModel> getTaskInProgressByEmployee(String userName) throws SQLException{
		EmployeeModel employee=employeeDao.getByUserName(userName);
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		String status="inProgress";
		 String z="select * from task where status='"+status+"' and id in(select task_id from task_enrolled where employee_id="+employee.getId()+") ;";
		 ResultSet rs=statement.executeQuery(z);
		 Set<TaskModel> tasks=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 TaskModel task=new TaskModel();
			 
			 task.setId(rs.getInt("id"));
			 task.setAttachment(rs.getString("attachment"));
			 task.setDescription(rs.getString("description"));
			 task.setStatus(rs.getString("status"));
			
			 
			 tasks.add(task);
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		
		conn.close();
		return tasks;
	}
	public Set<TaskModel>getTaskDoneByEmployee(String userName) throws SQLException{
		EmployeeModel employee=employeeDao.getByUserName(userName);
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		String status="done";
		 String z="select * from task where status='"+status+"' and id in(select task_id from task_enrolled where employee_id="+employee.getId()+") ;";
		 ResultSet rs=statement.executeQuery(z);
		 Set<TaskModel> tasks=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 TaskModel task=new TaskModel();
			 
			 task.setId(rs.getInt("id"));
			 task.setAttachment(rs.getString("attachment"));
			 task.setDescription(rs.getString("description"));
			 task.setStatus(rs.getString("status"));
			
			 
			 tasks.add(task);
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		
		conn.close();
		return tasks;
	}
	public TaskModel getById(Integer id) throws SQLException {
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		 String z="select * from task where id="+id+";";
		 ResultSet rs=statement.executeQuery(z);
		
		 
		 TaskModel task=new TaskModel();
		 
		if(rs.next()) {
			 
			task.setId(rs.getInt("id"));
			 task.setAttachment(rs.getString("attachment"));
			 task.setDescription(rs.getString("description"));
			 task.setStatus(rs.getString("status"));
				return task;
			 
		 }
		else throw new IllegalStateException("task not found");
	}
	public void addTask (TaskModel task) throws SQLException {
		
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		statement.executeUpdate("INSERT INTO task"
				+ " (id,description, attachment, status) " 
				+ "VALUES (DEFAULT, '"+task.getDescription()+"',"
						+ " '"+task.getAttachment()+"', '"+task.getStatus()+"')");
		
		conn.close();
		
		
		
		
	}
	public void assignTaskToEmployee(Integer id , String userName)throws SQLException  {
		EmployeeModel employee=employeeDao.getByUserName(userName);
		TaskModel task=getById(id);
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		statement.executeUpdate("INSERT INTO task_enrolled"
				+ " (id,task_id, employee_id) " 
				+ "VALUES (DEFAULT, "+task.getId()+","+employee.getId()+")");
	}
	public void deleteTask(Integer id) {
		
	}
	public void updateTask(TaskModel task,Integer id) throws SQLException {
		TaskModel task1=getById(id);
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		String s="UPDATE task SET description ='"+task.getDescription()+"', "
				+ "attachment='"+task.getAttachment()+"',"
						+ "status='"+task.getStatus()+"' WHERE id="+id+"";
	
		
		statement.executeUpdate(s);
		conn.close();
		
		
	}

}
