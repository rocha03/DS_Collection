package DataStructs.Stack;

import Exceptions.EmptyCollectionException;
import Interfaces.StackADT;

/**
 * The ArrayStack class implements a stack using an array.
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
     * Array of generic elements to represent the stack.
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

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Empty Stack ");
        return stack[top - 1];
    }

    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Empty Stack ");
        return stack[--top - 1];
    }

    @Override
    public void push(T element) {
        if (size() == stack.length)
            expand();
        stack[top++] = element;
    }

    /**
     * Expands the capacity of the stack by doubling the size of the array.
     */
    public void expand() {
        T[] newStack = (T[]) (new Object[stack.length * 2]);
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public String toString() {
        String result = "Stack Top to Bottom:\nNumber of elements: " + top + "\n--START--\n";
        for (int i = 0; i < top; i++) {
            result = result + stack[i].toString() + "\n";
        }
        result += "--END--\n";
        return result;
    }
}
