import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataFromDB {

    private String sqlRequestGetPersonName="SELECT * FROM person";
public void getNameDataFromDB(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet= statement.executeQuery(sqlRequestGetPersonName);
    while (resultSet.next()) {
        String name = resultSet.getString("person_name");
        System.out.println(name + "\n");
    }
    connection.close();
}

}
