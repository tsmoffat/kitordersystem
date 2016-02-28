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
 * Not sure why this is still here, it was an experiment that I'm not sure if I got working. The CSV reader is useful though
 */
public class DBReset {

    public DBReset(){
        String SQLFilePath = "/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/KitOrderProper.sql";
        try{
            System.out.println("RESETTING");
            Connection conn = new getConnection().getConnection();
            ScriptRunner sr = new ScriptRunner(conn);
            Reader reader = new BufferedReader(new FileReader(SQLFilePath));
            sr.runScript(reader);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.err.println("Failed to execute " + SQLFilePath + ". The error is " + e.getMessage());
        }

    }

}
