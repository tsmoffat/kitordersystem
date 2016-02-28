package kitordersystem;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

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
    public DBCreate() throws IOException, SQLException, SAXException, ParserConfigurationException {
        String sQLScriptFilePath="/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/KitOrderProper.sql";
        getConnection conn = new getConnection();
        conn.getConnection();
        Statement stmt = null;

    }

    public static void main(String[] args) throws IOException, SQLException, SAXException, ParserConfigurationException {

    }
}
