import app.ConnectionManager;
import app.DataFromDB;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DataFromDB dataFromDB = new DataFromDB();
        DOMConfigurator.configure("src/main/resources/log4j.xml");
        ConnectionManager connectionManager = new ConnectionManager();
        boolean loop= true;
        while (loop){
            System.out.println("Choose one");
            System.out.println("1: request to DB(get person name) \n2: exit");
            int number = scanner.nextInt();  // Read user input
            switch (number)
            {
                case 1 : Connection connection=connectionManager.connectToDB();
                    dataFromDB.getNameDataFromDB(connection);
                    break;
                case 2 : loop = false;
                    break;
            }
        }

    }
}
