package com.urise.webapp.storage;

import com.urise.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest{
    public SqlStorageTest() {
        super(new SqlStorage
                (Config.getInstance().getUrl(), Config.getInstance().getUser(), Config.getInstance().getPassword()));
    }
}
