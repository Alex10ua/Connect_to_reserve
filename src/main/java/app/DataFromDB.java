package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class DataFromDB {

    private static final Logger getLog= Logger.getLogger(ConnectionManager.class);
    private String sqlRequestGetPersonName="SELECT * FROM person";

    public void getNameDataFromDB(Connection connection) {
        Statement statement = null;
        if (connection!=null){
            try {
                statement = connection.createStatement();
                ResultSet resultSet= statement.executeQuery(sqlRequestGetPersonName);// result from DB
                getLog.info("Data of SQL request from DB are received successfully");
                while (resultSet.next()) {                                          // parse an answer and sout
                    String name = resultSet.getString("person_name");
                    System.out.println(name + "\n");
                }
                resultSet.close();
                statement.close();
                getLog.info("Current connect is closed");
            } catch (SQLException throwables) {
                getLog.error("Problem with SQL", throwables);
              }
        }else {
            getLog.error("All DB are not available");
            System.out.println("All DB are not available");
         }
        }
}
