package leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {
    Trie trie;
    String word;

    @Before
    public void setup() {
        trie = new Trie();
        word = "apple";
    }

    @Test
    public void testInitTree() {
        /**
         * Input
         * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
         * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
         * Output
         * [null, null, true, false, true, null, true]
         * 
         * Explanation
         * Trie trie = new Trie();
         * trie.insert("apple");
         * trie.search("apple"); // return True
         * trie.search("app"); // return False
         * trie.startsWith("app"); // return True
         * trie.insert("app");
         * trie.search("app"); // return True
         */

        assertNotNull(trie);
        assertNull(trie.getRoot());
    }

    @Test 
    public void testInsertion() {
        trie.insert(word);
        assertTrue(trie.search(word));
    }

    @Test 
    public void testInsertion1() {
        trie.insert(word);
        trie.insert("app");

        assertTrue(trie.startsWith("app"));
        assertTrue(trie.search("app"));
        assertTrue(trie.search(word));
    }

    @Test
    public void testSearch() {
        trie.insert(word);
        assertFalse(trie.search("app"));
    }

    @Test 
    public void testStartWith() {
        trie.insert(word);
        assertTrue(trie.startsWith("app"));
    }

    @Test 
    public void testInsertion2() {
        
    }
}
