package ch.hslu.sw03.ex6.tree;

class Node<E extends Comparable<E>> {
    E value;
    int hash;
    Node<E> left;
    Node<E> right;

    public Node(E value) {
        this.value = value;
        hash = value.hashCode();
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

}
