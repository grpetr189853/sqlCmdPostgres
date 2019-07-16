package ua.com.makarenko.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

class PropertiesOfConnecting {
    private String driver;
    private String host;
    private String database;
    private String login;
    private String password;

    public String getDriver() {
        return driver;
    }

    public String getHost() {
        return host;
    }

    public String getDatabase() {
        return database;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public PropertiesOfConnecting invoke() {
        Properties propertyDriver = new Properties();
        Properties propertyConfig = new Properties();
        try {
            FileInputStream fileInputDriverHost = new FileInputStream("src/main/resources/driver.properties");
            FileInputStream fileInputConfig = new FileInputStream("src/main/resources/config.properties");
            propertyDriver.load(fileInputDriverHost);
            propertyConfig.load(fileInputConfig);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = propertyDriver.getProperty("db.driver");
        host = propertyDriver.getProperty("db.host");
        database = propertyConfig.getProperty("db.database");
        login = propertyConfig.getProperty("db.login");
        password = propertyConfig.getProperty("db.password");
        return this;
    }
}
