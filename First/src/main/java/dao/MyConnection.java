package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

public class MyConnection {
	private static MyConnection single_instance = new MyConnection();
	private Context ctx;
	private static Connection conn ;
	private static DataSource ds;
	 
    // Declaring a variable of type String
    public String s;
 
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private MyConnection()
    {
    	try {
			ctx = new InitialContext();
			 ds = (DataSource)ctx.lookup("java:comp/env/jdbc/postgres");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
    }

    /*private MyConnection getInstance()
    {
        
 
        return single_instance;
    }*/
    public static Connection getConnection() {
    	try {
    		if (single_instance == null)
                single_instance = new MyConnection();
    		
			conn = ds.getConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return conn;
    }

	
    
}


