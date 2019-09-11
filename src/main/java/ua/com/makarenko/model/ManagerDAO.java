package ua.com.makarenko.model;

import java.util.Map;
import java.util.Set;

public interface ManagerDAO {

    Set<String> listTables();

    void createTable(String tableName, String keyName, Map<String, String> columns);

    void dropTable(String tableName);

    void clearTable(String tableName);

    void openTable(String tableName);

    void insertData(String tableName, Map<String, Object> row);

    void updateData(String tableName, String keyName, String keyValue, Map<String, Object> column);

    void deleteData(String tableName, String columnName, String columnValue);

    boolean isConnected();
}
