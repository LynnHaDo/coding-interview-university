#include <stdio.h>
#include <stdlib.h>
#include "bst.h"

const optional_int INVALID = {0, 0};

bst * create_bst() {
    bst * t = (bst *)malloc(sizeof(struct bst));

    if (t == NULL) {
        return NULL;
    }

    t->root = NULL;
    return t;
}

// insert value into tree](https://leetcode.com/problems/insert-into-a-binary-search-tree/submissions/987660183/)
void insert(bst * t, int value) {
    bst_node * new_node = (bst_node *)malloc(sizeof(struct bst_node));
    new_node->value = value;

    if (t->root == NULL) {
        t->root = new_node;
        return;
    }

    bst_node * cur = t->root;

    while (cur != NULL) {
        // If value to be inserted should be on the right
        if (value > cur->value) {
            if (cur->right == NULL) {
                cur->right = new_node;
                return;
            }
            cur = cur->right;
        } 
        else {
            if (cur->left == NULL) {
                cur->left = new_node;
                return;
            }
            cur = cur->left;
        } 
    }
}

// get count of values stored
int size(bst_node * node) {
    if (node == NULL) {
        return 0;
    }

    return 1 + size(node->right) + size(node->left);
}

// prints the values in the tree, from min to max
// inorder traversal
void print_values(bst_node * node) {
    if (node == NULL) {
        return;
    }

    print_values(node->left);
    printf("%i, ", node->value);
    print_values(node->right);
}

// delete the tree
void delete_tree(bst * t);

// returns true if a given value exists in the tree
int is_in_tree(bst * t);

// returns the height in nodes (single node's height is 1)](https://www.geeksforgeeks.org/find-the-maximum-depth-or-height-of-a-tree/)
int get_height(bst * t);

// returns the minimum value stored in the tree
optional_int get_min(bst * t) {
    if (t->root == NULL) {
        return INVALID;
    }

    optional_int min = {1, 0};

    while ()
}

// returns the maximum value stored in the tree
optional_int get_max(bst * t);   

// Validate BST https://leetcode.com/problems/validate-binary-search-tree/
int is_binary_search_tree(bst * t);

// Delete a value from the BST (if any)
int delete_value(bst * t, int value);

// Returns the next-highest value in the tree after given value, -1 if none
int get_successor(bst * t, int value); 

int main() {
    bst * bst = create_bst();

    insert(bst, 10);
    print_values(bst->root);
    printf("\n");

    insert(bst, 12);
    insert(bst, 4);
    insert(bst, 100);
    insert(bst, 13);
    print_values(bst->root);

    return 0;
}