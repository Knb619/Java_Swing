package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreDataFetch {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String userName = "postgres";
		String password = "2bdi2109";
		
		try (Connection connection = DriverManager.getConnection(url, userName, password)) {
			System.out.println("Connected to the PostgreSQL server successfully.");
			
			String sql = "SELECT CustomerID, Name, Sex, Age FROM Customer;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int customerID = resultSet.getInt("CustomerID");
				String sex = resultSet.getString("Sex");
				int age = resultSet.getInt("Age");
				
				System.out.println("CustomerID:" + customerID + " " + "Sex:" + sex + " " + "Age:" + age);
				System.out.println("-----------------------------------------------------------------------");
			}
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("SQLExceptions are raised.");
			e.printStackTrace();
			
		} finally {
			System.out.println("Terminates the process.");
		}
	}
}
