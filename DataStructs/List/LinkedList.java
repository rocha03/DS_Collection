package DataStructs.List;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import DataStructs.List.Iterators.LinkedIterator;
import DataStructs.List.Iterators.ModCount;
import DataStructs.Nodes.ListNodes.BasicNode;
import DataStructs.Nodes.ListNodes.LinearNode;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.List.ListADT;

/**
 * Abstract class representing a singly linked list.
 * The list is composed of nodes where each node points to the next node in the
 * list.
 * 
 * @param <T> the type of elements stored in this list.
 */
public abstract class LinkedList<T> implements ListADT<T> {

    /** The number of elements currently in the list. */
    protected int count;

    /** Reference to the first node in the list (head). */
    protected LinearNode<T> head;

    /** Reference to the last node in the list (tail). */
    protected LinearNode<T> tail;

    /** Instance of ModCount to track structural modifications to the list. */
    protected final ModCount modCount = new ModCount();

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param target the element to search for.
     * @return {@code true} if the element is found in the list, {@code false}
     *         otherwise.
     */
    @Override
    public boolean contains(T target) {
        LinearNode<T> current = head;
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
     * @return {@code true} if the list contains no elements, {@code false}
     *         otherwise.
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
     * Removes and returns the specified element from the list.
     *
     * @param element the element to remove.
     * @return the removed element.
     * @throws EmptyCollectionException if the list is empty.
     * @throws ElementNotFoundException if the element is not found in the list.
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty())
            throw new EmptyCollectionException("The list is empty.");

        LinearNode<T> current = head;
        LinearNode<T> previous = null;

        while (current != null) {
            if (current.getElement().equals(element)) {
                if (previous == null) {
                    // Element is at the head
                    head = current.getNext();
                    if (head == null) // If the list becomes empty
                        tail = null;
                } else {
                    // Element is somewhere else in the list
                    previous.setNext(current.getNext());
                    if (current.getNext() == null) { // If the element is at the tail
                        tail = previous;
                    }
                }
                count--;
                modCount.increment();
                return current.getElement(); // Return the removed element
            }
            previous = current;
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

        LinearNode<T> current = head;
        while (current.getNext() != null && current.getNext().getNext() != null)
            current = current.getNext();

        T removed = current.getNext().getElement();
        current.setNext(null);
        tail = current;

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
        // return new LinkedIterator<>(head, modCount);
        return new InnerIterator();
    }

    /**
     * InnerIterator is a private inner class that implements the {@link Iterator}
     * interface for traversing the elements of a linked list.
     *
     * @param <T> the type of elements returned by this iterator
     */
    private class InnerIterator implements Iterator<T> {
        /**
         * The current node being accessed by the iterator.
         */
        private LinearNode<T> current;

        /**
         * The previous node being accessed by the iterator.
         */
        private LinearNode<T> previous;

        /**
         * The expected modification count of the list to detect concurrent
         * modifications.
         */
        private int expectedModCount;

        /**
         * Flag indicating whether the last element returned by {@code next()} can be
         * removed.
         */
        private boolean canRemove;

        /**
         * Constructs an instance of the InnerIterator.
         * Initializes the current node to the head of the list and records the current
         * modification count.
         */
        public InnerIterator() {
            this.current = head;
            this.previous = null;
            this.expectedModCount = modCount.getValue();
            this.canRemove = false;
        }

        /**
         * Checks if there are more elements to iterate over.
         *
         * @return {@code true} if there are more elements; {@code false} otherwise
         * @throws ConcurrentModificationException if the list has been modified during
         *                                         iteration
         */
        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount.getValue()) {
                throw new java.util.ConcurrentModificationException();
            }
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws ConcurrentModificationException if the list has been modified during
         *                                         iteration
         * @throws NoSuchElementException          if there are no more elements to
         *                                         return
         */
        @Override
        public T next() {
            if (expectedModCount != modCount.getValue()) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            previous = current;
            current = current.getNext();
            canRemove = true;
            return previous.getElement();
        }

        /**
         * Removes the last element returned by this iterator.
         *
         * @throws IllegalStateException if the {@code next()} method has not been
         *                               called or {@code remove()} has already been
         *                               called after the last {@code next()} call
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Cannot remove before calling next()");
            }
            try {
                LinkedList.this.remove(previous.getElement());
            } catch (EmptyCollectionException | ElementNotFoundException ex) {
                // Handle exceptions from the list's remove method
            }
            expectedModCount++;
            modCount.increment();
            canRemove = false;
        }
    }

}
