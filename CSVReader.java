package kitordersystem;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is called when the database is reset, from the main menu, its only
 * function is to repopulate the items database, which it does from a CSV
 * file so that the contents of the table can be edited if the items ever
 * change without having to recompile the whole program.
 */
public class CSVReader {
    /**
     * @throws IOException
     * @throws SQLException
     * @throws SAXException
     * @throws ParserConfigurationException
     */

    public CSVReader() throws IOException, SQLException, SAXException,
            ParserConfigurationException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/" +
                "tsmoffat/kitordersystem/kitordersystem/kitordersystem/Hope" +
                ".csv"));
        Connection c = new getConnection().getConnection();
        Statement st = c.createStatement();
        st.executeUpdate("use mydb");
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] item = line.trim().split(",");
            // if you want to check either it contains some name
            // index 0 is first name, index 1 is last name, index 2 is ID
            st.executeUpdate("insert into Items (Item) values (\"" + item[1]
                    + "\")");

        }

    }
}
