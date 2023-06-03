package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected int size;
    protected int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            System.out.println("Resume with " + resume.getUuid() + " is already exist!");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else {
            addResume(resume, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " doesn't exist");
        } else {
            fillDeletedResume(index);
            size--;
            storage[size] = null;
        }
    }


    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume with " + resume.getUuid() + " doesn't exist!");
        } else {
            storage[index] = resume;
        }
    }
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " doesn't exist");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void fillDeletedResume(int index);

    protected abstract void addResume(Resume resume, int index);

}
