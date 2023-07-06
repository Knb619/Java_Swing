// PostgreDataFetch を取得データをListで受け取るように変更
package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Customer {
	private int customerid;
	private String name;
	private String sex;
	private int age;
	
	public Customer (int customerid, String name, String sex, int age) {
		this.customerid = customerid;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

public class PostgreDataConvList {
//	public class Customer {
//		private int customerid;
//		private String name;
//		private String sex;
//		private int age;
//		
//		public Customer (int customerid, String name, String sex, int age) {
//			this.customerid = customerid;
//			this.name = name;
//			this.sex = sex;
//			this.age = age;
//		}
//
//		public int getCustomerid() {
//			return customerid;
//		}
//
//		public void setCustomerid(int customerid) {
//			this.customerid = customerid;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public String getSex() {
//			return sex;
//		}
//
//		public void setSex(String sex) {
//			this.sex = sex;
//		}
//
//		public int getAge() {
//			return age;
//		}
//
//		public void setAge(int age) {
//			this.age = age;
//		}
//	}
	
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String userName = "postgres";
		String password = "2bdi2109";
		
		System.out.println("Start of process.\n");
		
		try (Connection connection = DriverManager.getConnection(url, userName, password)) {
			System.out.println("Connected to the PostgreSQL server successfully.\n");
			
			String sql = "SELECT customerid, name, sex, age FROM public.customer;";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			List<Customer> customerList = new ArrayList<>();
			
			while(resultSet.next()) {
				int customerID = resultSet.getInt("customerid");
				String name = resultSet.getString("name");
				String sex = resultSet.getString("sex");
				int age = resultSet.getInt("age");
				
				Customer customer = new Customer(customerID, name, sex, age); // 取得データを受け取るための Customer クラスをインスタンス化
//				PostgreDataConvList Postgre = new PostgreDataConvList(); // Customer クラスを内部クラスで実装する場合の記述
//				Customer customer = Postgre.new Customer(customerID, name, sex, age); // Customer クラスを内部クラスで実装する場合の記述
				
				customerList.add(customer);
			}
			resultSet.close();
			statement.close();
			connection.close();
			
			System.out.println("Connection closed.\n");
			System.out.println("Result:");
			
			for (Customer customer : customerList) {
				System.out.println("CustomerID:" + customer.getCustomerid() + " " + 
									     "Name:" + customer.getName() + " " + 
									      "Sex:" + customer.getSex() + " " + 
									      "Age:" + customer.getAge());
			}
			
			System.out.println();
			
		} catch (SQLException e) {
			System.out.println("SQLExceptions are raised.\n");
			e.printStackTrace();
			
		} finally {
			System.out.println("End of process.\n");
		}
	}
}

// Result
//Start of process.
//
//Connected to the PostgreSQL server successfully.
//
//Connection closed.
//
//Result:
//CustomerID:1 Name:Yohei Kanebako Sex:M Age:30
//CustomerID:2 Name:Rika Kanebako Sex:F Age:29
//CustomerID:3 Name:Rui Kanebako Sex:M Age:0
//
//End of process.

