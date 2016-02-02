package kitordersystem;

import java.io.*;
import java.sql.*;
import java.util.Scanner;



public class DBCreate {
	private static ScriptReader reader;
	/**
	 * 
	 * @param reader2
	 *            - passes the script through
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void SQLReader(ScriptReader reader2) throws IOException, SQLException {
		Reader reader = new FileReader(
				"/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/KitOrderSystem.sql");
		getConnection conn = new getConnection();
		conn.getConnection();
		reader2.runScript(reader);
	}

	public static void main(String[] args) throws IOException, SQLException {
		Reader reader2 = new FileReader("/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/KitOrderSystem.sql");
		ScriptReader reader = new runScript(reader2);
		SQLReader(reader);
	}
}
