package ua.com.makarenko.model;

import java.sql.Connection;
import java.util.Set;

public interface ManagerDAO {

    Connection getConnection();

    Set<String> listTables();


}
