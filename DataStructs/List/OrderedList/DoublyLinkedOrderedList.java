package DataStructs.List.OrderedList;

import DataStructs.List.DoublyLinkedList;
import DataStructs.Nodes.ListNodes.DoubleNode;
import Interfaces.List.OrderedListADT;

/**
 * An ordered list implementation using a doubly linked list, where elements are
 * inserted in sorted order based on their natural ordering (or a custom comparator).
 * This class extends DoublyLinkedList and implements the OrderedListADT interface.
 *
 * @param <T> the type of elements in the list, must be comparable
 */
public class DoublyLinkedOrderedList<T> extends DoublyLinkedList<T> implements OrderedListADT<T> {

    /**
     * Adds an element to the list while maintaining the list in sorted order.
     * The element is inserted into the correct position based on its natural
     * ordering (via {@code Comparable}).
     * If the list is empty or the element is the smallest, it is inserted at the head.
     *
     * @param element the element to add
     * @throws IllegalArgumentException if the element is not comparable
     */
    @Override
    public void add(T element) throws IllegalArgumentException {
        // Ensure the element is comparable
        if (!(element instanceof Comparable<?>))
            throw new IllegalArgumentException("Not comparable");

        // Cast element to Comparable
        Comparable<T> comparable = (Comparable<T>) element;
        DoubleNode<T> newNode = new DoubleNode<>(element);

        // Insert at the head if the list is empty or the element is the smallest
        if (head == null || comparable.compareTo(head.getElement()) <= 0) {
            newNode.setNext(head);  // Link the new node to the current head
            if (head != null)
                head.setPrevious(newNode);  // Update the previous pointer of the old head
            head = newNode;  // Move the head pointer to the new node

            // If the list was empty, set the tail to the new node as well
            if (tail == null)
                tail = newNode;
        } else {
            // Traverse the list to find the correct insertion point
            DoubleNode<T> current = head;
            while (current.getNext() != null && comparable.compareTo(current.getNext().getElement()) > 0)
                current = current.getNext();  // Move to the next node if the current node's value is smaller

            // Insert the new node at the found position
            newNode.setNext(current.getNext());  // Link the new node to the next node (if exists)
            if (current.getNext() != null)
                current.getNext().setPrevious(newNode);  // Update the previous pointer of the next node

            current.setNext(newNode);  // Link the current node to the new node
            newNode.setPrevious(current);  // Set the new node's previous pointer to the current node

            // Update tail if the new node is inserted at the end of the list
            if (newNode.getNext() == null)
                tail = newNode;
        }

        // Increment the element count and update the modification count
        count++;
        modCount.increment();
    }
}
