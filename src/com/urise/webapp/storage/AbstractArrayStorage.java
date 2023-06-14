package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage{
    protected int size;
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }


    @Override
    protected void doSave(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            addResume(resume, (Integer) index);
            size++;
        }
    }


    @Override
    protected void doDelete(Integer index) {
        fillDeletedResume(index);
        storage[size] = null;
        size--;
    }


    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }


    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void fillDeletedResume(int index);

    protected abstract void addResume(Resume resume, int index);

}
