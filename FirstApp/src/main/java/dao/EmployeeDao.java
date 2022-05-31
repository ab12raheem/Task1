package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import example.model.EmployeeModel;
import example.model.RoleModel;

public class EmployeeDao {
	private MyConnection myConnection = MyConnection.getInstance();
	private RoleDao roleDao=new RoleDao();
	public void addEmployee(EmployeeModel employee) {
		Connection conn=myConnection.getConnection();
		Statement statement;
		try {
			String z="select * from employee where email='"+employee.getEmail()+"';";
			String s="select * from employee where  user_name='"+employee.getUserName()+"' ;";
			statement = conn.createStatement();
			ResultSet rs= statement.executeQuery(z);
			if(rs.next()) {
				 throw new IllegalStateException("email used before");
				
			}
			rs=statement.executeQuery(s);
			if(rs.next()) {
				 throw new IllegalStateException("userName used before");
				
			}
			
			statement.executeUpdate("INSERT INTO employee"
					+ " (id,first_name, last_name, role_id,email, password, user_name) " 
					+ "VALUES (DEFAULT, '"+employee.getFirstName()+"',"
							+ " '"+employee.getLastName()+"', "+employee.getRole().getId()+",'"+employee.getEmail()+"',"
									+ "'"+employee.getPassword()+"','"+employee.getUserName()+"')");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// insert the data
		
	}
	public Set<EmployeeModel> getTeam(Integer id) throws SQLException{
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		 String z="select * from employee where id in(select employee2_id from employee_enrolled where employee_id="+id+") ;";
		 
		 ResultSet rs=statement.executeQuery(z);
		 
		 Set<EmployeeModel> employees=new HashSet();
		
		
		 
		 
		 while(rs.next()) {
			 EmployeeModel employee=new EmployeeModel();
			 employee.setId(rs.getInt("id"));
			 employee.setFirstName(rs.getString("first_name"));
			 employee.setLastName(rs.getString("last_name"));
			 employee.setEmail(rs.getString("email"));
			 employee.setPassword(rs.getString("password"));
			 RoleModel role = roleDao.getById(rs.getInt("role_id"));
			 employee.setRole(role);
			 employee.setUserName(rs.getString("user_name"));
			 
			 employees.add(employee);
			 
			 
		 }
		 if(employees.isEmpty()) {
			 throw new IllegalStateException("employees not found");
		 }
		
		conn.close();
		return employees;
	}
	public Set<EmployeeModel> getEmployees() throws SQLException{
		
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		 String z="select * from employee;";
		 ResultSet rs=statement.executeQuery(z);
		 Set<EmployeeModel> employees=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 EmployeeModel employee=new EmployeeModel();
			 
			 employee.setId(rs.getInt("id"));
			 employee.setFirstName(rs.getString("first_name"));
			 employee.setLastName(rs.getString("last_name"));
			 employee.setEmail(rs.getString("email"));
			 employee.setPassword(rs.getString("password"));
			 RoleModel role = roleDao.getById(rs.getInt("role_id"));
			 employee.setRole(role);
			 employee.setUserName(rs.getString("user_name"));
			 
			 employees.add(employee);
			 
			 
		 }
		 if(employees.isEmpty()) {
			 throw new IllegalStateException("employees not found");
		 }
		
		conn.close();
		return employees;
	}
	
public Set<EmployeeModel> getEmployeesByRole(String roleName) throws SQLException{
		RoleModel role =roleDao.getByName(roleName);
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		 String z="select * from employee where role_id="+role.getId()+";";
		 ResultSet rs=statement.executeQuery(z);
		 Set<EmployeeModel> employees=new HashSet();
		 
		 
		 
		 while(rs.next()) {
			 EmployeeModel employee=new EmployeeModel();
			 
			 employee.setId(rs.getInt("id"));
			 employee.setFirstName(rs.getString("first_name"));
			 employee.setLastName(rs.getString("last_name"));
			 employee.setEmail(rs.getString("email"));
			 employee.setPassword(rs.getString("password"));
			 employee.setRole(role);
			 employee.setUserName(rs.getString("user_name"));
			 
			 employees.add(employee);
			 
			 
		 }
		 if(employees.isEmpty()) {
			 throw new IllegalStateException("employees not found");
		 }
		
		conn.close();
		return employees;
	}
	
	
	
	public EmployeeModel getByUserName(String userName) throws SQLException {
		Connection conn=myConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		 String z="select * from employee where user_name='"+userName+"';";
		 ResultSet rs=statement.executeQuery(z);
		
		 
		 EmployeeModel employee=new EmployeeModel();
		 
		if(rs.next()) {
			 
			 
			 employee.setId(rs.getInt("id"));
			 employee.setFirstName(rs.getString("first_name"));
			 employee.setLastName(rs.getString("last_name"));
			 employee.setEmail(rs.getString("email"));
			 employee.setPassword(rs.getString("password"));
			 RoleModel role = roleDao.getById(rs.getInt("role_id"));
			 employee.setRole(role);
			 employee.setUserName(rs.getString("user_name")); 
			 conn.close();
				return employee;
			 
		 }
		else throw new IllegalStateException("employee not found");
		
	}
	public void deleteEmployee(String userName) {
		
		
		
	}
	public void updateEmloyee(EmployeeModel employee,String userName) {
		Connection conn=myConnection.getConnection();
		Statement statement;
		try {
			
			
			String s="select * from employee where  user_name='"+userName+"' ;";
			statement = conn.createStatement();
			ResultSet rs= statement.executeQuery(s);
			if(!rs.next()) {
				 throw new IllegalStateException("user not found");
				
			}
			s="UPDATE employee SET first_name ='"+employee.getFirstName()+"', "
					+ "last_name='"+employee.getLastName()+"',"
							+ "role_id="+employee.getRole().getId()+","
									+ "email='"+employee.getEmail()+"',"
											+ "password='"+employee.getPassword()+"',"
													+ "user_name='"+employee.getUserName()+"' WHERE user_name='"+userName+"'";
		
			
			statement.executeUpdate(s);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
