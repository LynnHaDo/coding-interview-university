#include <stdio.h>
#include <stdlib.h>
#include "queuell.h"

queue create_queue() {
    queue * q = malloc(sizeof(struct queue));

    if (q == NULL) {
        return NULL;
    }

    q->head = NULL;
    q->tail = NULL;

    return queue;
}

// Adds value at a position at the tail
void enqueue(node ** head, node ** tail, int value) {
    node * newNode = malloc(sizeof(struct node *));

    if (newNode == NULL) {
        return NULL;
    }

    newNode->value = value;
    newNode->next = tail;
}

// Returns value and removes least recently added element (front)
optional_int dequeue(node ** head, node ** tail) {
    optional_int nodeVal = {0, 0};

    if (empty(head, tail)) {
        return nodeVal;
    }

    
}

// Check whether the queue is empty
int empty(node ** head, node ** tail) {
    return *head == NULL;
}



