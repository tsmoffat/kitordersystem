package kitordersystem;

import java.io.*;
import java.sql.*;
import java.util.Scanner;



public class DBCreate {
	private static ScriptReader reader;
	/**
	 * 
	 * @param scrreader
	 *            - passes the script through
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void SQLReader(ScriptReader scrreader) throws IOException, SQLException {
		Reader reader = new FileReader(
				"/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/KitOrderSystem.sql");
		getConnection conn = new getConnection();
		conn.getConnection();
		scrreader.runScript(reader);
	}

	public static void main(String[] args) throws IOException, SQLException {
		SQLReader(reader);
	}
}
