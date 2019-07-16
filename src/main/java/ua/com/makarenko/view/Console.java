package ua.com.makarenko.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console implements Message {
    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void writeError(String message) {
        System.err.println(message);
    }

    @Override
    public String read() {
        String readResult = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            readResult = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readResult;
    }
}
