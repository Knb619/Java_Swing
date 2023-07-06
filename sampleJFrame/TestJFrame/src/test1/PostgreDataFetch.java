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
			
			String sql = "SELECT customerid, name, sex, age FROM public.customer;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int customerID = resultSet.getInt("customerid");
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				int age = resultSet.getInt("age");
				
				System.out.println("CustomerID:" + customerID + " " + "Name:" + name + " " + "Sex:" + sex + " " + "Age:" + age);
				System.out.println("-----------------------------------------------------------------------");
			}
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("SQLExceptions are raised.");
			e.printStackTrace();
			
		} finally {
			System.out.println("End of process.");
		}
	}
}

// Result
//Connected to the PostgreSQL server successfully.
//CustomerID:1 Name:Yohei Kanebako Sex:M Age:30
//-----------------------------------------------------------------------
//CustomerID:2 Name:Rika Kanebako Sex:F Age:29
//-----------------------------------------------------------------------
//End of process.
