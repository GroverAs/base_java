package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {

        File dir = new File("D:\\JAVA\\BaseJava\\base_java\\src\\com\\urise\\webapp");

        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
        showDirectory(dir,"");
    }

    public static void showDirectory(File dir, String shift) {
        File[] listFiles = dir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    System.out.println(shift + "File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(shift + "Dir: " + file.getName());
                    showDirectory(file, " ");
                }
            }
        } else {
            System.out.println(dir.getName() + " is not a folder");
        }
    }
}
