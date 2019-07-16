package ua.com.makarenko.commands;

import java.io.*;
import java.util.Properties;

public class Connect implements Command {
    @Override
    public boolean beginCommand(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void executeCommand(String command) {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            String line = reader.readLine();
//            String[] data = line.split("\\|");
//
//            String database = data[0];
//            String login = data[1];
//            String password = data[2];
//
//            OutputStream output = new FileOutputStream("src/main/resources/config.properties");
//            Properties prop = new Properties();
//
//            prop.setProperty("db.database", database);
//            prop.setProperty("db.login", login);
//            prop.setProperty("db.password", password);
//            prop.store(output, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
