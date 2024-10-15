package DataStructs.Stack;

import DataStructs.Nodes.LinearNode;
import Exceptions.EmptyCollectionException;
import Interfaces.StackADT;

/**
 * 
 */
public class LinkedStack<T> implements StackADT<T> {
    /**
     * Top node in the stack.
     */
    private LinearNode<T> top;
    /**
     * int that represents the number of elements.
     */
    private int count;

    /**
     * Creates an empty stack.
     */
    public LinkedStack() {
        this.top = null;
        this.count = 0;
    }

    @Override
    public boolean isEmpty() {
        if (this.count == 0)
            return true;
        return false;
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Empty Stack ");
        return this.top.getElement();
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Empty Stack ");
        T removed = top.getElement();
        top = top.getNext();
        return removed;
    }

    @Override
    public void push(T element) {
        LinearNode<T> newNode = new LinearNode<T>(element);
        newNode.setNext(this.top);
        this.top = newNode;
        this.count++;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public String toString() {
        String result = "Stack Top to Bottom:\nNumber of elements: " + count + "\n--START--\n";
        LinearNode<T> current = top;

        while (current != null) {
            result = result + current.toString() + "\n";
            current = current.getNext();
        }
        result += "--END--";
        return result;
    }
}
