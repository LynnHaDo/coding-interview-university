#include <stdio.h>
#include <stdlib.h>
#include "arraylist.h"

optional_int_list INVALID = {0, 0};

arraylist_ptr create_arraylist() {
    arraylist_ptr list = (arraylist_ptr)calloc(sizeof(arraylist), 1);

    if (list == NULL){
        return NULL;
    }

    list->size = 0;
    list->capacity = 10;
    list->data = (int *)malloc(sizeof(int) * 10);

    return list;
}

arraylist_ptr create_arraylist_with_capacity(int capacity) {
    arraylist_ptr list = (arraylist_ptr)calloc(sizeof(arraylist), 1);

    if (list == NULL){
        return NULL;
    }

    list->size = 0;
    list->capacity = capacity;
    list->data = (int *)malloc(sizeof(int) * capacity);

    return list;
}

// SIZE
int size(arraylist * a)
{
    return a->size;
}

// CAPACITY
int capacity(arraylist * a)
{
    return a->capacity;
}

// IS EMPTY
int is_empty(arraylist * a)
{
    return a->size == 0;
}

// AT
int * at(arraylist * a, int index)
{
    if (is_empty(a) || index < 0 || index >= size(a))
    {
        return NULL;
    }

    return a->data + index;
}

// PUSH
void push(arraylist * a, int item)
{
    if (a->size >= a->capacity)
    {
        resize(a, a->capacity * 2);
    } 

    // Update the last element to be the item
    *(a->data + (a->size)) = item;
    a->size++;
}

// INSERT
void insert(arraylist * a, int item, int index)
{
    // Invalid indices
    if (index < 0 || index > a->size)
    {
        return;
    }

    // If the arraylist is full, resize
    if (a->size >= a->capacity)
    {
        resize(a, a->capacity * 2);
    } 
    
    for (int i = a->size; i > index; i--){
        // Shift items to the right
        *(a->data + i) = *(a->data + i - 1);
    };
    
    // Item to be inserted
    *(a->data + index) = item;
    a->size++;
}

// Insert above at index 0
void prepend(arraylist * a, int item){
    // If the arraylist is full, resize
    if (a->size >= a->capacity)
    {
        resize(a, a->capacity * 2);
    } 

    for (int i = a->size; i > 0; i--){
        // Shift items to the right
        *(a->data + i) = *(a->data + i - 1);
    };
    
    // Item to be inserted
    *(a->data) = item;
    a->size++;
}

// Remove from end, return value
optional_int_list pop(arraylist * a)
{
    if (is_empty(a))
    {
        return INVALID;
    }

    optional_int_list last = {1, *(a->data + (a->size))};

    free(a->data + a->size);
    a->size--;
    
    if (a->size <= a->capacity/4)
    {
        resize(a, a->capacity / 2);
    }

    return last;
}

// Delete item at index, shifting all trailing elements left
void delete(arraylist * a, int index)
{
    // Invalid cases
    if (is_empty(a) || index < 0 || index > a->size)
    {
        return;
    }

    for (int i = index+1; i < a->size; i++){
        // Shift items to the left
        *(a->data + i - 1) = *(a->data + i);
    };

    a->size--;

    if (a->size <= a->capacity/4)
    {
        resize(a, a->capacity / 2);
    }
}

// Looks for value and removes index holding it (even if in multiple places)
void remove_item(arraylist * a, int item)
{
    // Invalid cases
    if (is_empty(a))
    {
        return;
    }

    int * new = (int *)malloc(sizeof(int) * (a->capacity));
    int j = 0;

    for (int i = 0; i < a->size; i++){
        if (*(a->data + i) != item)
        {
            *(new + j) = *(a->data + i);
            j++;
        }
    };

    if (j > 0)
    {
        free(a->data);
        a->data = new;
        a->size = j;

        if (a->size < a->capacity/4)
        {
            resize(a, a->capacity / 2);
        } 
        else if (a->size >= a->capacity)
        {
            resize(a, a->capacity * 2);
        }
    } 
}

// Looks for value and returns first index with that value, -1 if not found
int find(arraylist * a, int item)
{
    if (is_empty(a))
    {
        return -1;
    }

    for (int i = 0; i < a->size; i++){
        if (*(a->data + i) == item)
        {
            return i;
        }
    };

    return -1;
}

// Private function:
    // When you reach capacity, resize to double the size
    // When popping an item, if the size is 1/4 of capacity, resize to half
void resize(arraylist * a, int new_capacity)
{
    a->capacity = new_capacity;

    int * new_arr = (int *)malloc(sizeof(int) * (a->capacity));

    for (int i = 0; i < a->size; i++)
    {
        *(new_arr + i) = *(a->data + i);
    }
    free(a->data);
    a->data = new_arr;
}

void destroy(arraylist * a)
{
    free(a);
}

void print_arraylist(arraylist * a)
{
    if (a->size == 0)
    {
        printf("[]\n");
    }
    else {
        printf("[");
        for (int i = 0; i < a->size; i++)
        {
            printf("%i", *(a->data + i));
            // Last element
            if (i == a->size - 1)
            {
                printf("%c", ']');
                printf("\n");
            } 
            else
            {
                printf("%c", ',');
            }
        }
    }
}