package com.urise.webapp;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\JAVA\\BaseJava\\base_java\\.gitignore");
        System.out.println(file.getCanonicalPath());
        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        for (String name : Objects.requireNonNull(dir.list())){
            System.out.println(name);
        }
    }
}
