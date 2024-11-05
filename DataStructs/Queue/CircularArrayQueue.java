package DataStructs.Queue;

import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;

/**
 * The CircularArrayQueue class implements a circular queue using an array.
 */
public class CircularArrayQueue<T> implements QueueADT<T> {
    /**
     * Default capacity of the queue.
     */
    private static final int DEFAULT_CAPACITY = 100;
    /**
     * Array that stores the elements of the queue.
     */
    private T[] queue;
    /**
     * Index of the front of the queue.
     */
    private int front;
    /**
     * Index of the rear of the queue.
     */
    private int rear;
    /**
     * Count of elements in the queue.
     */
    private int count;

    /**
     * Creates a circular queue with the default capacity.
     */
    public CircularArrayQueue() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.front = this.rear = this.count = 0;
    }

    /**
     * Creates a circular queue with the specified capacity.
     * 
     * @param size the capacity of the queue.
     */
    public CircularArrayQueue(int size) {
        this.queue = (T[]) (new Object[size]);
        this.front = this.rear = this.count = 0;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        T removed = queue[front];
        front = loop(front);
        count--;
        return removed;
    }

    @Override
    public void enqueue(T element) {
        if (size() == queue.length)
            expand();
        queue[rear] = element;
        rear = loop(rear);
        count++;
    }

    /**
     * Expands the capacity of the circular queue when it is full.
     */
    private void expand() {
        T[] newQueue = (T[]) (new Object[queue.length * 2]);
        int i = front;
        for (int j = 0; j < count; j++) {
            newQueue[j] = queue[i];
            i = loop(i);
        }
        queue = newQueue;
        front = 0;
        rear = count;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        return queue[front];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

    /**
     * Computes the circular index for a given index.
     * 
     * @param index the index to be adjusted.
     * 
     * @return the new circular index.
     */
    private int loop(int index) {
        return (index + 1) % queue.length;
    }

    @Override
    public String toString() {
        String result = "Queue Front to Rear:\nNumber of elements: " + rear + "\n--START--\n";
        int j = front;
        for (int i = front; i < count; i++) {
            result = result + queue[j].toString() + "\n";
            j = loop(j);
        }
        result += "--END--\n";
        return result;
    }
}
