package ua.com.makarenko.model;

import ua.com.makarenko.view.DescriptionMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    private static Connection connection;

    public static Connection setupConnection(String database, String login, String password) {
        PropertiesOfConnecting properties = new PropertiesOfConnecting().invoke();
        String driver = properties.getDriver();
        String host = properties.getHost();

        try {
            Class.forName(driver);
            if (connection != null) connection.close();
            connection = DriverManager.getConnection(host + database, login, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(DescriptionMessage.SETUP_DRIVER.getDescription(), e);
        } catch (SQLException e) {
            connection = null;
            throw new RuntimeException(String.format(DescriptionMessage.NOT_CONNECT.getDescription(), database), e);
        }
        return connection;
    }

    public static Connection connect() {
        if(connection == null)
             new ConnectDatabase();
        return connection;
    }
}
