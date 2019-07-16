package ua.com.makarenko.model;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class JDBCManagerDAO implements ManagerDAO {

    @Override
    public Connection getConnection() {
        Connection connection;

        PropertiesOfConnecting properties = new PropertiesOfConnecting().invoke();
        String driver = properties.getDriver();
        String host = properties.getHost();
        String database = properties.getDatabase();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(host + database, login, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Setup JDBC driver", e);
        } catch (SQLException e) {
            throw new RuntimeException(String.format(
                    "Could not connect to database '%s' ", database), e);
        }
        return connection;
    }

    @Override
    public Set<String> listTables() {
        Connection connection = getConnection();
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
        Set<String> tables = new LinkedHashSet<>();
        if (connection != null) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    tables.add(resultSet.getString("table_name"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
        }
        return tables;
    }
}

