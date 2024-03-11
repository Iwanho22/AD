package ch.hslu.sw02.ex2.collections;

import java.util.*;

public class LinkedList<E> implements List<E> {
    private Node<E> last;
    private Node<E> first;
    private int size;

    public void push(E item) {
        if (size == 0) {
            var newNode = new Node<>(item, null, null);
            last = newNode;
            first = newNode;
            size++;
        } else {
            linkBefore(item, first);
        }
    }

    public void pushAll(List<? extends E> items) {
        var reversed = items.stream().sorted(Collections.reverseOrder()).toList();
        for (E item : reversed) {
            push(item);
        }
    }


    public E pop() {
        E ret = null;
        if (size != 0) {
            ret = unlink(first);

            // reset nex reference of head and tail to avoid memory leak
            if (first != null) {
                first.previous = null;
            } else {
                last = null;
            }
        }
        return ret;
    }

    @Override
    public boolean add(E item) {
        linkLast(item);

        return true;
    }

    @Override
    public E removeLast() {
        if (size != 0) {
            var ret = unlink(last);

            // reset nex reference of head and tail to avoid memory leak
            if (last != null) {
                last.next = null;
            } else {
                first = null;
            }
            return ret;
        } else {
            throw new NoSuchElementException("List is empty.");
        }
    }

    @Override
    public E getFirst() {
        return first != null ? first.item : null;
    }

    @Override
    public E getLast() {
        return last != null ? last.item : null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Node<E> current = last; current != null; current = current.previous) {
            if (Objects.equals(o, current.item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for (Node<E> current = first; current != null; current = current.next) {
            if (Objects.equals(o, current.item)) {
                var previous = current.previous;
                var next = current.next;

                if (next == null) {
                    removeLast();
                } else if (previous == null) {
                    pop();
                } else {
                    unlink(current);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);
        if (index == size) {
            addAll(c);
        } else {
            var next = node(index);

            for (E item : c) {
                linkBefore(item, next);
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        var changed = false;
        for (Object o : c) {
            changed = remove(o) || changed;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        var itr = iterator();
        var changed = false;
        while (itr.hasNext()) {
            var next = itr.next();
            if (!c.contains(next)) {
                itr.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        this.last = null;
        this.first = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        Node<E> current = node(index);

        return current.item;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        var node = node(index);
        var oldVal = node.item;
        node.item = element;

        return oldVal;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        var node = node(index);

        return unlink(node);
    }

    @Override
    public int indexOf(Object o) {
        var index = 0;
        for (E item : this) {
            if (Objects.equals(o, item)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        var index = size - 1;
        for (Node<E> current = last; current != null; current = current.previous) {
            if (Objects.equals(current.item, o)) {
                return index;
            }
            index--;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListItr(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new ListItr(0);
    }

    @Override
    public Object[] toArray() {
        var obj = new Object[size];
        int index = 0;

        for (E element : this) {
            obj[index++] = element;
        }

        return Arrays.copyOf(obj, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int size = size();
        @SuppressWarnings("unchecked")
        T[] ret = a.length >= size ? a : (T[]) java.lang.reflect.Array
                .newInstance(a.getClass().getComponentType(), size);

        Iterator<E> it = iterator();

        for (int i = 0; i < ret.length; i++) {
            if (!it.hasNext()) { // fewer elements than expected
                if (a == ret) {
                    ret[i] = null; // null-terminate
                } else if (a.length < i) {
                    return Arrays.copyOf(ret, i);
                } else {
                    System.arraycopy(ret, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
                return a;
            }
            ret[i] = (T) it.next();
        }

        return ret;
    }

    private Node<E> node(int index) {
        Node<E> x;
        if (index < (size / 2)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.previous;
        }
        return x;
    }


    private void linkBefore(E item, Node<E> next) {
        var prev = next.previous;
        var current = new Node<>(item, prev, next);
        next.previous = current;


        if (prev == null) {
            first = current;
        } else {
            prev.next = current;
        }
        size++;
    }

    private void linkLast(E item) {
        var previous = last;
        var newLast = new Node<>(item, previous, null);
        last = newLast;
        if (size == 0) {
            first = newLast;
        }
        if (previous != null) {
            previous.next = newLast;
        }
        size++;
    }

    private E unlink(Node<E> node) {
        var prev = node.previous;
        var next = node.next;

        if (next == null) {
            last = prev;
        }
        if (prev == null) {
            first = next;
        }
        if (next != null && prev != null) {
            prev.next = next;
            next.previous = prev;
        }
        node.next = null;
        node.previous = null;
        size--;

        return node.item;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for list with size %s.", index,
                    size));
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
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

    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;

        private ListItr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;

            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            next = next == null ? first : next.previous;
            lastReturned = next;
            nextIndex--;

            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            var lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
        }

        @Override
        public void set(E e) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            lastReturned.item = e;
        }

        @Override
        public void add(E e) {
            lastReturned = null;
            if (next == null) {
                linkLast(e);
            } else {
                linkBefore(e, next);
            }
            nextIndex++;
        }
    }
}
