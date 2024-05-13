#include <stdio.h>
#include "01_arrays.h"

// SIZE
int size(Array * a)
{
    return a->size;
}

// CAPACITY
int capacity(Array * a)
{
    return a->capacity;
}

// IS EMPTY
bool is_empty(Array * a)
{
    return a->size == 0;
}

// AT
int at(Array * a, int index)
{
    if (is_empty(a) || index < 0 || index > size(a) - 1)
    {
        return -1;
    }

    return *(a->array + index);
}

// PUSH
void push(Array * a, int item)
{
    // If the array is full, resize
    if (a->size == a->capacity)
    {
        a->capacity = a->capacity * 2;
        int * new_arr[a->capacity];

        for (int i = 0; i < a->size; i++)
        {
            *(new_arr + i) = *(a->array + i);
        }

        a->array = new_arr;
    }
    // Update the last element to be the item
    * (a->array + (a->size)) = item;
    a->size = a->size + 1;
}

// INSERT
void insert(Array * a, int item, int index)
{
    // If the array is full, resize
    if (a->size == a->capacity)
    {
        a->capacity = a->capacity * 2;
        int * new_arr[a->capacity];

        for (int i = 0; i < a->size; i++)
        {
            *(new_arr + i) = *(a->array + i);
        }

        a->array = new_arr;
    }

    
    // Update the last element to be the item
    * (a->array + index) = item;
    a->size = a->size + 1;
}
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

void * print_array(Array * a)
{
    if (a->size == 0)
    {
        printf("[]\n");
    }
    else {
        printf("[");
        for (int i = 0; i < a->size; i++)
        {
            printf("%i", *(a -> array + i));
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

int main()
{
    int * my_array;

    array((Array *)my_array);

    printf("size(): %i\n", size((Array *) my_array));
    printf("capacity(): %i\n", capacity((Array *) my_array));
    printf("is_empty(): %i\n", is_empty((Array *) my_array));
    print_array((Array *) my_array);
    printf("at(): %i\n", at((Array *) my_array, 1));

    printf("\n-------\n");

    push((Array *) my_array, 23);
    push((Array *) my_array, 3);
    push((Array *) my_array, 2003);

    printf("size(): %i\n", size((Array *) my_array));
    printf("capacity(): %i\n", capacity((Array *) my_array));
    printf("is_empty(): %i\n", is_empty((Array *) my_array));
    print_array((Array *) my_array);
    printf("at(): %i\n", at((Array *) my_array, 1));


}