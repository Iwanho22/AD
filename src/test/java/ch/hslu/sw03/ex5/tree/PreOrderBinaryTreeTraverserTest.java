package ch.hslu.sw03.ex5.tree;

import ch.hslu.sw03.ex5.BinaryTreeTraverser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PreOrderBinaryTreeTraverserTest {
    private static final Logger LOG = LoggerFactory.getLogger(PreOrderBinaryTreeTraverserTest.class);
    private final BinaryTree tree = new BinaryTree();

    @BeforeEach
    public void setUp() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(7);
        tree.insert(8);
        tree.insert(13);
        tree.insert(12);
        tree.insert(15);
        tree.insert(14);
    }

    @Test
    public void traverse_LogToConsole() {
        // arrange
        BinaryTreeTraverser<Integer> traverser = new PreOrderBinaryTreeTraverser();

        // act
        traverser.traverse(tree, element -> LOG.info("Current Value:{}", element));
    }

    @Test
    public void traverseInor_addToList_shouldHaveSize10() {
        // arrange
        BinaryTreeTraverser<Integer> traverser = new PreOrderBinaryTreeTraverser();
        var list = new ArrayList<>();

        // act
        traverser.traverse(tree, list::add);

        // assert
        assertThat(list.size()).isEqualTo(10);
    }

    @Test
    public void traverse_addToList_orderShouldMatch() {
        // arrange
        BinaryTreeTraverser<Integer> traverser = new PreOrderBinaryTreeTraverser();
        var list = new ArrayList<>();
        var expected = List.of(10, 5, 4, 3, 7, 8, 13, 12, 15, 14);

        // act
        traverser.traverse(tree, list::add);

        // assert
        assertThat(list).isEqualTo(expected);
    }
}