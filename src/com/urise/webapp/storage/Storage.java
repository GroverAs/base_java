package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public interface Storage {
    public void clear();

    public void update(Resume resume);

    public void save(Resume resume);

    Resume get(String uuid);

    public void delete(String uuid);

    Resume[] getAll();

    public int size();

}
