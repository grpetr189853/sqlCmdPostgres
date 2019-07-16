package ua.com.makarenko;

import ua.com.makarenko.model.JDBCManagerDAO;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.Console;
import ua.com.makarenko.view.Message;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Message message = new Console();

        try {
            String line = message.read();
            String[] data = line.split("\\|");

            String database = data[0];
            String login = data[1];
            String password = data[2];

            OutputStream output = new FileOutputStream("src/main/resources/config.properties");
            Properties prop = new Properties();

            prop.setProperty("db.database", database);
            prop.setProperty("db.login", login);
            prop.setProperty("db.password", password);
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ManagerDAO managerDAO = new JDBCManagerDAO();
        managerDAO.getConnection();

        Set<String> strings = managerDAO.listTables();

        System.out.println(strings);

    }
}
