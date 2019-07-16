package ua.com.makarenko.view;

public interface Message {

    void write(String message);

    void writeError(String message);

    String read();
}
