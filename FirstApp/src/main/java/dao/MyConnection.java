package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

public class MyConnection {
	private static MyConnection single_instance = null;
	private Context ctx;
	private Connection conn;
	private DataSource ds;
	 
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

    public static MyConnection getInstance()
    {
        if (single_instance == null)
            single_instance = new MyConnection();
 
        return single_instance;
    }
    public Connection getConnection() {
    	try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return conn;
    }

	
    
}


