import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool implements IConnectionPool{

    //    private final String url = "jdbc:mysql://localhost:3306/people";
//    private final String user = "root";
//    private final String password = "root";
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;


    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        return false;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
