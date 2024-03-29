package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {


    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    public Node<E> getRoot() {
        return root;
    }

    public boolean add(E parent, E child) {
        boolean isAdd = false;
        Optional<Node<E>> curNode = findBy(parent);
        if (curNode.isPresent() && !findBy(child).isPresent()) {
            curNode.get().addChildren(child);
            isAdd = true;
        }
        return isAdd;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

}