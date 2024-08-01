package leetcode.src;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class TrieNode {
    String val;
    HashMap<String, TrieNode> children;
    boolean endString;

    public TrieNode(){};

    public TrieNode(String val) {
        this.val = val;
        this.endString = false;
    }

    public String getValue() {
        return this.val;
    }

    public HashMap<String, TrieNode> getChildren() {
        return this.children;
    }

    public void addChild(String prefix, TrieNode child) {
        if (this.children == null) {
            this.children = new HashMap<String, TrieNode>();
        }

        this.children.put(prefix, child);
    }
}

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to
 * efficiently store and retrieve keys in a dataset of strings. There are
 * various applications of this data structure, such as autocomplete and
 * spellchecker.
 */
public class Trie {
    TrieNode root;
    List<String> strings;

    /**
     * Initializes the trie object.
     */
    public Trie() {
        strings = new ArrayList<>();
    }

    public TrieNode getRoot() {
        return this.root;
    }

    public void printTrie(TrieNode root, String string) {
        if (root == null) {
            return;
        }

        if (root.endString) {
            strings.add(string);
        }

        char chr = 'a';

        for (int i = 0; i < 26; i++) {
            if (root.getChildren() != null && root.getChildren().containsKey(Character.toString(chr))) {
                TrieNode child = root.getChildren().get(Character.toString(chr));
                string += chr;
                printTrie(child, string);
            }
            chr += 1;
        }
    }

    /**
     * Inserts the string word into the trie.
     * 
     * @param word
     */
    public void insert(String word) {
        // Empty 
        if (root == null) {
            root = new TrieNode(""); // init root to be a node with empty string
        }

        TrieNode cur = root;
        
        for (int i = 0; i < word.length(); i++) {
            if (cur.getChildren() == null || 
                cur.getChildren().get(Character.toString(word.charAt(i))) == null) {
                TrieNode node = new TrieNode(Character.toString(word.charAt(i))); 
                cur.addChild(Character.toString(word.charAt(i)), node);
                cur = node;
                continue;
            }

            // Get the child node containing the current character
            cur = cur.getChildren().get(Character.toString(word.charAt(i)));
        }

        cur.endString = true;
    }

    /**
     * Returns true if the string word is in the trie (i.e., was inserted before),
     * and false otherwise.
     * 
     * @param word lowercase word to search for
     * @return true if the string word is in the trie (i.e., was inserted before),
     * and false otherwise.
     */
    public boolean search(String word) {
        if (root == null) {
            return false;
        }

        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            if (cur == null || cur.getChildren() == null) {
                return false;
            }
            
            // Get the child node containing the current character
            TrieNode childContainingChar = cur.getChildren().get(Character.toString(word.charAt(i)));
            if (childContainingChar == null) {
                return false;
            }
            // If the word has ended, but the word still continues
            if (i == word.length() - 1 && childContainingChar.endString) {
                return true;
            }

            if (i == word.length() - 1 && !childContainingChar.endString) {
                return false;
            }

            cur = childContainingChar;
        }

        return true;
    }

    /**
     * Returns true if there is a previously inserted string word that has the
     * prefix, and false otherwise.
     * 
     * @param prefix lowercase prefix to check for in the string 
     * @return true if there is a previously inserted string word that has the
     * prefix, and false otherwise.
     */
    public boolean startsWith(String prefix) {
        if (root == null) {
            return false;
        }

        TrieNode cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            if (cur == null || cur.getChildren() == null) {
                return false;
            }
            // Get the child node containing the current character
            TrieNode childContainingChar = cur.getChildren().get(Character.toString(prefix.charAt(i)));
            
            if (childContainingChar == null) {
                return false;
            }

            cur = childContainingChar;
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");
        trie.printTrie(trie.root, "");
        System.out.println(Arrays.toString(trie.strings.toArray()));
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */