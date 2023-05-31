package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    // общий метод для нахождения резюме по индексу
    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void fillDeletedResume(int index) {
        storage[index] = storage[size - 1];

    }

    @Override
    protected void addResume(Resume resume, int index) {
        storage[size] = resume;

    }
}
