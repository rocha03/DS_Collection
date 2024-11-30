package DataStructs.List;

import java.util.Iterator;
import DataStructs.List.Iterators.LinkedIterator;
import DataStructs.List.Iterators.ModCount;
import DataStructs.Nodes.ListNodes.DoubleNode;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * Abstract class representing a doubly linked list.
 * The list consists of nodes, where each node has references to both its previous and next nodes.
 * 
 * @param <T> the type of elements stored in this list.
 */
public abstract class DoublyLinkedList<T> implements ListADT<T> {

    /** The number of elements in the list. */
    protected int count;

    /** Reference to the first node in the list (head). */
    protected DoubleNode<T> head;

    /** Reference to the last node in the list (tail). */
    protected DoubleNode<T> tail;

    /** Instance of ModCount to track structural modifications to the list. */
    protected final ModCount modCount = new ModCount();

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param target the element to search for.
     * @return {@code true} if the element is found, {@code false} otherwise.
     */
    @Override
    public boolean contains(T target) {
        DoubleNode<T> current = head;
        while (current != null) {
            if (current.getElement().equals(target))
                return true;
            current = current.getNext();
        }
        return false;
    }

    /**
     * Returns the first element in the list.
     *
     * @return the first element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty.");
        return head.getElement();
    }

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list contains no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the last element in the list.
     *
     * @return the last element in the list.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty.");
        return tail.getElement();
    }

    /**
     * Removes the specified element from the list.
     *
     * @param element the element to remove.
     * @return the removed element.
     * @throws EmptyCollectionException if the list is empty.
     * @throws ElementNotFoundException if the element is not found in the list.
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        DoubleNode<T> current = head;
        while (current != null) {
            if (current.getElement().equals(element)) {
                // Element found, remove it
                if (current == head) {
                    // Element is at the head
                    head = current.getNext();
                    if (head == null) // If the list becomes empty
                        tail = null;
                } else {
                    // Element is somewhere else in the list
                    // Skip the current node
                    current.getPrevious().setNext(current.getNext());
                    if (current.getNext() == null) {
                        // If the element is at the tail
                        tail = current.getPrevious();
                    } else {
                        current.getNext().setPrevious(current.getPrevious());
                    }
                }
                count--;
                modCount.increment();
                return current.getElement(); // Return the removed element
            }
            current = current.getNext();
        }
        throw new ElementNotFoundException("Element not found in the list.");
    }

    /**
     * Removes and returns the first element in the list.
     *
     * @return the removed first element.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty.");
        T removed = head.getElement();
        head = head.getNext();
        if (head == null) // If the list becomes empty after removal
            tail = null;
        else
            head.setPrevious(null);
        count--;
        modCount.increment();
        return removed;
    }

    /**
     * Removes and returns the last element in the list.
     *
     * @return the removed last element.
     * @throws EmptyCollectionException if the list is empty.
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty.");
        T removed = tail.getElement();
        tail = tail.getPrevious();
        if (tail == null) // If the list becomes empty after removal
            head = null;
        else
            tail.setNext(null);
        count--;
        modCount.increment();
        return removed;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list.
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator over the elements in this list.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<>(head, modCount);
    }
}
