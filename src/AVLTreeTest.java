import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {
    @Test
    public void testInsert() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        assertEquals("10 20 25 30 40 50 ", tree.getTree());
    }

    @Test
    public void testDelete() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        tree.delete("20");

        assertEquals("10 25 30 40 50 ", tree.getTree());
    }

    @Test
    public void testBalance() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        tree.delete("10");
        tree.delete("20");

        assertEquals("25 30 40 50 ", tree.getTree());
    }

    @Test
    public void testGetLonger() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        List<String> expected = new ArrayList<>(List.of("30", "40", "50"));
        List<String> actual = tree.getLonger("25");
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetShorter() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        List<String> expected = new ArrayList<>(List.of("10", "20", "25"));
        List<String> actual = tree.getShorter("30");
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEqual() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        List<String> expected = new ArrayList<>(List.of("25"));
        List<String> actual = tree.getEqual("25");
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetInRange() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        List<String> expected = new ArrayList<>(List.of("20", "25", "30"));
        List<String> actual = tree.getInRange("20", "30");
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetFirst() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        assertEquals("10", tree.getFirst());
    }

    @Test
    public void testGetLast() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        assertEquals("50", tree.getLast());
    }

    @Test
    public void testIterator() {
        AVLTree tree = new AVLTree();
        tree.insert("10");
        tree.insert("20");
        tree.insert("30");
        tree.insert("40");
        tree.insert("50");
        tree.insert("25");

        StringBuilder result = new StringBuilder();
        for (String key : tree) {
            result.append(key).append(" ");
        }

        assertEquals("10 20 25 30 40 50 ", result.toString());
    }
}