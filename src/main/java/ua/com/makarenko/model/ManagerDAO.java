package ua.com.makarenko.model;

import java.util.Map;
import java.util.Set;

public interface ManagerDAO {

    Set<String> listTables();

    void createTable(String tableName, String keyName, Map<String, Object> columns);

    void dropTable(String tableName);

    void clearTable(String tableName);

    void openTable(String tableName);
}
