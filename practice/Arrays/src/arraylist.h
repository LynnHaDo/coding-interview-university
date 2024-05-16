typedef struct arraylist {
    int size, capacity;
    int * data;
} arraylist;

typedef arraylist * arraylist_ptr;

// Constructor
arraylist * create_arraylist();

int size(arraylist * a);
int capacity(arraylist * a);
int is_empty(arraylist * a);
// Return element at the index
int * at(arraylist * a, int index);
// Add item to the end
void push(arraylist * a, int item);
// Inserts item at index, shifts that index's value and trailing elements to the right
void insert(arraylist * a, int item, int index); 
// Insert above at index 0
void prepend(arraylist * a, int item);
// Remove from end, return value
int pop(arraylist * a);
// Delete item at index, shifting all trailing elements left
void delete(arraylist * a, int index);
// Looks for value and removes index holding it (even if in multiple places)
void remove_item(arraylist * a, int item);
// Looks for value and returns first index with that value, -1 if not found
int find(arraylist * a, int item);
// Private function:
    // When you reach capacity, resize to double the size
    // When popping an item, if the size is 1/4 of capacity, resize to half
void resize(arraylist * a, int new_capacity);

void destroy(arraylist * self);