public class Main {
    public static void main(String[] args) {
        System.out.println("hello");
        ConnectionManager connectionManager = new ConnectionManager();
        ConnectionPool connectionPool = new ConnectionPool();
        connectionManager.connectToDB(connectionPool.getUrl(),connectionPool.getUser(),connectionPool.getPassword());
    }
}
