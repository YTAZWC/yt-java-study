package top.ytazwc.demo;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;

/**
 * @author yt
 * 2024/6/3
 */
public class OutputStreamWriterDemo {
    public static void main(String[] args) throws IOException {
        File f = new File("temp.log");
        Writer out = new OutputStreamWriter(Files.newOutputStream(f.toPath()));
        out.write("Hello World!!!");
        out.close();
    }
}
