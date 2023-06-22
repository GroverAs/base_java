package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage {
    public void clear();

    public void update(Resume r);

    public void save(Resume r);

    Resume get(String uuid);

    public void delete(String uuid);

    List<Resume> getAllSorted();

    public int size();

}
