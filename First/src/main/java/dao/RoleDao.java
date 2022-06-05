package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import example.model.EmployeeModel;
import example.model.RoleModel;

public class RoleDao {
	
	public Set<RoleModel>getRoles() throws SQLException{
		Connection conn=MyConnection.getConnection();
		Statement statement;
		statement = conn.createStatement();
		 String z="select * from role;";
		 ResultSet rs=statement.executeQuery(z);
		 Set<RoleModel> roles=new HashSet();
		 

		 
		 
		 while(rs.next()) {
			 RoleModel role = new RoleModel();
			 
			 role.setId(rs.getInt("id"));
			 role.setName(rs.getString("name"));
			 
			 
			 roles.add(role);
			 
			 
		 }
		 if(roles.isEmpty()) {
			 throw new IllegalStateException("no roles found");
		 }
		
		conn.close();
		return roles;
	}
	public void addRole(RoleModel role) {
		Connection conn=MyConnection.getConnection();
		Statement statement;
		try {
			String z="select * from role where name='"+role.getName()+"';";
			
			statement = conn.createStatement();
			ResultSet rs= statement.executeQuery(z);
			if(rs.next()) {
				 throw new IllegalStateException("name used before");
				
			}
		
			
			statement.executeUpdate("INSERT INTO employee"
					+ " (id,name) " 
					+ "VALUES (DEFAULT, '"+role.getName()+"')");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public RoleModel getByName(String name) throws SQLException {
		Connection conn=MyConnection.getConnection();
		Statement statement;
		
			String z="select * from role where name='"+name+"';";
			
			statement = conn.createStatement();
			ResultSet rs= statement.executeQuery(z);
			if(rs.next()) {
				RoleModel role = new RoleModel();
				role.setId(rs.getInt("id"));
				role.setName(name);
				conn.close();
				return role;
				
				 
				
			}
			
			else throw new IllegalStateException("role not found");
		

			
	
	}
	public RoleModel getById(Integer id) throws SQLException {
		Connection conn=MyConnection.getConnection();
		Statement statement;
		
			String z="select * from role where id='"+id+"';";
			
			statement = conn.createStatement();
			ResultSet rs= statement.executeQuery(z);
			if(rs.next()) {
				RoleModel role = new RoleModel();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				conn.close();
				return role;
				
				 
				
			}
			
			else throw new IllegalStateException("role not found");
		

			
	
	}
	
	

}
