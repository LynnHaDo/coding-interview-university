#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"

const optional_int INVALID = {0, 0};

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
optional_int value_at(node ** head, int index) {
    if (index < 0 || index >= size(head)) {
        return INVALID;
    }

    int c = 0;
    node * cur = *head;
    optional_int result = {1, 0};

    while (cur != NULL) {
        if (c == index) {
            break;
        }
        c++;
        cur = cur->next;
    }

    result.value = cur->value;

    return result;
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
optional_int pop_front(node ** head) {
    if (empty(head)) {
        return INVALID;
    }
    
    optional_int result = {1, 0};
    result.value = (*head)->value;
    *head = (*head)->next;
    return result;
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
optional_int pop_back(node ** head) {
    if (empty(head)) {
        return INVALID;
    }

    node * cur = *head;
    optional_int result = {1, cur->value};

    if (cur->next != NULL) {
        while (cur->next->next != NULL) {
            cur = cur->next;
        }
        result.value = (cur->next)->value;
        cur->next = NULL;
    } else {
        *head = NULL;
    }

    return result;
}

// Get the value of the front item
optional_int front(node ** head) {
    if (empty(head)) {
        return INVALID;
    }
    optional_int result = {1, (*head)->value};

    return result;
}

// Get the value of the end item
optional_int back(node ** head) {
    if (empty(head)) {
        return INVALID;
    }

    node * cur = *head;
    while (cur->next != NULL) {
        cur = cur->next;
    }

    optional_int result = {1, cur->value};
    return result;
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
    if (empty(head)) {
        printf("Empty list\n");
        return;
    }

    if (index < 0 || index >= size(head)) {
        printf("Invalid index\n");
        return;
    }

    if (index == 0) {
        *head = (*head)->next;
        return;
    }

    node * cur = *head;
    int c = 0;

    while (cur != NULL) {
        if (c == index - 1) {
            cur->next = cur->next->next;
            break;
        }
        c++;
        cur = cur->next;
    }
}

// Returns the value of the node at the nth position from the end of the list
optional_int value_n_from_end(node ** head, int n) {
    if (empty(head)) {
        return INVALID;
    }

    int index = size(head) - n;

    if (index < 0 || index >= size(head)) {
        return INVALID;
    }

    node * cur = *head;
    int c = 0;

    while (cur != NULL) {
        if (c == index) {
            break;
        }
        c++;
        cur = cur->next;
    }
    optional_int result = {1, cur->value};
    return result;
}

// Reverses the list
void reverse(node ** head) {
    // 0 elements
    if (empty(head)) {
        return;
    }
    // 1 element
    if ((*head)->next == NULL) {
        return;
    }

    node * cur = *head;
    node * prev = NULL;
    node * next = NULL;

    while (cur != NULL) {
        next = cur->next;
        cur->next = prev;
        prev = cur;
        cur = next;
    }

    *head = prev;
}

// removes the first item in the list with this value
void remove_value(node ** head, int value) {
    if (empty(head)) {
        return;
    }

    if ((*head)->value == value) {
        *head = (*head)->next;
        return;
    }

    node * cur = *head;
    
    while (cur->next != NULL) {
        if (cur->next->value == value) {
            cur->next = cur->next->next;
            break;
        }

        cur = cur->next;
    }
}


