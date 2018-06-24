package com.zyy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamOfLines {

    public static void main(String[] args) {
        try {
//            readLinesUsingFileReeader();
//            readStreamOfLinesUsingFiles();
//            readStreamOfLinesUsingFilesWithTryBlock();
//            readStreamOfLinesUsingFilesWithTryBlock2();
            readStreamOfLinesUsingFilesWithTryBlock3();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("Hello World!");
    }

    private static void readLinesUsingFileReeader() throws IOException {
        File file = new File("c:/work/data.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("password")) {
                System.out.println(line);
            }
        }
        br.close();
        fr.close();
    }

    private static void readStreamOfLinesUsingFiles() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("c:/work", "data.txt"));
        Optional<String> hasPassword = lines.filter(s -> s.contains("password")).findFirst();
        if (hasPassword.isPresent()) {
            System.out.println(hasPassword.get());
        }
        //Close the stream and it's underlying file as well
        lines.close();
    }

    private static void readStreamOfLinesUsingFilesWithTryBlock() throws IOException {
        Path path = Paths.get("c:/work", "data.txt");
        //The stream hence file will also be closed here
        try (Stream<String> lines = Files.lines(path)) {
            Optional<String> hasPassword = lines.filter(s -> s.contains("password")).findFirst();
            if (hasPassword.isPresent()) {
                System.out.println(hasPassword.get());
            }
        }
    }

    private static void readStreamOfLinesUsingFilesWithTryBlock2() throws IOException {
        Path path = Paths.get("c:/work", "data.txt");
        //When filteredLines is closed, it closes underlying stream as well as underlying file.
        try (Stream<String> filteredLines = Files.lines(path).filter(s -> s.contains("password"))) {
            Optional<String> hasPassword = filteredLines.findFirst();
            if (hasPassword.isPresent()) {
                System.out.println(hasPassword.get());
            }

        }
    }

    private static void readStreamOfLinesUsingFilesWithTryBlock3() throws IOException {
        Path path = Paths.get("c:/work","data.txt");
        //When filteredLines is closed, it closes underlying stream as well as underlying file.
        try(Stream<String> filteredLines = Files.lines(path)
                .onClose(() -> System.out.println("File closed"))
                .filter(s -> s.contains("password"))){
            Optional<String> hasPassword = filteredLines.findFirst();
            if(hasPassword.isPresent()){
                System.out.println(hasPassword.get());
            }
        }
    }

}

