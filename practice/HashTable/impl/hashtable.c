#include <stdio.h>
#include <stdlib.h>
#include "hashtable.h"

const optional_int INVALID = {0, 0};

// Constructor
hashtable * create_hashtable(int arr_size) 
{
    if (arr_size <= 0) {
        return NULL;
    }

    hashtable * h = (hashtable *) malloc(sizeof(hashtable));

    if (h == NULL) {
        return NULL;
    }

    h->size = 0;
    h->capacity = arr_size;
    item * data = (item *) calloc(sizeof(item), arr_size);
    h->data = &data;

    if (*(h->data) == NULL) {
        free(h);
        return NULL;
    }

    return h;
}

// Return the position of a given key
// - m is the size of the hash table
int hash(hashtable * h, int k)
{
    return k % h->capacity;
}

// If the key already exists, update value
void add(hashtable * h, int key, int value)
{
    item * cur = *(h->data) + hash(h, key);
    // Linear probing
        if (exists(h, key)) {
            
            int i = hash(h, key);
            int count = 0;

            for (; count <= h->capacity; count++) {
                if (i == h->capacity - 1) {
                    cur = *(h->data); // wrap around to find the location for insertion
                }

                // Empty spot
                if (cur == NULL) {
                    cur->key = key;
                    cur->value = value;
                    h->size += 1;
                    return;
                }
                cur++;
                i++;
            }
        }


        // No available spot or spot not taken otherwise; update value
        cur->key = key;
        cur->value = value;
        h->size += 1;
}

/**
 * Assume table is non-null
 */
int indexof(hashtable * h, int key) {
    if (h->size == 0) {
        return -1;
    }

    // O(n) search: Not in the hash position
    if ( *(h->data) + hash(h, key) == NULL || 
        (*(h->data) + hash(h, key))->key != key) {

        for (int i = 0; i < h->capacity; i++) {
            if ((*(h->data) + i) == NULL) 
            {
                continue;
            }

            if ((*(h->data) + i)->key == key) 
            {
                return i; 
            }
        }

    } 
    
    // O(1) search
    if ((*(h->data) + hash(h, key))->key == key) {
        return hash(h, key);
    }

    return -1;
}

// Return true if the key already exists
int exists(hashtable * h, int key)
{
    if (h == NULL || h->size == 0) 
    {
        return 0;
    }

    return indexof(h, key) >= 0;
}

// Get the value associated with a given key
optional_int get_key(hashtable * h, int key)
{
    if (exists(h, key)) {
        optional_int item = {1, (*(h->data) + indexof(h, key))->value};
        return item;
    } 

    return INVALID;
}

// Remove a key from the hash table and return the value associated with that key
optional_int remove_key(hashtable * h, int key)
{
    if (exists(h, key)) {
        optional_int item = {1, (*(h->data) + indexof(h, key))->value};
        *(h->data + indexof(h, key)) = NULL;
        h->size--;
        return item;
    } 

    return INVALID;
}

void print_hashtable(hashtable * h) {
    if (h->size == 0) 
    {
        printf("[]\n");
    } 
    else 
    {
        printf("[");
        for (int i = 0; i < h->capacity; i++)
        {
            item * it = (*(h->data) + i);

            if (it == NULL) {
                printf("(,)");
                continue;
            }
            
            printf("(%i, %i),", it->key, it->value);
        }
        printf("]");
        printf("\n");
    }
}


int main() {
    hashtable * h = create_hashtable(5);

    add(h, 1, 2);
    add(h, 1, 2);
    

    print_hashtable(h);

    return 0;
}
