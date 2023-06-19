package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Object uuid) {
        storage.put((String) uuid,resume);

    }

    @Override
    protected void doUpdate(Resume resume, Object uuid) {
        storage.put((String) uuid, resume);

    }

    @Override
    protected void doDelete(Object uuid) {
        storage.remove((String) uuid);

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey((String) uuid);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    public void clear() {
        storage.clear();

    }

    @Override
    public List<Resume> doGetAll() {
        return ****
    }

    @Override
    public int size() {
        return storage.size();
    }
}
