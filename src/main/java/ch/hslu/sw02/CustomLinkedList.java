package ch.hslu.sw02;

import java.util.*;

public final class CustomLinkedList<E> implements CustomList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public void push(E item) {
        var previous = head;
        var newNode = new Node<>(item, previous, null);
        head = newNode;
        if (size == 0) {
            tail = newNode;
        }
        if (previous != null) {
            previous.next = newNode;
        }
        size++;
    }

    @Override
    public void pushAll(List<? extends E> items) {
        var reversed = items.stream().sorted(Collections.reverseOrder()).toList();
        for (E item : reversed) {
            push(item);
        }
    }

    @Override
    public E pop() {
        E ret = null;
        if (size != 0) {
            var oldHead = head;
            head = oldHead.previous;

            // reset nex reference of head and tail to avoid memory leak
            if (head != null) {
                head.next = null;
            } else {
                tail = null;
            }
            size--;
            ret = oldHead.item;
        }
        return ret;
    }

    @Override
    public void add(E item) {
        var next = tail;
        var newLast = new Node<>(item, null, next);
        tail = newLast;
        if (size == 0) {
            head = newLast;
        }
        if (next != null) {
            next.previous = newLast;
        }
        size++;
    }

    @Override
    public void removeLast() {
        if (size != 0) {
            var oldLast = tail;
            tail = oldLast.next;

            // reset nex reference of head and tail to avoid memory leak
            if (tail != null) {
                tail.previous = null;
            } else {
                head = null;
            }
            size--;
        }
    }

    @Override
    public E getFirst() {
        return head != null ? head.item : null;
    }

    @Override
    public E getLast() {
        return tail != null ? tail.item : null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(Object o) {
        for (Node<E> current = head; current != null; current = current.previous) {
            if (Objects.equals(o, current.item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(Object o) {
        for (Node<E> current = head; current != null; current = current.previous) {
            if (Objects.equals(o, current.item)) {
                var previous = current.previous;
                var next = current.next;

                if (next == null) {
                    pop();
                } else if (previous == null) {
                    removeLast();
                } else {
                    previous.next = next;
                    next.previous = previous;
                    size--;
                }
                return;
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomLinkedListItr(head);
    }

    private static class Node<E> {
        private E item;
        private Node<E> previous;
        private Node<E> next;

        Node(E item, Node<E> previous, Node<E> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }

    private class CustomLinkedListItr implements Iterator<E> {
        private Node<E> current;

        public CustomLinkedListItr(Node<E> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current.previous != null;
        }

        @Override
        public E next() {
            var item = current.item;
            current = current.previous;
            return item;
        }
    }
}
