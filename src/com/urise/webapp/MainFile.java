package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {

        File root = new File("D:\\JAVA\\BaseJava\\base_java\\src\\com\\urise\\webapp");

        System.out.println(root.isDirectory());
        String[] list = root.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
        showDirectory(root);
    }

        public static void showDirectory(File root){
            File[] listFiles = root.listFiles();
            if(listFiles != null){
            for (File file : listFiles) {
                if (file.isFile()) {
                    System.out.println("File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName());
                    showDirectory(file);
                }
            }
        }
    }
}
