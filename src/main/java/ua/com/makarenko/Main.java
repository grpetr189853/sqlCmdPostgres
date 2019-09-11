package ua.com.makarenko;

import ua.com.makarenko.controller.Controller;
import ua.com.makarenko.model.JDBCManagerDAO;
import ua.com.makarenko.model.ManagerDAO;
import ua.com.makarenko.view.Console;
import ua.com.makarenko.view.Message;

public class Main {

    public static void main(String[] args) {
        Message message = new Console();
        ManagerDAO databaseManager = new JDBCManagerDAO();

        Controller controller = new Controller(message, databaseManager);
        controller.run();
    }
}
