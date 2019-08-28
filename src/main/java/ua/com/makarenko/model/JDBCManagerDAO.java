package ua.com.makarenko.model;

import ua.com.makarenko.view.PrintTable;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class JDBCManagerDAO implements ManagerDAO {

    public Connection getConnection() {
        return ConnectDatabase.connect();
    }

    @Override
    public Set<String> listTables() {
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
        Set<String> tables = new LinkedHashSet<>();
            try (Statement statement = getConnection().createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    tables.add(resultSet.getString("table_name"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
        return tables;
    }

    @Override
    public void createTable(String tableName, String keyName, Map<String, Object> columns) {
        try (Statement stmt = getConnection().createStatement()) {
            String result = "";
            for (Map.Entry<String, Object> pair : columns.entrySet()) {
                result += ", " + pair.getKey() + " " + pair.getValue();
            }
            stmt.executeUpdate("CREATE TABLE " + tableName +
                    " ( " + keyName + " INT PRIMARY KEY NOT NULL " + result + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public void dropTable(String tableName) {
        String sql = "DROP TABLE ";
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sql + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public void clearTable(String tableName) {
        String sql = "DELETE FROM " + tableName;
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public void openTable(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            PrintTable.printResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}

