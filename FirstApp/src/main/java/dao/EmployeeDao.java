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

public class EmployeeDao {
	private final String ID="id",FIRST_NAME="first_name",lAST_NAME="last_name",ROLE_ID="role_id",
			EMAIL="email",PASSWORD="password",USER_NAME="user_name";
	private PreparedStatement statement;

	private RoleDao roleDao=new RoleDao();
	public EmployeeModel mapRecordToEmployee(Integer id,String firstName,String lastName,Integer roleId,String email,String  password,
			String userName) throws SQLException {
		EmployeeModel employee=new EmployeeModel();
		 employee.setId(id);
		 employee.setFirstName(firstName);
		 employee.setLastName(lastName);
		 employee.setEmail(email);
		 employee.setPassword(password);
		 RoleModel role = roleDao.getById(roleId);
		 employee.setRole(role);
		 employee.setUserName(userName);
		 return employee;
		
		
	}
	public Boolean emailUsedBefore(String email) throws SQLException {
		Connection conn=MyConnection.getConnection();
		String z="select * from employee where email=?";
		statement = conn.prepareStatement(z);
		statement.setString(1, email);
		ResultSet rs= statement.executeQuery();
		if(rs.next()) {
			 return true;
			
		}
		return false;
		
		
	}
	public Boolean userNameUsedBefore(String userName) throws SQLException {
		Connection conn=MyConnection.getConnection();
		String z="select * from employee where user_name=?";
		statement = conn.prepareStatement(z);
		statement.setString(1, userName);
		ResultSet rs= statement.executeQuery();
		if(rs.next()) {
			 return true;
			
		}
		return false;
		
		
	}
	
	public void addEmployee(EmployeeModel employee) {
		Connection conn=MyConnection.getConnection();
		
		try {
			Boolean used=userNameUsedBefore(employee.getUserName());
			if(used) {
				throw new IllegalStateException("user name used before");
			}
			used =emailUsedBefore(employee.getEmail());
			if(used) {
				throw new IllegalStateException("email used before");
			}
			statement = conn.prepareStatement("INSERT INTO employee"
					+ " (id,first_name, last_name, role_id,email, password, user_name) " 
					+ "VALUES (DEFAULT,?,?,?,?,?,?)");
			statement.setString(1,employee.getFirstName());
			statement.setString(2,employee.getLastName());
			statement.setInt(3,employee.getRole().getId());
			statement.setString(4,employee.getEmail());
			statement.setString(5,employee.getPassword());
			statement.setString(6,employee.getUserName());
			statement.executeUpdate();
			
			/*statement.executeUpdate("INSERT INTO employee"
					+ " (id,first_name, last_name, role_id,email, password, user_name) " 
					+ "VALUES (DEFAULT, '"+employee.getFirstName()+"',"
							+ " '"+employee.getLastName()+"', "+employee.getRole().getId()+",'"+employee.getEmail()+"',"
									+ "'"+employee.getPassword()+"','"+employee.getUserName()+"')");*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// insert the data
		
	}
	public Set<EmployeeModel> getTeam(Integer id) throws SQLException{
		Connection conn=MyConnection.getConnection();
		
		 String z="select * from employee where id in(select employee2_id from employee_enrolled where employee_id=?)";
		 
		 statement = conn.prepareStatement(z);
		 statement.setInt(1,id);
		 ResultSet rs=statement.executeQuery();
		 
		 Set<EmployeeModel> employees=new HashSet();
		
		
		 
		 
		 while(rs.next()) {
			 EmployeeModel employee=mapRecordToEmployee(rs.getInt(ID),rs.getString(FIRST_NAME),rs.getString(lAST_NAME)
					 ,rs.getInt(ROLE_ID),rs.getString(EMAIL),rs.getString(PASSWORD),
					 rs.getString(USER_NAME));
			 
			 employees.add(employee);
			 
			 
		 }
		 if(employees.isEmpty()) {
			 throw new IllegalStateException("employees not found");
		 }
		
	
		return employees;
	}
	public Set<EmployeeModel> getEmployees() throws SQLException{
		
		Connection conn=MyConnection.getConnection();
		String z="select * from employee";
	
		statement = conn.prepareStatement(z);
		 
		 ResultSet rs=statement.executeQuery();
		 Set<EmployeeModel> employees=new HashSet();
		
		 
		 
		 while(rs.next()) {
			 EmployeeModel employee=mapRecordToEmployee(rs.getInt(ID),rs.getString(FIRST_NAME),rs.getString(lAST_NAME)
					 ,rs.getInt(ROLE_ID),rs.getString(EMAIL),rs.getString(PASSWORD),
					 rs.getString(USER_NAME));
			 
			 employees.add(employee);
			 
		 }
		 if(employees.isEmpty()) {
			 throw new IllegalStateException("employees not found");
		 }
		

		return employees;
	}
	
public Set<EmployeeModel> getEmployeesByRole(String roleName) throws SQLException{
		RoleModel role =roleDao.getByName(roleName);
		Connection conn=MyConnection.getConnection();
		String z="select * from employee where role_id=?";
	
		statement = conn.prepareStatement(z);
		statement.setInt(1, role.getId());
		 
		 ResultSet rs=statement.executeQuery();
		 Set<EmployeeModel> employees=new HashSet();
		 
		 
		 
		 while(rs.next()) {
			 EmployeeModel employee=mapRecordToEmployee(rs.getInt(ID),rs.getString(FIRST_NAME),rs.getString(lAST_NAME)
					 ,rs.getInt(ROLE_ID),rs.getString(EMAIL),rs.getString(PASSWORD),
					 rs.getString(USER_NAME));
			 
			 employees.add(employee);
			 
			 
		 }
		 if(employees.isEmpty()) {
			 throw new IllegalStateException("employees not found");
		 }
		
		return employees;
	}
	
	
	
	public EmployeeModel getByUserName(String userName) throws SQLException {
		Connection conn=MyConnection.getConnection();
		String z="select * from employee where user_name=?";
		statement = conn.prepareStatement(z);
		statement.setString(1, userName);
		 
		 ResultSet rs=statement.executeQuery();
		
		 
		 EmployeeModel employee=new EmployeeModel();
		 
		if(rs.next()) {
			 
			 
		 employee=mapRecordToEmployee(rs.getInt(ID),rs.getString(FIRST_NAME),rs.getString(lAST_NAME)
					 ,rs.getInt(ROLE_ID),rs.getString(EMAIL),rs.getString(PASSWORD),
					 rs.getString(USER_NAME));
				return employee;
			 
		 }
		else throw new IllegalStateException("employee not found");
		
	}
	public void deleteEmployee(String userName) throws SQLException {
		Connection conn=MyConnection.getConnection();
		EmployeeModel employee=getByUserName(userName);
		String s= "delete from employee_enrolled where employee_id=? or employee2_id=? ";
		String z="delete from task_enrolled where employee_id=?";
		String x="delete from employee where id=?";
		statement=conn.prepareStatement(s);
		statement.setInt(1, employee.getId());
		statement.setInt(2, employee.getId());
		statement.executeUpdate();
		
		statement=conn.prepareStatement(z);
		statement.setInt(1, employee.getId());
		statement.executeUpdate();
		
		statement=conn.prepareStatement(x);
		statement.setInt(1, employee.getId());
		statement.executeUpdate();
		
	}
	public void updateEmloyee(EmployeeModel employee,String userName) {
		Connection conn=MyConnection.getConnection();
		
		try {
			
			
			Boolean used=userNameUsedBefore(userName);
			if(!used) {
				throw new IllegalStateException("user not found");
			}
			
	
			String s="UPDATE employee SET first_name =?, "
					+ "last_name=?,"
							+ "role_id=?,"
									+ "email=?,"
											+ "password=?,"
													+ "user_name=? WHERE user_name=?";
			statement=conn.prepareStatement(s);
			statement.setString(1,employee.getFirstName());
			statement.setString(2,employee.getLastName());
			statement.setInt(3,employee.getRole().getId());
			statement.setString(4,employee.getEmail());
			statement.setString(5,employee.getPassword());
			statement.setString(6,employee.getUserName());
			statement.setString(7,userName);
		
			statement.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
