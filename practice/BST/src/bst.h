typedef struct optional_int {
    int valid;
    int value;
}
optional_int;

typedef struct bst_node {
    int value;
    struct bst_node * left;
    struct bst_node * right;
}
bst_node;

typedef struct bst {
    struct bst_node * root;
}
bst;

bst * create_bst();

// insert value into tree](https://leetcode.com/problems/insert-into-a-binary-search-tree/submissions/987660183/)
void insert(bst * t, int value);    

// get count of values stored
int size(bst_node * node);

// prints the values in the tree, from min to max
void print_values(bst_node * t);

// delete the tree
void delete_tree(bst * t);

// returns true if a given value exists in the tree
int is_in_tree(bst * t);

// returns the height in nodes (single node's height is 1)](https://www.geeksforgeeks.org/find-the-maximum-depth-or-height-of-a-tree/)
int get_height(bst * t);

// returns the minimum value stored in the tree
optional_int get_min(bst * t);

// returns the maximum value stored in the tree
optional_int get_max(bst * t);   

// Validate BST https://leetcode.com/problems/validate-binary-search-tree/
int is_binary_search_tree(bst * t);

// Delete a value from the BST (if any)
int delete_value(bst * t, int value);

// Returns the next-highest value in the tree after given value, -1 if none
int get_successor(bst * t, int value); 