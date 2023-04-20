package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con=null;
	public static ConnectDB instance=new ConnectDB();
	public static ConnectDB getInstance() {
		return instance;
	}
	
	
	public void Connect() throws SQLException{
		String url="jdbc:sqlserver://localhost:1433;databasename=qlsv";
		String user="sa";
		String password="12345";
		con=DriverManager.getConnection(url, user, password);
		
		
	}
	public void disconnect() {
		if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
	}
	public static Connection getConnection() {
		return con;
	}

}
