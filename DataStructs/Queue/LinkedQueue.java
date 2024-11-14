package DataStructs.Queue;

import DataStructs.Nodes.LinearNode;
import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;

/**
 * The LinkedQueue class implements a linked queue using linear nodes.
 */
public class LinkedQueue<T> implements QueueADT<T> {

    /**
     * Reference to the front node of the queue.
     */
    private LinearNode<T> front;

    /**
     * Reference to the rear node of the queue.
     */
    private LinearNode<T> rear;

    /**
     * Count of elements in the queue.
     */
    private int count;

    /**
     * Creates an empty queue.
     */
    public LinkedQueue() {
        this.front = this.rear = null;
        this.count = 0;
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        T removed = front.getElement();

        front = (front == rear) ? (rear = null) : (front.getNext());
        
        count--;
        return removed;
    }

    @Override
    public void enqueue(T element) {
        LinearNode<T> newNode = new LinearNode<T>(element);
        
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        count++;
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue is empty.\n");
        return front.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        String result = "Queue Front to Rear:\nNumber of elements: " + count + "\n--START--\n";
        LinearNode<T> current = front;

        while (current != null) {
            result = result + current.toString() + "\n";
            current = current.getNext();
        }
        result += "--END--";
        return result;
    }
}
