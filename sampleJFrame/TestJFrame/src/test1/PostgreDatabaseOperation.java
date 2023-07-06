package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreDatabaseOperation {
	public static void main(String[] args) {
		final String url = "jdbc:postgresql://localhost:5432/postgres";
		final String userName = "postgres";
		final String password = "2bdi2109";
		
		System.out.println("Start of process.");
		
		try (Connection connection = DriverManager.getConnection(url, userName, password)) {
			System.out.println("Connected to the PostgreSQL server successfully.");
			
			String sql = "INSERT INTO public.customer (customerid, sex, age, name) VALUES (3, 'M', 0, 'Rui Kanebako');";
//			String sql = "DELETE FROM public.customer WHERE customerid = 6;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			int result = statement.executeUpdate(); // DELETE, INSERT, UPDATEは executeUpdate() を実行する。戻り値はint型。

			System.out.println("result:" + result + " Operation completed.");
			
			statement.close();
			connection.close();
			
			System.out.println("Connection closed.");
			
		} catch (SQLException e) {
			System.out.println("[ERROR] SQLExceptions are raised.");
			e.printStackTrace();
			
		} catch (Exception e) {
			System.out.println("[ERROR] Exceptions are raised.");
			e.printStackTrace();
			
		} finally {
			System.out.println("End of process.");
		}
	}
}

// Result
//Start of process.
//Connected to the PostgreSQL server successfully.
//result:1 Operation completed.
//Connection closed.
//End of process.
