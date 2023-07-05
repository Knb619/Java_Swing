package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnect {

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String userName = "postgres";
		String password = "2bdi2109";
		
		try {
			Connection connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Connected to the PostgreSQL server successfully.");
			
			// do someting
			
			connection.close();
		
		} catch (SQLException e) {
			System.out.println("Connected to the PostgreSQL server failed.");
			e.printStackTrace();
			
		} finally {
			System.out.println("Terminates the process");
		}
	}
}
