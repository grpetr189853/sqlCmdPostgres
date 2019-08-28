package ua.com.makarenko.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

class PropertiesOfConnecting {
    private String driver;
    private String host;

    public String getDriver() {
        return driver;
    }

    public String getHost() {
        return host;
    }

    public PropertiesOfConnecting invoke() {
        Properties propertyDriver = new Properties();

        try {
            FileInputStream fileInputDriver = new FileInputStream("src/main/resources/driver.properties");
            propertyDriver.load(fileInputDriver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = propertyDriver.getProperty("db.driver");
        host = propertyDriver.getProperty("db.host");
        return this;
    }
}
