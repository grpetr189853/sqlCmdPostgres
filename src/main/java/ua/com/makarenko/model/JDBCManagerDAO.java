package ua.com.makarenko.model;

import ua.com.makarenko.view.PrintTable;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class JDBCManagerDAO implements ManagerDAO {

    private Connection getConnection() {
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
    public void createTable(String tableName, String keyName, Map<String, String> columns) {
        try (Statement stmt = getConnection().createStatement()) {
            String result = "";
            for (Map.Entry<String, String> pair : columns.entrySet()) {
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

    @Override
    public void insertData(String tableName, Map<String, Object> row) {
        try (Statement statement = getConnection().createStatement()) {
            StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
            Iterator<String> iteratorKey = row.keySet().iterator();
            while (iteratorKey.hasNext()) {
                sql.append(iteratorKey.next());
                if (iteratorKey.hasNext()) {
                    sql.append(", ");
                }
            }
            sql.append(") VALUES (");
            Iterator<Object> iteratorValue = row.values().iterator();
            while (iteratorValue.hasNext()) {
                sql.append("'" + iteratorValue.next() + "'");
                if (iteratorValue.hasNext()) {
                    sql.append(", ");
                }
            }
            sql.append(")").toString();
            statement.executeUpdate(String.valueOf(sql));
        } catch (SQLException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public void updateData(String tableName, String keyName, String keyValue, Map<String, Object> column) {
        try (Statement statement = getConnection().createStatement()) {
            for (Map.Entry<String, Object> pair : column.entrySet()) {
                statement.executeUpdate("UPDATE " + tableName +
                        " SET " + pair.getKey() + " = '" + pair.getValue() +
                        "' WHERE " + keyName + " = '" + keyValue + "'");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteData(String tableName, String columnName, String columnValue) {
        String sql = "DELETE FROM " + tableName + " WHERE " + columnName + " = '" + columnValue + "'";
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public boolean isConnected() {
        return getConnection() != null;
    }
}

