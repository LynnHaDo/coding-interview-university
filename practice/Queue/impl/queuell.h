typedef struct optional_int {
    int valid;
    int value;
}
optional_int;

typedef struct node {
    int value;
    struct node * next;
}
node;

typedef struct queue {
    struct node * head;
    struct node * tail;
}
queue;

queue * create_queue();

// Adds value at a position at the tail
void enqueue(queue * q, int value);

// Returns value and removes least recently added element (front)
optional_int dequeue(queue * q);

// Check whether the queue is empty
int empty(queue * q);

