package ch.hslu.sw03.ex5.tree;

class Node<E extends Comparable<E>> {
    E value;
    Node<E> left;
    Node<E> right;

    public Node(E value) {
        this.value = value;
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
