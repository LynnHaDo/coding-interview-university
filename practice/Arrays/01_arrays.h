#include <stdbool.h>

struct Array{
    int size, capacity;
    int * array;
};

typedef struct Array Array;

// Constructor
void array(Array * a){
    a->size = 0;
    a->capacity = 10; // imitate behavior of ArrayList in Java
}

int size(Array * a);
int capacity(Array * a);
bool is_empty(Array * a);
// Return element at the index
int at(Array * a, int index);
// Add item to the end
void push(Array * a, int item);
// Inserts item at index, shifts that index's value and trailing elements to the right
void insert(Array * a, int item, int index); 
// Insert above at index 0
void prepend(Array * a, int item);
// Remove from end, return value
int pop(Array * a);
// Delete item at index, shifting all trailing elements left
void delete(Array * a, int index);
// Looks for value and removes index holding it (even if in multiple places)
void remove_item(Array * a, int item);
// Looks for value and returns first index with that value, -1 if not found
void find(Array * a, int item);
// Private function:
    // When you reach capacity, resize to double the size
    // When popping an item, if the size is 1/4 of capacity, resize to half
void resize(Array * a, int new_capacity);