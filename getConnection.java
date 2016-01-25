package kitordersystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class getConnection {
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Scanner in = new Scanner(System.in);
		System.out.println(
				"Please enter the IP/web address, including port, of the system or leave blank if it is running on your system");
		String IP = in.nextLine();
		System.out.println("Please enter the username for your database");
		String user = in.nextLine();
		System.out.println("Please enter the password for your database");
		String password = in.nextLine();
		if (IP == null) {
			String JDBC_URL = "jdbc:mysql://localhost:3306/";
			conn = DriverManager.getConnection(JDBC_URL, user, password);
		} else {
			String JDBC_URL = "jdbc:mysql://" + IP;
			conn = DriverManager.getConnection(JDBC_URL, user, password);
		}
		return conn;
	}
}
