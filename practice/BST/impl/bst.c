#include <stdio.h>
#include <stdlib.h>
#include "bst.h"
#include "../../Arrays/src/arraylist.c"

optional_int INVALID_INT = {0, 0};

bst * create_bst() {
    bst * t = (bst *)malloc(sizeof(struct bst));

    if (t == NULL) {
        return NULL;
    }

    t->root = NULL;
    return t;
}

bst_node * get_root(bst * t) {
    return t->root;
}

bst_node * get_new_node() {
    bst_node * new_node = (bst_node *)malloc(sizeof(struct bst_node));

    if (new_node == NULL) {
        return NULL;
    }

    return new_node;
}

// insert value into tree](https://leetcode.com/problems/insert-into-a-binary-search-tree/submissions/987660183/)
void insert_tree(bst * t, int value) {
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

int recursively_count_size(bst_node * node) {
    if (node == NULL) {
        return 0;
    }

    return 1 + recursively_count_size(node->right) + recursively_count_size(node->left);
}

// get count of values stored
int size_tree(bst * t) {
    return recursively_count_size(t->root);
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

void recursively_delete_tree(bst_node * node) {
    if (node == NULL) {
        return;
    }

    recursively_delete_tree(node->left);
    recursively_delete_tree(node->right);

    free(node);
    node = NULL;
}

// delete the tree
void delete_tree(bst * t) {
    if (t->root == NULL) {
        return;
    }

    recursively_delete_tree(t->root);
    t->root = NULL;
}

// returns true if a given value exists in the tree
int is_in_tree(bst * t, int value) {
    if (t->root == NULL) {
        return 0;
    }

    bst_node * cur = t->root;

    while (cur != NULL) {
        // If the value to find is on the left
        if (value < cur->value) {
            cur = cur->left;
        }
        else if (value == cur->value) {
            return 1;
        } else {
            cur = cur->right;
        }
    }

    return 0;
}

int recursively_count_depth(bst_node * node) {
    if (node == NULL) {
        return -1;
    }

    int left_height = recursively_count_depth(node->left);
    int right_height = recursively_count_depth(node->right);
    
    if (left_height > right_height) {
        return 1 + left_height;
    } else {
        return 1 + right_height;
    }
}

// returns the height in nodes (single node's height is 1)]
// (https://www.geeksforgeeks.org/find-the-maximum-depth-or-height-of-a-tree/)
int get_height(bst * t) {
    if (t->root == NULL) {
        return 1;
    }

    return recursively_count_depth(t->root);
}

// returns the minimum value stored in the tree
optional_int get_min(bst * t) {
    if (t->root == NULL) {
        return INVALID_INT;
    }

    optional_int min = {1, 0};
    bst_node * cur = t->root;

    while (cur->left != NULL) {
        cur = cur->left;
    }

    min.value = cur->value;
    return min;
}

// returns the maximum value stored in the tree
optional_int get_max(bst * t) {
    if (t->root == NULL) {
        return INVALID_INT;
    }

    optional_int max = {1, 0};
    bst_node * cur = t->root;

    while (cur->right != NULL) {
        cur = cur->right;
    }

    max.value = cur->value;
    return max;
}

void inorder_traverse(bst_node * node, arraylist_ptr list) {
    if (node == NULL) {
        return;
    }

    inorder_traverse(node->left, list);
    push(list, node->value);
    inorder_traverse(node->right, list);
}

// Validate BST https://leetcode.com/problems/validate-binary-search-tree/
int is_binary_search_tree(bst * t) {
    if (t->root == NULL) {
        return 1;
    }

    arraylist_ptr inorder_output = create_arraylist();
    inorder_traverse(t->root, inorder_output);
    
    for (int i = 0; i < size(inorder_output) - 1; i++) {
        if (*(inorder_output->data + i) >= *(inorder_output->data + i + 1)) {
            return 0;
        }
    }

    return 1;
}

/**
 * Recursively find the node and delete it
 * @param node root node to start with
 * @param value key to look up for
 */
bst_node * recursively_delete_value(bst_node * node, int value) {
    if (node == NULL) {
        return NULL;
    }

    if (value < node->value) {
        node->left = recursively_delete_value(node->left, value);
    }
    else if (value > node->value) {
        node->right = recursively_delete_value(node->right, value);
    } 
    else {
        // Leaf node
        if (node->left == NULL && node->right == NULL) {
            free(node);
            return NULL;
        }
        // One child
        else if (node->left == NULL && node->right != NULL) {
            return node->right;
        }
        else if (node->left != NULL && node->right == NULL) {
            return node->left;
        }
        // Two children
        else {
            bst_node * temp = node->right;
            // Find the minimum of the right subtree
            while (temp->left != NULL) {
                temp = temp->left;
            }

            node->value = temp->value;
            node->right = recursively_delete_value(node->right, value);
        }

    }
    return node;
}

// Delete a value from the BST (if any)
void delete_value(bst * t, int value) {
    if (t->root == NULL) {
        return;
    }

    recursively_delete_value(t->root, value);
}

// Returns the next-highest value in the tree after given value, -1 if none
optional_int get_successor(bst * t, int value) {
    bst_node * root = t->root;
    bst_node * cur = t->root;

    // Find the node containing the passed in value
    while (cur != NULL) {
        if (value < cur->value) {
            cur = cur->left;
        } 
        else if (value > cur->value) {
            cur = cur->right;
        }
        else {
            bst_node * successor = NULL;
            optional_int successor_int = INVALID_INT;

            if (cur->right != NULL) {
                // find the minimum of the right subtree
                cur = cur->right;
                while (cur->left != NULL) {
                    cur = cur->left;
                }
                successor_int.valid = 1;
                successor_int.value = cur->value;
            }
            else {
                // Otherwise, search from the root 
                while (root != NULL) {
                    if (value < root->value) {
                        successor = root;
                        root = root->left;
                    } 
                    else if (value > root->value) {
                        root = root->right;
                    } 
                    else {
                        break;
                    }
                }

                if (successor != NULL) {
                    successor_int.valid = 1;
                    successor_int.value = successor->value;
                }
            }

            return successor_int;
        }
    }
    
    // The given value is not found in the tree
    return INVALID_INT;
}

int main() {
    bst * bst = create_bst();

    insert_tree(bst, 10);
    print_values(bst->root);
    printf("\n");

    insert_tree(bst, 12);
    insert_tree(bst, 4);
    insert_tree(bst, 100);
    insert_tree(bst, 13);
    print_values(bst->root);
    printf("\n");
    printf("%i\n", size_tree(bst));

    //delete_tree(bst);
    printf("Height: %i\n", get_height(bst));
    printf("Min: %i\n", get_min(bst).value);
    printf("Max: %i\n", get_max(bst).value);

    delete_value(bst, 12);
    print_values(bst->root);
    printf("\n");
    printf("%i\n", size_tree(bst));

    return 0;
}