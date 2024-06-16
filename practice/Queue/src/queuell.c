#include <stdio.h>
#include <stdlib.h>
#include "queuell.h"

const optional_int INVALID = {0, 0};

queue * create_queue() {
    queue * q = malloc(sizeof(struct queue));

    if (q == NULL) {
        return NULL;
    }

    q->head = NULL;
    q->tail = NULL;

    return q;
}

// Adds value at a position at the tail
void enqueue(queue * q, int value) {
    node * newNode = malloc(sizeof(struct node));

    if (newNode == NULL) {
        return;
    }

    newNode->value = value;
    newNode->next = q->tail;

    if (empty(q)) {
        q->head = newNode; 
        return;
    }

    node * cur = q->head;

    while (cur->next != NULL) {
        cur = cur->next;
    }

    cur->next = newNode;
}

// Returns value and removes least recently added element (front)
optional_int dequeue(queue * q) {
    if (empty(q)) {
        return INVALID;
    }

    optional_int nodeVal = {1, (q->head)->value};
    
    if ((q->head)->next == NULL) {
        q->head = NULL;
        return nodeVal;
    }

    q->head = (q->head)->next;
    return nodeVal;
}

// Check whether the queue is empty
int empty(queue * q) {
    return q->head == NULL;
}

void print_queue(node * head) {
    node * cur = head;

    while (cur != NULL) {
        printf("%i -> ", cur->value);
        cur = cur->next;
    }

    printf("\n");
}

int main() {
    queue * q = create_queue();

    printf("isEmpty: %i\n", empty(q));
    enqueue(q, 24);
    enqueue(q, 23);
    enqueue(q, 3);
    enqueue(q, 2003);
    printf("isEmpty: %i\n", empty(q));
    print_queue(q->head);

    printf("Dequeue: %i\n", dequeue(q).value);
    print_queue(q->head);
    printf("Dequeue: %i\n", dequeue(q).value);
    print_queue(q->head);
    printf("Dequeue: %i\n", dequeue(q).value);
    print_queue(q->head);
    printf("Dequeue: %i\n", dequeue(q).value);
    print_queue(q->head);
    printf("Dequeue: %i\n", dequeue(q).value); // invalid
    print_queue(q->head);

    return 0;
}



