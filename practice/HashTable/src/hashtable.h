typedef struct optional_int {
    int valid, value;
}
optional_int;

/**
 * Consist of a key and value 
 */
typedef struct item {
    int key, value;
}
item;

typedef struct hashtable {
    int size; // capacity of the array
    item * data; // array of items
}
hashtable;

// Constructor
hashtable * create_hashtable(int arr_size);

// Return the position of a given key
// - m is the size of the hash table
int hash(hashtable * h, int k, int m);

// If the key already exists, update value
void add(hashtable * h, int key, int value);

int indexof(hashtable * h, int key);

// Return true if the key already exists
int exists(hashtable * h, int key);

// Get the value associated with a given key
optional_int get_key(hashtable * h, int key);

// Remove a key from the hash table and return the value associated with that key
optional_int remove_key(hashtable * h, int key);