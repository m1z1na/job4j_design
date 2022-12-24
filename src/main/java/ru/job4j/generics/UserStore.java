package ru.job4j.generics;

public class UserStore implements Store<User> {

    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        if (findById(model.getId()) == null) {
            store.add(model);
        }

    }

    @Override
    public boolean replace(String id, User model) {
        boolean result = false;
        if (findById(id) != null) {
            store.replace(id, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (findById(id) != null) {
            store.delete(id);
            result = true;
        }
        return result;
    }

    @Override
    public User findById(String id) {
        return store.findById(id);
    }
}