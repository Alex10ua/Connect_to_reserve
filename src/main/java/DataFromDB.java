import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataFromDB {
private static final Logger getLog = LogManager.getLogger(DataFromDB.class);
    private String sqlRequestGetPersonName="SELECT * FROM person";
public void getNameDataFromDB(Connection connection) {
    Statement statement = null;
    try {
        statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery(sqlRequestGetPersonName);
        getLog.trace("Data of SQL request from DB are received successfully");
        while (resultSet.next()) {
            String name = resultSet.getString("person_name");
            System.out.println(name + "\n");
        }
        connection.close();
        getLog.trace("Current connect is closed");
    } catch (SQLException throwables) {
        throwables.printStackTrace();
        getLog.trace("Problem with sql", throwables);
    }




}

}
