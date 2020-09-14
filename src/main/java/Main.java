import app.ConnectionManager;
import app.DataFromDB;
import app.SearchInLogs;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        DataFromDB dataFromDB = new DataFromDB();
        SearchInLogs searchInLogs = new SearchInLogs();
        DOMConfigurator.configure("src/main/resources/log4j.xml");
        ConnectionManager connectionManager = new ConnectionManager();
        boolean loop= true;
        while (loop){
            System.out.println("Choose one");
            System.out.println("1: request to DB(get person name) \n2: searching in logs\n3: exit");
            int number = scanner.nextInt();  // Read user input
            switch (number)
            {
                case 1 : Connection connection=connectionManager.connectToDB();
                    dataFromDB.getNameDataFromDB(connection);
                    break;
                case 2 : searchInLogs.searchInLog();
                    break;
                case 3 : loop = false;
                    break;
            }
        }

    }
}
