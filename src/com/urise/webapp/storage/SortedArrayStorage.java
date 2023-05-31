package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void fillDeletedResume(int index) {
        int deletedInd = size-index-1;
        if (deletedInd < 0){
            System.arraycopy(storage,????????);    //застрял здесь
        }

    }

    @Override
    protected void addResume(Resume resume, int index) {
        int addedInd = -index - 1;
        System.arraycopy(storage, addedInd, storage, index + 1, size - addedInd);
        storage[addedInd] = resume;
        size++;
    }

}

