package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage{

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Object res) {
        storage.put(resume.getUuid(), resume);

    }

    @Override
    protected void doUpdate(Resume resume, Object res) {
        storage.put(resume.getUuid(), resume);

    }

    @Override
    protected void doDelete(Object res) {
        storage.remove(((Resume)res).getUuid());

    }


    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object res) {
        return res != null;
    }

    @Override
    protected Resume doGet(Object res) {
        return (Resume) res;
    }

    @Override
    public void clear() {
        storage.clear();

    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
