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
        exit(0);
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

// Remove the front item and return its value
int pop_front(node ** head) {
    if (*head == NULL) {
        printf("Empty list");
        exit(0);
    }
    
    int value = (*head)->value;
    *head = (*head)->next;
    return value;
}

// Adds an item at the end
void push_back(node ** head, int value) {
    node * n = malloc(sizeof(node));

    if (n != NULL) {
        n->value = value;
        n->next = NULL;
        node * cur = *head;

        if (cur == NULL) {
            *head = n; 
        } 
        else {
            while (cur->next != NULL) {
                cur = cur->next;
            }
            cur->next = n;
        }
    }
}

// Removes end item and returns its value
int pop_back(node ** head) {
    if (*head == NULL) {
        printf("Empty list");
        exit(0);
    }

    node * cur = *head;
    int value = cur->value;

    if (cur->next != NULL) {
        while (cur->next->next != NULL) {
            cur = cur->next;
        }
        value = (cur->next)->value;
        cur->next = NULL;
    } else {
        *head = NULL;
    }

    return value;
}

// Get the value of the front item
int front(node ** head) {
    if (*head == NULL) {
        printf("Empty list");
        exit(0);
    }

    return (*head)->value;
}

// Get the value of the end item
int back(node ** head) {
    if (*head == NULL) {
        printf("Empty list");
        exit(0);
    }

    node * cur = *head;

    while (cur->next != NULL) {
        cur = cur->next;
    }

    return cur->value;
}

// Insert value at index, so the current item at that index is pointed to by the new item at the index
void insert(node ** head, int index, int value) {
    if (index < 0 || (index >= size(head) && size(head) > 0) || (index > 0 && size(head) == 0)) {
        printf("Index invalid\n");
        return;
    }

    node * n = malloc(sizeof(node));

    if (n != NULL) {
        n->value = value;

        int c = 0;
        node * cur = *head;

        if (cur == NULL || index == 0) {
            n->next = *head;
            *head = n;
            return;
        }

        while (cur != NULL) {
            if (c == index - 1) {
                n->next = cur->next;
                cur->next = n;
                break;
            }
            c++;
            cur = cur->next;
        }
    }
}

// Removes node at given index
void erase(node ** head, int index) {
    
}

// Returns the value of the node at the nth position from the end of the list
int value_n_from_end(node ** head, int n) {

}

void print_list(node ** head) {
    node * cur = *head;
    while (cur != NULL) {
        printf("%i -> ", cur->value);
        cur = cur->next;
    }
}

int main() {
    node ** head = create_linkedlist();
    
    insert(head, 1, 20);
    insert(head, 0, 12321);
    push_back(head, 69);
    push_front(head, 32);
    insert(head, 2, 20);
    insert(head, 0, 12);
    insert(head, 1, 2312);

    
    print_list(head);
}


