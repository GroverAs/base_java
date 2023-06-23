package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    private static final Comparator<Resume> COMPARATOR = Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullName);

    protected abstract SK getSearchKey(String uuid); 
    
    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> doGetAll();



    public void save(Resume r) {
        LOG.info("Save" + r);
        SK searchKey = getNotExistingSearchKey(r.getUuid());
            doSave(r, searchKey);
        }


    public void delete(String uuid) {
        LOG.info("Delete" + uuid);
        SK searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }


    public void update(Resume r) {
        LOG.info("Update" + r);
        SK searchKey = getExistingSearchKey(r.getUuid());
            doUpdate(r, searchKey);
        }


    public Resume get(String uuid) {
        LOG.info("Get" + uuid);
        SK searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    private SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }


    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }

}

