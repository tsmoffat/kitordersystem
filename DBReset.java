package kitordersystem;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * How the program resets the database if everything goes wrong with it, or
 * it gets too cluttered. Most of the heavy lifting is done through Mybatis,
 * which is far more powerful than this project needs but it works very well.
 */
public class DBReset {

    public DBReset(){
        String SQLFilePath = "/Users/tsmoffat/kitordersystem/kitordersystem/" +
                "kitordersystem/KitOrderProper.sql";
        try{
            System.out.println("RESETTING");
            Connection conn = new getConnection().getConnection();
            ScriptRunner sr = new ScriptRunner(conn);
            Reader reader = new BufferedReader(new FileReader(SQLFilePath));
            sr.runScript(reader);
        } catch (SAXException | SQLException | ParserConfigurationException |
                IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.err.println("Failed to execute " + SQLFilePath + ". The  " +
                    "error is " + e.getMessage());
        }

    }

}
