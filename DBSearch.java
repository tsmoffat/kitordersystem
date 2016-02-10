package kitordersystem;

import jdk.internal.org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by tsmoffat on 10/02/2016.
 * This is a class to search the database for a specific string. Works in much the same way as the connection part in TableViewTest
 * but uses a user inputted search string as opposed to returning everything
 */
public class DBSearch {
    public DBSearch(String token) throws SQLException, IOException, org.xml.sax.SAXException, ParserConfigurationException {
        Connection conn = new getConnection().getConnection();
        String SQL = "select o.ID, o.CustomerID, c.Name, c.Email_Address, c.Squad, o.Order, o.OrderSize, o.OrderNumber, o.NameOnGarment, i.Item from Orders o INNER JOIN Customers c ON o.CustomerID = c.ID INNER JOIN Items i ON i.idItems=o.Order where regexp '^" + token + ",| " + token + ",| " + token + "$'";
        ResultSet rs = conn.createStatement().executeQuery(SQL);
    }
}
