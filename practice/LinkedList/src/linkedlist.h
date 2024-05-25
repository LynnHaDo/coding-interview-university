typedef struct optional_int {
    int valid;
    int value;
}
optional_int;

typedef struct node {
    int value;
    struct node * next;
    // cannot do: "node * next" without adding "node" after struct
    // because of the "declaration before use" rule in C
}
node;

typedef node ** nodePtr;

nodePtr create_linkedlist();

// Returns the number of data elements in the list
int size(node ** head);

// Bool returns true if empty
int empty(node ** head); 

// Returns the value of the nth item (starting at 0 for first)
optional_int value_at(node ** head, int index);

// Adds an item to the front of the list
void push_front(node ** head, int value);

// Remove the front item and return its value
optional_int pop_front(node ** head);

// Adds an item at the end
void push_back(node ** head, int value);

// Removes end item and returns its value
optional_int pop_back(node ** head);

// Get the value of the front item
optional_int front(node ** head);

// Get the value of the end item
optional_int back(node ** head);

// Insert value at index, so the current item at that index is pointed to by the new item at the index
void insert(node ** head, int index, int value);

// Removes node at given index
void erase(node ** head, int index);

// Returns the value of the node at the nth position from the end of the list
optional_int value_n_from_end(node ** head, int n);

// Reverses the list
void reverse(node ** head);

// removes the first item in the list with this value
void remove_value(node ** head, int value);