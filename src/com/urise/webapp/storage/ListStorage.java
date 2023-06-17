package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage{

    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        resumeList.add(resume);

    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumeList.set((Integer) searchKey, resume);

    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeList.remove(((Integer)searchKey).intValue());

    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for(int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)){
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeList.get((Integer)searchKey);
    }

    @Override
    public void clear() {
        resumeList.clear();

    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
