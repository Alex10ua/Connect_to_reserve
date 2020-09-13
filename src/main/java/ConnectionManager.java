import org.apache.log4j.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConnectionManager {
    final static Logger logger = Logger.getLogger(ConnectionManager.class);

    public String author ="Oleksandr";
    public String query = "SELECT VERSION()";
    Connection connection;
    ConnectionPool connectionPool = new ConnectionPool();

    public Connection connectToMasterDB(String url,String user,String password) {
        try {
            connection= DriverManager.getConnection(url,user,password);// створення підключення до перщої бази даних
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            return connection;
        }


    }

    public Connection connectToSecondDB(String secondUrl,String secondUser,String secondPassword){
        try {
            connection=DriverManager.getConnection(secondUrl,secondUser,secondPassword);
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }


    public Connection connectToDB(){
        // ініціалізувати тут пул для отримання адрес
        Connection activeConnection = null;// глобальна зміння для передачі поточного звязу
        try {
            //masterDB
            Connection masterConnect = connectToMasterDB(connectionPool.getMasterUrl(),connectionPool.getUser(),connectionPool.getPassword());
            System.out.println(masterConnect.isValid(1));
            activeConnection=masterConnect;
            masterConnect.setAutoCommit(false);
            boolean timeOutMaster = masterConnect.isValid(20);
            if (timeOutMaster!=true){
                Connection secondConnect = connectToSecondDB(connectionPool.getSecondUrl(),connectionPool.getUser(),connectionPool.getPassword());
                System.out.println(secondConnect.isValid(10));
                activeConnection=secondConnect;
                secondConnect.setAutoCommit(false);

                if (masterConnect.isValid(5))//зробити нормальну перевірку чи доступна мастер база
                {
                    Connection status=connectToMasterDB(connectionPool.getMasterUrl(),connectionPool.getUser(),connectionPool.getPassword());
                    System.out.println(status.isValid(10));
                }
            }
//            connection.isValid(25);// записати в дата сторадж
        } catch (SQLException throwables) {

            throwables.printStackTrace();
            logger.error("SQL Error", throwables);//.fillInStackTrace()
        }
    return activeConnection;    
    }


    /**
     * Тут потрібно написати підключення до перщої бази
     * потім перевірку чи не померла вона
     * написати метод для відновлення після відмови
     * підключення до резервної
     * реалізація через пули бази даних
     * час від часу перевіряти чи перша база диних відновилася
     * якщо відновилася перепідключитися до першої
     *
     *
     * Використати логування для кожної дії
     * записувати ці дії у дата сторадж
     * пошук даних по дата стораджу
     *
     */
}
