package ch.hslu.sw03.ex6;

import ch.hslu.sw03.ex6.tree.BinaryTree;

import java.util.function.Consumer;

public interface BinaryTreeTraverser<E extends Comparable<E>> {
    /**
     * Traverses a tree.
     * The order of the traversal is determined on the concrete implementation.
     * Performs the given action on the element.
     *
     * @param action The action to perform.
     * @param tree   The tree to traverse.
     */
    void traverse(BinaryTree<E> tree, Consumer<E> action);
}
