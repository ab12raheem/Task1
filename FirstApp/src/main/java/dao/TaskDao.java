package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import example.model.EmployeeModel;
import example.model.RoleModel;
import example.model.TaskModel;

public class TaskDao {
	
	private final EmployeeDao employeeDao = new EmployeeDao();
	private PreparedStatement statement;
	private final String ID="id",ATTACHMENT="attachment",DESCRIPTION="description",STATUS="status";
	public TaskModel mapRecordToTask(Integer id, String attachment , String description , String status) {
		TaskModel task=new TaskModel();
		 
		 task.setId(id);
		 task.setAttachment(attachment);
		 task.setDescription(description);
		 task.setStatus(status);
		 return task;
	}
	
	public Set<TaskModel> getTasks() throws SQLException{
		Connection conn=MyConnection.getConnection();
		String z="select * from task;";
		statement = conn.prepareStatement(z);
		 
		 ResultSet rs=statement.executeQuery();
		 Set<TaskModel> tasks=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 TaskModel task=mapRecordToTask(rs.getInt(ID), rs.getString(ATTACHMENT),rs.getString(DESCRIPTION),rs.getString(STATUS));
				 
			 tasks.add(task);
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		

		return tasks;
		
		
		
		
	}
	public Set<TaskModel> getTaskByEmployee(String userName) throws SQLException{
		EmployeeModel employee=employeeDao.getByUserName(userName);
		Connection conn=MyConnection.getConnection();
		
		
		 String z="select * from task where id in(select task_id from task_enrolled where employee_id=?)";
		 
		 statement = conn.prepareStatement(z);
		 statement.setInt(1, employee.getId());
		 ResultSet rs=statement.executeQuery();
		 Set<TaskModel> tasks=new HashSet();
		 while(rs.next()) {
			 TaskModel task=mapRecordToTask(rs.getInt(ID), rs.getString(ATTACHMENT),rs.getString(DESCRIPTION),rs.getString(STATUS));
			 
			 tasks.add(task);
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		

		return tasks;
	}
	
	
	/*public Set<TaskModel> getTaskInProgress() throws SQLException{
		Connection conn=MyConnection.getConnection();

		
		String status="inProgress";
		 String z="select * from task where status=?";
		 statement = conn.prepareStatement(z);
		 statement.setString(1, status);
		 ResultSet rs=statement.executeQuery();
		 Set<TaskModel> tasks=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 TaskModel task=mapRecordToTask(rs.getInt(ID), rs.getString(ATTACHMENT),rs.getString(DESCRIPTION),rs.getString(STATUS));
			 
			 tasks.add(task);
			 
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		
		
		return tasks;
		
		
	}
	public Set<TaskModel>getTaskDone() throws SQLException{
		Connection conn=MyConnection.getConnection();
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
		
		
		
		
	}*/
	
	public Set<TaskModel> getTaskByStatus(String status) throws SQLException{
	Connection conn=MyConnection.getConnection();
	 String z="select * from task where status=?";
	 statement = conn.prepareStatement(z);
	 statement.setString(1, status);
	 ResultSet rs=statement.executeQuery();
	 Set<TaskModel> tasks=new HashSet();
	
	 
	 
	 while(rs.next()) {
		 TaskModel task=mapRecordToTask(rs.getInt(ID), rs.getString(ATTACHMENT),rs.getString(DESCRIPTION),rs.getString(STATUS));
		 
		 tasks.add(task);
		 
		 
		 
	 }
	 if(tasks.isEmpty()) {
		 throw new IllegalStateException("tasks not found");
	 }
	
	
	return tasks;
	
	
}
	
	
	
	
	public Set<TaskModel> getTaskByStatusAndEmployee(String userName,String status) throws SQLException{
		EmployeeModel employee=employeeDao.getByUserName(userName);
		Connection conn=MyConnection.getConnection();
	
		
	
		 String z="select * from task where status=? and id in(select task_id from task_enrolled where employee_id=?) ";
		 statement = conn.prepareStatement(z);
		 statement.setString(1, status);
		 statement.setInt(2, employee.getId());
		 ResultSet rs=statement.executeQuery();
		 Set<TaskModel> tasks=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 TaskModel task=mapRecordToTask(rs.getInt(ID), rs.getString(ATTACHMENT),rs.getString(DESCRIPTION),rs.getString(STATUS));
			 
			 tasks.add(task);
			 
			 
		 }
		 if(tasks.isEmpty()) {
			 throw new IllegalStateException("tasks not found");
		 }
		

		return tasks;
	}
	/*public Set<TaskModel>getTaskDoneByEmployee(String userName) throws SQLException{
		EmployeeModel employee=employeeDao.getByUserName(userName);
		Connection conn=MyConnection.getConnection();
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
	}*/
	public TaskModel getById(Integer id) throws SQLException {
		Connection conn=MyConnection.getConnection();
		
		
		 String z="select * from task where id=?";
		 statement = conn.prepareStatement(z);
		 statement.setInt(1, id);
		 ResultSet rs=statement.executeQuery();
		
		 
		 TaskModel task=new TaskModel();
		 
		if(rs.next()) {
			 
			task=mapRecordToTask(rs.getInt(ID), rs.getString(ATTACHMENT),rs.getString(DESCRIPTION),rs.getString(STATUS));
				return task;
			 
		 }
		else throw new IllegalStateException("task not found");
	}
	public void addTask (TaskModel task) throws SQLException {
		
		Connection conn=MyConnection.getConnection();
		String z="INSERT INTO task"
				+ " (id,description, attachment, status) " 
				+ "VALUES (DEFAULT,?,?,?)";
		statement=conn.prepareStatement(z);
		statement.setString(1, task.getDescription());
		statement.setString(2, task.getAttachment());
		statement.setString(3, task.getStatus());
		statement.executeUpdate();
		
		
	}
	public void assignTaskToEmployee(Integer id , String userName)throws SQLException  {
		EmployeeModel employee=employeeDao.getByUserName(userName);
		TaskModel task=getById(id);
		Connection conn=MyConnection.getConnection();
		String z="INSERT INTO task_enrolled"
				+ " (id,task_id, employee_id) " 
				+ "VALUES (DEFAULT,?,?)";

		statement = conn.prepareStatement(z);
		statement.setInt(1, task.getId());
		statement.setInt(1, employee.getId());
		statement.executeUpdate();
	}
	public void deleteTask(Integer id) {
		
	}
	public void updateTask(TaskModel task,Integer id) throws SQLException {
		TaskModel task1=getById(id);
		Connection conn=MyConnection.getConnection();
		
		
		String s="UPDATE task SET description =?, "
				+ "attachment=?,"
						+ "status=? WHERE id=?";
		statement = conn.prepareStatement(s);
		statement.setString(1,task.getDescription());
		statement.setString(2,task.getAttachment());
		statement.setString(3,task.getStatus());
		statement.setInt(4,id);
	
		
		statement.executeUpdate(s);
	
		
		
	}

}
