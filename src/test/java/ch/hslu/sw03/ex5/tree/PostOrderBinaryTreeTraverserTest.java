package ch.hslu.sw03.ex5.tree;

import ch.hslu.sw03.ex5.BinaryTreeTraverser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostOrderBinaryTreeTraverserTest {
    private static final Logger LOG = LoggerFactory.getLogger(PostOrderBinaryTreeTraverserTest.class);

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
        BinaryTreeTraverser<Integer> traverser = new PostOrderBinaryTreeTraverser();

        // act
        traverser.traverse(tree, element -> LOG.info("Current Value:{}", element));
    }

    @Test
    public void traverseInor_addToList_shouldHaveSize10() {
        // arrange
        BinaryTreeTraverser<Integer> traverser = new PostOrderBinaryTreeTraverser();
        var list = new ArrayList<>();

        // act
        traverser.traverse(tree, list::add);

        // assert
        assertThat(list.size()).isEqualTo(10);
    }

    @Test
    public void traverse_addToList_orderShouldMatch() {
        // arrange
        BinaryTreeTraverser<Integer> traverser = new PostOrderBinaryTreeTraverser();
        var list = new ArrayList<>();
        var expected = List.of(3, 4, 8, 7, 5, 12, 14, 15, 13, 10);

        // act
        traverser.traverse(tree, list::add);

        // assert
        assertThat(list).isEqualTo(expected);
    }
}