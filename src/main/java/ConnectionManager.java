import org.apache.log4j.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConnectionManager {
    final static Logger logger = Logger.getLogger(ConnectionManager.class);

    public String author ="Oleksandr";
    public String query = "SELECT VERSION()";



    public void connectToDB(String url, String user, String password){

        try {
            Connection connection = DriverManager.getConnection(url,user,password);// створення підключення до перщої бази даних
            logger.debug("Підключення до бази даних");
            logger.debug("Відбулося підключення до бази даних: ");
            connection.isValid(25);// записати в дата сторадж
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("SQL Error", throwables);//.fillInStackTrace()
        }

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
