package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{

    Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Object uuid) {
        resumeMap.put((String) uuid,resume);

    }

    @Override
    protected void doUpdate(Resume resume, Object uuid) {
        resumeMap.put((String) uuid, resume);

    }

    @Override
    protected void doDelete(Object uuid) {
        resumeMap.remove((String) uuid);

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object uuid) {
        return resumeMap.containsKey((String) uuid);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return resumeMap.get((String) uuid);
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
