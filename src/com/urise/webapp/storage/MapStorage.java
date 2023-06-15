package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Object searchKey) {

    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {

    }

    @Override
    protected void doDelete(Integer searchKey) {

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    public void clear() {
        resumeMap.clear();

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
