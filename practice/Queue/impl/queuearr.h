#include "queuell.h"

const int MAX_SIZE = 5;

typedef struct queue_array {
    int read, write, size;
    int * queue;
}
queue_array;

queue_array * create_queue_array();

// Adds item at end of available storage
void enqueue_array(queue_array * q, int value);

// Returns value and removes least recently added element
optional_int dequeue_array(queue_array * q);

// Is empty?
int empty_array(queue_array * q);

// Is full?
int full_array(queue_array * q);