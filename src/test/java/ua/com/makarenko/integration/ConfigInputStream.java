package ua.com.makarenko.integration;

import java.io.InputStream;

public class ConfigInputStream extends InputStream {
    private String line;
    private boolean endLine = false;

    @Override
    public int read() {
        if (line.length() == 0) {
            return -1;
        }

        if (endLine) {
            endLine = false;
            return -1;
        }

        char ch = line.charAt(0);
        line = line.substring(1);

        if (ch == '\n') {
            endLine = true;
        }
        return (int) ch;
    }

    public void add(String line) {
        if (this.line == null) {
            this.line = line;
        } else {
            this.line += "\n" + line;
        }
    }

    @Override
    public synchronized void reset() {
        line = null;
        endLine = false;
    }
}
