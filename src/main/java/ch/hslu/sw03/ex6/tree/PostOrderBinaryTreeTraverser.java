package ch.hslu.sw03.ex6.tree;

import java.util.function.Consumer;

public class PostOrderBinaryTreeTraverser<E extends Comparable<E>> extends AbstractBinaryTreeTraverser<E> {
    @Override
    void traverse(Consumer<E> action, Node<E> current) {
        if (current.left != null) {
            traverse(action, current.left);
        }
        if (current.right != null) {
            traverse(action, current.right);
        }
        action.accept(current.value);
    }
}
