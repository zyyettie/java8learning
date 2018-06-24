package com.zyy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteToFile {
    public static void main(String[] args) {
        try {
//            writeUsingBufferedWriter();
            writeUsingFilesWrite();
        } catch (IOException e) {

        }
    }

    private static void writeUsingBufferedWriter() throws IOException {
        //Get the file reference
        Path path = Paths.get("c:/work/output.txt");

        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("Hello World");
        }
    }

    private static void writeUsingFilesWrite() throws IOException {
        String content = "Hello world 1";
        Files.write(Paths.get("c:/work/output.txt"), content.getBytes());
    }

}
