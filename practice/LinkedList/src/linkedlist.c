#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"

nodePtr create_linkedlist() {
    nodePtr list = malloc(sizeof(nodePtr));
    if (list == NULL) {
        return NULL;
    }
    node * head = NULL;
    *list = head;
    return list;
}

// Returns the number of data elements in the list
int size(node ** head) {
    int size = 0;
    for (node * cur = *head; cur != NULL; cur = cur->next) {
        size++;
    }

    return size;
}

// Bool returns true if empty
int empty(node ** head) {
    return *head == NULL;
}

// Returns the value of the nth item (starting at 0 for first)
int value_at(node ** head, int index) {
    if (index < 0 || index >= size(head)) {
        printf("Index invalid");
    }

    int c = 0;
    node * cur = *head;

    while (cur != NULL) {
        if (c == index) {
            break;
        }
        c++;
        cur = cur->next;
    }
    return cur->value;
}

// Adds an item to the front of the list
void push_front(node ** head, int value) {
    node * n = malloc(sizeof(node));
    
    if (n != NULL) {
        n->value = value;
        n->next = *head;
        *head = n;
    }
}


void print(node * head) {
    node * cur = head;
    while (cur != NULL) {
        printf("%i -> ", cur->value);
        cur = cur->next;
    }
}

int main() {
    node ** list = create_linkedlist();
    push_front(list, 23);
    printf("%i\n", size(list));
    push_front(list, 2);
    printf("%i\n", size(list));
    //printf("%i\n", value_at(list, 0));
}


