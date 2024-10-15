package DataStructs.Stack;

import Exceptions.EmptyCollectionException;
import Interfaces.StackADT;

/**
 * 
 */
public class ArrayStack<T> implements StackADT<T> {
    /**
     * Constant to represent the default capacity of the array.
     */
    private final int DEFAULT_CAPACITY = 100;
    /**
     * int that represents both the number of elements and the next
     * available position in the array.
     */
    private int top;
    /**
     * Array of generic elements to represent the stack
     */
    private T[] stack;

    /**
     * Creates an empty stack using the default capacity.
     */
    public ArrayStack() {
        top = 0;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty stack using the specified capacity.
     * 
     * @param initialCapacity Represents the specified capacity.
     */
    public ArrayStack(int initialCapacity) {
        top = 0;
        stack = (T[]) (new Object[initialCapacity]);
    }

    // do I have to put javadoc on implemented methods?
    @Override
    public boolean isEmpty() {
        if (this.top == 0)
            return true;
        return false;
    }

    @Override
    public T peek() throws EmptyCollectionException { // how do I fix this?
        if (isEmpty())
            throw new EmptyCollectionException("Empty Stack ");

        return this.stack[this.top - 1];
    }

    @Override
    public T pop() throws EmptyCollectionException { // how do I fix this?
        if (isEmpty())
            throw new EmptyCollectionException("Empty Stack ");

        this.top--;
        return this.stack[this.top - 1];// do I have to set the removed to null?
    }

    @Override
    public void push(T element) {
        if (size() == this.stack.length)
            expand();

        this.stack[this.top] = element;
        this.top++;
    }

    /**
     * 
     */
    public void expand() {
        T[] newStack = (T[]) (new Object[stack.length * 2]);

        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }

        this.stack = newStack;
    }

    @Override
    public int size() {
        return this.top;
    }

    @Override
    public String toString() {
        String result = "Stack Top to Bottom:\nNumber of elements: " + top + "\n--START--\n";
        for (int i = 0; i < this.top; i++) {
            result = result + this.stack[i].toString() + "\n";
        }
        result += "--END--\n";
        return result;
    }
}
