package nodomain.akd.interDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class LoginDB {
	
	private static LoginDB instance = null;
	Connection connection = null;
	Statement statement = null;
	
	MessageDigest md = null;
	
	public LoginDB() {
		instance = this;
		//createNewDatabase();
        try
        {
          // create a database connection
        	connection = DriverManager.getConnection("jdbc:sqlite:plugins/AkdLogin/login.db");
        	statement = connection.createStatement();
        	statement.setQueryTimeout(30);  // set timeout to 30 sec.
        	statement.execute("CREATE TABLE IF NOT EXISTS userData ( nick TEXT PRIMARY KEY, pwd TEXT NOT NULL );");
//
//          statement.executeUpdate("drop table if exists person");
//          statement.executeUpdate("create table person (id integer, name string)");
//          statement.executeUpdate("insert into person values(1, 'leo')");
//          statement.executeUpdate("insert into person values(2, 'yui')");
//          ResultSet rs = statement.executeQuery("select * from person");
        }
        catch(SQLException e)
        {
          System.err.println("Все пошло по пизде");
        }
        
        try {
        	md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
	}
	
	public boolean userExists(String name) {
		if (connection != null && statement != null) {
			try {
				System.out.println("[AL.LoginDB] Seek started.");
				String request = "SELECT COUNT (nick) FROM userData WHERE nick = \"" + name + "\";";
				if (statement.executeQuery(request).getInt(1) == 1) {
					
					return true;
				}
				else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Что то пошло по пизде .-.");
			}
		}
		return false;
		
		
	}
	
	public void addUser(String name, String pwd) {
		try {
			statement.executeUpdate("insert into userData values(\"" + name + "\", \"" + pwd + "\")");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Что то пошло по пизде .-.");
		}
	}
	
	
	
	
	
	public boolean checkPassword(String name, String pwd) {
		String request = "SELECT (pwd) FROM userData WHERE nick = \"" + name + "\";";
		try {
			if (statement.executeQuery(request).getString(1).equals(pwd)) {
				return true;
			}
			else {
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Что то пошло по пизде .-.");
		}
		return false;
	}
	
	public String hash(String input) {
		byte[] bytesOfMessage = null;
		try {
			bytesOfMessage = input.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] theMD5digest = md.digest(bytesOfMessage);
		return theMD5digest.toString();
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static LoginDB getInstance() { return instance; }
	
	
	//unused
	public void setLogId(String name, int logid) {
		try {
			statement.executeUpdate("insert into userData values(\"" + name + "\", \"" + logid + "\")");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Что то пошло по пизде .-.");
		}
	}
	
	public int getLogId(String name) {
		String request = "SELECT (logid) FROM userData WHERE nick = \"" + name + "\";";
		try {
			return statement.executeQuery(request).getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
		public static void createNewDatabase() {

	        String url = "jdbc:sqlite:plugins/AkdLogin/login.db";

	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
}
