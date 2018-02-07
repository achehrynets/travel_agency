package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader {

    private final File file;

    public FileReader(File file) {
        this.file = file;
    }

    public String read() throws IOException{
        String text = "";
        synchronized (file) {
            BufferedReader in = new BufferedReader(new java.io.FileReader(file));
            StringBuilder sb = new StringBuilder();
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
            } finally {
                in.close();
            }
            text = sb.toString();
        }
        return text;
    }

}
