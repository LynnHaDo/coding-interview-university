#include <stdio.h>
#include <stdlib.h>
#include "hashtable.h"

const optional_int INVALID = {0, 0};

// Constructor
hashtable * create_hashtable(int arr_size) 
{
    hashtable * h = malloc(sizeof(hashtable));

    if (h == NULL) {
        return NULL;
    }

    h->size = arr_size;
    h->data = malloc(sizeof(item) * arr_size);

    return h;
}

// Return the position of a given key
// - m is the size of the hash table
int hash(hashtable * h, int k, int m)
{
    return k % m;
}

// If the key already exists, update value
void add(hashtable * h, int key, int value)
{
    // Linear probing
    if (exists(h, key)) {
        int hashIndex = hash(h, key, h->size);
        int i = hashIndex;

        if (hashIndex == h->size - 1) {
            i = 0; // wrap around to find the location for insertion
        }

        for (; i < h->size; i++) {
            // Empty spot
            if ((h->data + i) == NULL) {
                (h->data + i)->key = key;
                (h->data + i)->value = value;
                return;
            }
        }
    }
    // No available spot otherwise; update value
    (h->data + hash(h, key, h->size))->key = key;
    (h->data + hash(h, key, h->size))->value = value;
}

/**
 * Assume table is non-null
 */
int indexof(hashtable * h, int key) {
    if (h->data + hash(h, key, h->size) == NULL) {
        int hashIndex = hash(h, key, h->size);
        int i = hashIndex;

        if (hashIndex == h->size - 1) {
            i = 0; // wrap around to find the location for insertion
        }

        for (; i < h->size; i++) {
            if ((h->data + i) == NULL) {
                continue;
            }

            if ((h->data + i)->key == key) {
                return i; 
            }
        }
    } 
    else {
        return hash(h, key, h->size);
    }

    return -1;
}

// Return true if the key already exists
int exists(hashtable * h, int key)
{
    if (h == NULL || h->data == NULL) 
    {
        return 0;
    }

    return indexof(h, key) >= 0;
}

// Get the value associated with a given key
optional_int get_key(hashtable * h, int key)
{
    if (exists(h, key)) {
        optional_int item = {1, (h->data + indexof(h, key))->value};
        return item;
    } 

    return INVALID;
}

// Remove a key from the hash table and return the value associated with that key
optional_int remove_key(hashtable * h, int key)
{
    if (exists(h, key)) {
        optional_int item = {1, (h->data + indexof(h, key))->value};
        free(h->data + indexof(h, key));

        //h->data + indexof(h, key) = NULL;
        return item;
    } 

    return INVALID;
}

void print_hashtable(hashtable * h) {
    if (h->size == 0) 
    {
        printf("[]");
    } else 
    {
        printf("[");
        for (int i = 0; i < h->size; i++)
        {
            item * item = (h->data + i);

            if (item == NULL) {
                printf("NULL");
                continue;
            }
            
            printf("(%i, %i),", item->key, item->value);
        }
        printf("]");
        printf("\n");
    }
}


int main() {
    hashtable * h = create_hashtable(5);
    print_hashtable(h);
    printf("%i\n", exists(h, 23)); // 0
    printf("%i\n", indexof(h, 23)); // 1
    add(h, 23, 3);
    printf("%i\n", exists(h, 23)); // 1
    
    print_hashtable(h);

    return 0;
}
