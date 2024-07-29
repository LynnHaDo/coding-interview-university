package leetcode.src;

import java.util.List;
import java.util.ArrayList;

class TrieNode {
    String val;
    TrieNode parent;
    List<TrieNode> children;

    public TrieNode(){
        this.children = new ArrayList<TrieNode>();
    };

    public TrieNode(String val) {
        this.val = val;
        this.children = new ArrayList<TrieNode>();
    }

    public String getValue() {
        return this.val;
    }

    public TrieNode getParent() {
        return parent;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    public List<TrieNode> getChildren() {
        return this.children;
    }

    public void addChild(TrieNode child) {
        this.children.add(child);
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

    /**
     * Initializes the trie object.
     */
    public Trie() {}

    /**
     * Inserts the string word into the trie.
     * 
     * @param word
     */
    public void insert(String word) {
        TrieNode cur = root;
        // Empty 
        if (cur == null) {
            root = new TrieNode(""); // init with empty string
            TrieNode node = new TrieNode(Character.toString(word.charAt(0)));
            root.addChild(node);

            for (int i = 1; i < word.length(); i++) {
                node = new TrieNode(Character.toString(word.charAt(i)));
                node.setParent(cur);
                cur = node;
            }

            cur = null; // end of the string

            return;
        }
        
        

        

        
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
        return false;
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
        return false;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */