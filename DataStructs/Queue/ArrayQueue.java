package DataStructs.Queue;

import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;

/**
 * 
 */
public class ArrayQueue<T> implements QueueADT<T> {
    /**
     * 
     */
    private static final int DEFAULT_CAPACITY = 100;
    /**
     * 
     */
    private T[] queue;
    /**
     * 
     */
    private int rear;

    /**
     * 
     */
    public ArrayQueue() {
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
        this.rear = 0;
    }

    /**
     * 
     * @param size
     */
    public ArrayQueue(int size) {
        this.queue = (T[]) (new Object[size]);
        this.rear = 0;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        T removed = queue[0];
        for (int i = 0; i < rear; i++) {
            queue[i] = queue[i + 1];
        }
        rear--;
        return removed;
    }

    @Override
    public void enqueue(T element) {
        if (rear == queue.length)
            expand();
        queue[rear++] = element;
    }

    /**
     * 
     */
    private void expand() {
        T[] newQueue = (T[]) (new Object[queue.length * 2]);
        for (int i = 0; i < queue.length; i++) {
            newQueue[i] = queue[i];
        }
        this.queue = newQueue;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        return queue[0];
    }

    @Override
    public boolean isEmpty() {
        if (rear == 0)
            return true;
        return false;
    }

    @Override
    public int size() {
        return rear;
    }

    @Override
    public String toString() {
        String result = "Queue Front to Rear:\nNumber of elements: " + rear + "\n--START--\n";
        for (int i = 0; i < this.rear; i++) {
            result = result + this.queue[i].toString() + "\n";
        }
        result += "--END--\n";
        return result;
    }
}
