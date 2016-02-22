/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitordersystem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class getConnection {

    public String dbms;
    public String dbName;
    public String userName;
    public String password;
    public String urlString;
    private Properties prop;
    private String serverName;
    private int portNumber;

    /**
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public Connection getConnection() throws SQLException, IOException, SAXException, ParserConfigurationException {
        Connection conn = null;
        this.setProperties("/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/kitorder.xml");
        String JDBC_URL = "jdbc:" + dbms + "://" + serverName + ":" + "3306" + "/";
        System.out.println("Connecting to: " + dbms + "://" + serverName + ":3306");
        conn = DriverManager.getConnection(JDBC_URL, userName, password);
        return conn;
    }

    /**
     * @param fileName
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InvalidPropertiesFormatException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public void setProperties(String fileName) throws FileNotFoundException, IOException, InvalidPropertiesFormatException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fileName);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("database");
        Element database = (Element) nList.item(0);


        Element connection = (Element) database.getElementsByTagName("connection").item(0);


        this.dbms = connection.getElementsByTagName("dbms").item(0).getTextContent();
        this.dbName = connection.getElementsByTagName("database_name").item(0).getTextContent();
        this.userName = connection.getElementsByTagName("user_name").item(0).getTextContent();
        this.password = connection.getElementsByTagName("password").item(0).getTextContent();
        this.serverName = connection.getElementsByTagName("server_name").item(0).getTextContent();
        this.portNumber = Integer.parseInt(connection.getElementsByTagName("port_number").item(0).getTextContent());
    }

}
