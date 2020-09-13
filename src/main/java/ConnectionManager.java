import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
    final static Logger logger = Logger.getLogger(ConnectionManager.class);

    Connection connection;
    ConnectionPool connectionPool = new ConnectionPool();



    public Connection connectToDB() throws SQLException {
        Connection activeConnection = null;// global variable to return active connect to DB
        try{
            activeConnection = connection = DriverManager.getConnection(
                    connectionPool.getMasterUrl(),
                    connectionPool.getUser(),
                    connectionPool.getPassword()
            );

            connection.setAutoCommit(false);
        } catch (SQLException e) {
            try {
                 activeConnection = connection = DriverManager.getConnection(
                        connectionPool.getSecondUrl(),
                        connectionPool.getUser(),
                        connectionPool.getPassword()
                );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
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
