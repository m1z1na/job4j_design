package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Tree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }


        public void addChildren(E value) {
            children.add(new Node<E> (value));
        }


        public List<Node<E>> getChildren() {
            return children;
        }
    }
}