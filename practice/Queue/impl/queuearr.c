#include <stdio.h>
#include <stdlib.h>
#include "queuearr.h"

const optional_int INVALID = {0, 0};

queue_array * create_queue_array() {
    queue_array * q = malloc(sizeof(queue_array *));

    if (q == NULL) {
        return NULL;
    }

    int * arr = (int *)malloc(sizeof(MAX_SIZE * sizeof(int)));

    if (arr == NULL) {
        return NULL;
    }

    q->read = 0;
    q->write = 0;
    q->size = 0;
    q->queue = arr;
    return q;
}

// Is empty?
int empty_array(queue_array * q) {
    return q->size==0;
}

// Is full?
int full_array(queue_array * q) {
    return q->size==MAX_SIZE;
}

void print_queue_array(queue_array * q) {
    if (empty_array(q)) {
        return;
    }


    int i = q->read;
    int count = 0;

    for (; count <= q->size; count++) {
        
        if (i == MAX_SIZE) {
            i = 0;
        }
        printf("%i -> ", *(q->queue + i));
        i++;
    }
}

// Adds item at end of available storage
void enqueue_array(queue_array * q, int value) {
    // Wrap around
    if (q->write == MAX_SIZE) {
        q->write = 0;
    } 
    else if (q->write + 1 == q->read) {
        return; // cannot insert
    }     

    *(q->queue + q->write) = value;
    q->write++;
    q->size++;
}

// Returns value and removes least recently added element
optional_int dequeue_array(queue_array * q) {
    if (empty_array(q)) {
        return INVALID;
    }

    optional_int first_el = {1, *(q->queue + q->read)};
    q->read++;
    q->size--;
    return first_el;
}

int main() {
    queue_array * q = create_queue_array();
    printf("Is empty: %i\n", empty_array(q));

    enqueue_array(q, 12);
    print_queue_array(q);
    
    return 0;
}