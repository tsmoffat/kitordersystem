package kitordersystem;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

/**
 * The class that makes at least some of the fun happen, this was put in so the end user has the option to completely drop the table and rebuild it if something gets messed up for some
 * reason or another.
 */

public class DBCreate {
    private static ScriptReader reader2;

    /**
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
