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
    private static final int FRONT = 0;
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
        T removed = queue[FRONT];
        for (int i = FRONT; i < rear - 1; i++) {
            queue[i] = queue[i + 1];
        }
        rear--;
        return removed;
    }

    @Override
    public void enqueue(T element) {
        if (size() == queue.length)
            expand();
        queue[rear++] = element;
    }

    /**
     * 
     */
    private void expand() {
        T[] newQueue = (T[]) (new Object[queue.length * 2]);
        for (int i = FRONT; i < queue.length; i++) {
            newQueue[i] = queue[i];
        }
        this.queue = newQueue;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        return queue[FRONT];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return rear;
    }

    @Override
    public String toString() {
        String result = "Queue Front to Rear:\nNumber of elements: " + rear + "\n--START--\n";
        for (int i = FRONT; i < this.rear; i++) {
            result = result + this.queue[i].toString() + "\n";
        }
        result += "--END--\n";
        return result;
    }
}
