package ch.hslu.sw10.ex4;

public class FixedSizeHeap<T extends Comparable<T>> implements Heap<T> {
    private final T[] heap;
    private int last;

    @SuppressWarnings("unchecked")
    public FixedSizeHeap(final int size) {
        this.heap = (T[]) new Comparable[size];
        this.last = -1;
    }

    @Override
    public void insert(T element) {
        this.heap[++this.last] = element;

        // restructure heap
        int position = this.last;
        int parent = (position - 1) / 2;
        while (this.heap[parent].compareTo(element) > 0) {

            swap(position, parent);
            position = parent;
            parent = (position - 1) / 2;
        }
    }

    @Override
    public T extractFirst() {
        var ret = this.heap[0];
        this.heap[0] = this.heap[this.last];
        this.heap[this.last] = null;

        // restructure
        int position = 0;
        int leftChild = 1;
        int rightChild = 2;
        while (position < this.last && leftChild < this.last) {
            int swapIndex = this.heap[rightChild] == null
                    || this.heap[leftChild].compareTo(this.heap[rightChild]) < 0 ? leftChild : rightChild;
            if (this.heap[position].compareTo(this.heap[swapIndex]) > 0) {
                swap(position, swapIndex);
            }

            position = swapIndex;
            leftChild = (2 * position) + 1;
            rightChild = 2 * (position + 1);
        }
        this.last--;
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return this.last < 0;
    }

    private void swap(int i, int j) {
        T tmp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = tmp;
    }
}
