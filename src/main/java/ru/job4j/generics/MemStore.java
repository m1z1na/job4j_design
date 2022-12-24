package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        if (findById(id) != null) {
            storage.replace(id, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (findById(id) != null) {
            storage.remove(id);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}