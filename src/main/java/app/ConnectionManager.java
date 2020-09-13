package app;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final Logger getLog= Logger.getLogger(ConnectionManager.class);
    Connection connection;
    ConnectionPool connectionPool = new ConnectionPool();
    private static Connection activeConnection; // global variable to return active connect to DB
    public ConnectionManager() {
    }
    public Connection connectToDB() throws SQLException {
        if (activeConnection==null) {
            try {
                getLog.debug("Connecting to master DB...");
                connection = DriverManager.getConnection(
                        connectionPool.getMasterUrl(),
                        connectionPool.getUser(),
                        connectionPool.getPassword()
                );                                          //getting connection to masterDB
                activeConnection = connection;
                getLog.info("Connected to master DB");
                activeConnection.setAutoCommit(false);//disable auto-commit mode
            } catch (SQLException e) {
                getLog.error("Can't connect to master DB", e);
                try {
                    getLog.info("Connecting to reserve DB...");
                    connection = DriverManager.getConnection(
                            connectionPool.getSecondUrl(),
                            connectionPool.getUser(),
                            connectionPool.getPassword()
                    );                                      //getting connection to reserveDB
                    activeConnection = connection;
                    getLog.info("Connected to reserve DB");
                    activeConnection.setAutoCommit(false);//disable auto-commit mode
                } catch (SQLException throwables) {
                    activeConnection=null;// if all DB are not available
                    getLog.error("Can't connect to reserve DB", throwables);
                }

            }
        }
    return activeConnection;    
    }
}
