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
int value_at(node ** head, int index);

// Adds an item to the front of the list
void push_front(node ** head, int value);

/**
// Remove the front item and return its value
int pop_front();
// Adds an item at the end
void push_back(int value);
// Removes end item and returns its value
int pop_back();
// Get the value of the front item
int front();
// Get the value of the end item
int back();
// Insert value at index, so the current item at that index is pointed to by the new item at the index
void insert(int index, int value);
// removes node at given index
void erase(int index);
// Returns the value of the node at the nth position from the end of the list
int value_n_from_end(int n);
// Reverses the list
void reverse();
// removes the first item in the list with this value
void remove_value(int value);
*/