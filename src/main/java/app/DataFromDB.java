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
        try {
            statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sqlRequestGetPersonName);
            getLog.info("Data of SQL request from DB are received successfully");
            while (resultSet.next()) {
                String name = resultSet.getString("person_name");
                System.out.println(name + "\n");
            }
            resultSet.close();
            statement.close();
            connection.close();
            getLog.info("Current connect is closed");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            getLog.error("Problem with sql", throwables);
        }
    }
}
