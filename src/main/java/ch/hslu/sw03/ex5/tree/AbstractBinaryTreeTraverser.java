package ch.hslu.sw03.ex5.tree;

import ch.hslu.sw03.ex5.BinaryTreeTraverser;

import java.util.function.Consumer;

abstract class AbstractBinaryTreeTraverser implements BinaryTreeTraverser<Integer> {

    @Override
    public void traverse(BinaryTree tree, Consumer<Integer> action) {
        if (tree.root != null) {
            traverse(action, tree.root);
        }
    }

    abstract void traverse(Consumer<Integer> action, Node<Integer> current);
}
