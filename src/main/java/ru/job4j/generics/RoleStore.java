package ru.job4j.generics;

public class RoleStore implements Store<Role> {


    private final Store<Role> role = new MemStore<>();


    @Override
    public void add(Role model) {
        if (findById(model.getId()) == null) {
            role.add(model);
        }
    }

    @Override
    public boolean replace(String id, Role model) {
        boolean result = false;
        if (findById(id) != null) {
            role.replace(id, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (findById(id) != null) {
            role.delete(id);
            result = true;
        }
        return result;
    }

    @Override
    public Role findById(String id) {
        return role.findById(id);
    }
}