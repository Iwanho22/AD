package ch.hslu.sw03.ex5.tree;

import ch.hslu.sw03.ex5.BinaryTreeTraverser;
import ch.hslu.sw03.ex5.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryTreeTest {
    private final Tree<Integer> tree = new BinaryTree();

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

    @CsvSource({
            "10,true",
            "5,true",
            "4,true",
            "3,true",
            "2,false",
            "7,true",
            "8,true",
            "13,true",
            "12,true",
            "15,true",
            "14,true",
            "27,false",
    })
    @ParameterizedTest
    public void testSearch(int number, boolean expected) {
        // act
        var res = tree.search(number);

        // assert
        assertThat(res).isEqualTo(expected);
    }

    @Test
    public void testInsert_treeShouldChange() {
        // act
        var res = tree.insert(17);

        // assert
        assertThat(res).isTrue();
    }

    @Test
    public void testInsert_shouldContain() {
        // act
        tree.insert(17);

        // assert
        assertThat(tree.search(17)).isTrue();
    }

    @Test
    public void testRemove_withLeaf_shouldNotContain() {
        // act
        tree.remove(3);

        // assert
        assertThat(tree.search(3)).isFalse();
    }

    @Test
    public void testRemove_withRoot_ShouldNotContain() {
        // act
        tree.remove(10);

        // assert
        assertThat(tree.search(10)).isFalse();
    }

    @Test
    public void testRemove_withRoot_rootShouldBe12() {
        // arrange
        var list = new ArrayList<Integer>();
        BinaryTreeTraverser<Integer> traverser = new PreOrderBinaryTreeTraverser();

        // act
        tree.remove(10);

        // assert
        traverser.traverse((BinaryTree) tree, list::add);
        assertThat(list.getFirst()).isEqualTo(12);
    }

    @Test
    public void testRemove_withNodeWithTwoChildren_shouldNotContain() {
        // act
        tree.remove(13);

        // assert
        assertThat(tree.search(13)).isFalse();
    }

    @Test
    public void testRemove_withNodeWithTwoChildren_inOrderShouldBeCorrect() {
        // arrange
        var list = new ArrayList<Integer>();
        BinaryTreeTraverser<Integer> traverser = new InOrderBinaryTreeTraverser();

        // act
        tree.remove(13);

        // assert
        traverser.traverse((BinaryTree) tree, list::add);
        assertThat(list).containsExactly(3, 4, 5, 7, 8, 10, 12, 14, 15);
    }

    @Test
    public void testRemove_withNodeWithOneChild_shouldNotContain() {
        // act
        tree.remove(4);

        // assert
        assertThat(tree.search(4)).isFalse();
    }

    @Test
    public void testRemove_withNodeWithOneChild_inOrderShouldBeCorrect() {
        // arrange
        var list = new ArrayList<Integer>();
        BinaryTreeTraverser<Integer> traverser = new InOrderBinaryTreeTraverser();

        // act
        tree.remove(4);

        // assert
        traverser.traverse((BinaryTree) tree, list::add);
        assertThat(list).containsExactly(3, 5, 7, 8, 10, 12, 13, 14, 15);
    }
}