package com.urise.webapp;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import java.io.*;
import java.util.Properties;

public class Config {
    public static final Config INSTANCE = new Config();
    private static final File PROPS = new File("config\\resumes.properties");

    //            -DhomeDirectory="D:/JAVA/BaseJava/base_java"
    private final Storage storage;
    private final File storageDir;
    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream("config\\resumes.properties")) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));

        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

//    private static File getHomeDirectory() {
//        String prop = System.getProperty("homeDirectory");
//        File homeDirectory = new File(prop == null ? "." : prop);
//        if (!homeDirectory.isDirectory()) {
//            throw new IllegalStateException(homeDirectory + " is not directory");
//        }
//        return homeDirectory;
//    }

    public File getStorageDir() {
        return storageDir;
    }
    public Storage getStorage() {
        return storage;
    }

}
