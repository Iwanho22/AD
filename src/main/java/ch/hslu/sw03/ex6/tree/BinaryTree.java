package ch.hslu.sw03.ex6.tree;

import ch.hslu.sw03.ex6.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryTree<E extends Comparable<E>> implements Tree<E> {
    private static final Logger LOG = LoggerFactory.getLogger(BinaryTree.class);
    Node<E> root;

    @Override
    public boolean search(E element) {
        var current = root;
        var elementHash = element.hashCode();

        while (current != null) {
            LOG.info("Processing Node {}", current);
            if ( elementHash == current.hash) {
                return true;
            }
            if (element.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    @Override
    public boolean insert(E element) {
        if (search(element)) {
            return false;
        }

        if (root == null) {
            root = new Node<>(element);
            return true;
        }

        var current = root;
        var inserted = false;
        do {
            if (element.compareTo(current.value) > 0) {
                if (current.right == null) {
                    current.right = new Node<>(element);
                    inserted = true;
                }
                current = current.right;
            } else {
                if (current.left == null) {
                    current.left = new Node<>(element);
                    inserted = true;
                }
                current = current.left;
            }
        } while (!inserted);
        return true;
    }

    @Override
    public boolean remove(E element) {
        var current = root;
        Node<E> parentOfCurrent = null;
        var found = false;
        BinaryNodeType type = BinaryNodeType.ROOT;

        do {
            if (element.equals(current.value)) {
                found = true;
            } else {
                parentOfCurrent = current;
                if (element.compareTo(current.value) > 0) {
                    current = current.right;
                    type = BinaryNodeType.RIGHT;
                } else {
                    current = current.left;
                    type = BinaryNodeType.LEFT;
                }
            }
        } while (!found && current != null);

        if (found) {
            Node<E> parentOfReplacement = current;
            Node<E> replacement = current.right;
            while (replacement != null && replacement.left != null) {
                parentOfReplacement = replacement;
                replacement = replacement.left;
            }

            if (replacement != null) {
                replacement.left = current.left;
                replacement.right = current.right;
            } else {
                replacement = current.left;
            }

            // reset reference to avoid infinite loop
            if (parentOfReplacement != current) {
                parentOfReplacement.left = null;
            }

            switch (type) {
                case BinaryNodeType.LEFT -> parentOfCurrent.left = replacement;
                case BinaryNodeType.RIGHT -> parentOfCurrent.right = replacement;
                case BinaryNodeType.ROOT -> root = replacement;
            }

        }

        return found;
    }
}
