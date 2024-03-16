package ch.hslu.sw03.ex5.tree;

import java.util.function.Consumer;

public class PostOrderBinaryTreeTraverser extends AbstractBinaryTreeTraverser {
    @Override
    void traverse(Consumer<Integer> action, Node<Integer> current) {
        if (current.left != null) {
            traverse(action, current.left);
        }
        if (current.right != null) {
            traverse(action, current.right);
        }
        action.accept(current.value);
    }
}
