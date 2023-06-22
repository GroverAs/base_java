package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;
import static java.util.Arrays.copyOfRange;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer>{
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
    protected void doSave(Resume r, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            addResume(r, index);
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
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }


    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    public List<Resume> doGetAll() {
        return Arrays.asList(copyOfRange(storage, 0, size));
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void fillDeletedResume(int index);

    protected abstract void addResume(Resume r, int index);

}
