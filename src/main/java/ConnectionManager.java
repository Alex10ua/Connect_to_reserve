import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
    private static final Logger getLog = LogManager.getLogger(ConnectionManager.class);

    Connection connection;
    ConnectionPool connectionPool = new ConnectionPool();


    public Connection connectToDB() throws SQLException {
        Connection activeConnection = null;// global variable to return active connect to DB
        try{
            getLog.debug("Connecting to master DB...");
            activeConnection = connection = DriverManager.getConnection(
                    connectionPool.getMasterUrl(),
                    connectionPool.getUser(),
                    connectionPool.getPassword()
            );
            getLog.debug("Connected to master DB");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            try {
                getLog.trace("Can't connect to master DB, connecting to reserve DB...", e);
                 activeConnection = connection = DriverManager.getConnection(
                        connectionPool.getSecondUrl(),
                        connectionPool.getUser(),
                        connectionPool.getPassword()
                );
                getLog.trace("Connected to reserve DB");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                getLog.error("Can't connect to reserve DB", throwables);
            }
            connection.setAutoCommit(false);
        }
    return activeConnection;    
    }


    /**
     *
     * Використати логування для кожної дії
     * записувати ці дії у дата сторадж
     * пошук даних по дата стораджу
     *
     */
}
