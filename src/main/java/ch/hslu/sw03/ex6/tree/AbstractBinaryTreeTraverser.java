package ch.hslu.sw03.ex6.tree;

import ch.hslu.sw03.ex6.BinaryTreeTraverser;

import java.util.function.Consumer;

abstract class AbstractBinaryTreeTraverser<E extends Comparable<E>> implements BinaryTreeTraverser<E> {

    @Override
    public void traverse(BinaryTree<E> tree, Consumer<E> action) {
        if (tree.root != null) {
            traverse(action, tree.root);
        }
    }

    abstract void traverse(Consumer<E> action, Node<E> current);
}
