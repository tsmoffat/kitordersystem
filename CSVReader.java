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
 * Created by tsmoffat on 22/02/2016.
 */
public class CSVReader {
    /**
     * @throws IOException
     * @throws SQLException
     * @throws SAXException
     * @throws ParserConfigurationException
     */

    public CSVReader() throws IOException, SQLException, SAXException, ParserConfigurationException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/Hope.csv"));
        Connection c = new getConnection().getConnection();
        Statement st = c.createStatement();
        st.executeUpdate("use mydb");
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] item = line.trim().split(",");
            // if you want to check either it contains some name
            // index 0 is first name, index 1 is last name, index 2 is ID
            st.executeUpdate("insert into Items (Item) values (\"" + item[1] + "\")");

        }

    }
}
