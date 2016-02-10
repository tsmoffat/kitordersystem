package kitordersystem;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;



public class DBCreate {
	private static ScriptReader reader2;

	/**
	 *
	 * @throws IOException
	 * @throws SQLException
	 * @throws SAXException
	 * @throws ParserConfigurationException
     */
	public static void SQLReader() throws IOException, SQLException, SAXException, ParserConfigurationException {
		Reader reader = new FileReader(
				"/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/KitOrderSystem.sql");
		getConnection conn = new getConnection();
		conn.getConnection();
		
		reader2.runScript(reader);
	}

	public static void main(String[] args) throws IOException, SQLException, SAXException, ParserConfigurationException {
		SQLReader();
	}
}
