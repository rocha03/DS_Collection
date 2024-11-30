package DataStructs.List.UnorderedList;

import DataStructs.List.DoublyLinkedList;
import DataStructs.Nodes.ListNodes.DoubleNode;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;

/**
 * An unordered list implementation using a doubly linked list.
 * Supports operations like adding elements to the front, rear, and after a specific target.
 * 
 * @param <T> the type of elements in the list
 */
public class DoublyLinkedUnorderedList<T> extends DoublyLinkedList<T> implements UnorderedListADT<T> {

    /**
     * Adds an element to the front of the list.
     * If the list is empty, the new node becomes both the head and the tail.
     * 
     * @param element the element to add to the front
     */
    @Override
    public void addToFront(T element) {
        DoubleNode<T> node = new DoubleNode<T>(element);
        if (head == null) {
            head = tail = node;  // If list is empty, node becomes both head and tail
        } else {
            node.setNext(head);  // Set the new node's next to the current head
            head.setPrevious(node);  // Set the current head's previous to the new node
            head = node;  // Move head to the new node
        }
        count++;  // Increment element count
        modCount.increment();  // Increment modification count to track changes
    }

    /**
     * Adds an element to the rear (tail) of the list.
     * If the list is empty, the new node becomes both the head and the tail.
     * 
     * @param element the element to add to the rear
     */
    @Override
    public void addToRear(T element) {
        DoubleNode<T> node = new DoubleNode<T>(element);
        if (tail == null) {
            head = tail = node;  // If list is empty, node becomes both head and tail
        } else {
            node.setPrevious(tail);  // Set the new node's previous to the current tail
            tail.setNext(node);  // Set the current tail's next to the new node
            tail = node;  // Move tail to the new node
        }
        count++;  // Increment element count
        modCount.increment();  // Increment modification count to track changes
    }

    /**
     * Adds an element after a specific target element in the list.
     * If the target is found, a new node is inserted after it.
     * If the target is not found, an ElementNotFoundException is thrown.
     * 
     * @param element the element to add after the target
     * @param target the target element after which the new element is added
     * @throws ElementNotFoundException if the target element is not found in the list
     */
    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        DoubleNode<T> current = head;
        boolean found = false;

        // Traverse the list to find the target element
        while (current != null && !found) {
            if (current.getElement().equals(target)) {
                // If target is found, create a new node and insert it after the target
                DoubleNode<T> node = new DoubleNode<T>(element);
                node.setNext(current.getNext());  // Set the new node's next to the target's next
                node.setPrevious(current);  // Set the new node's previous to the target

                if (current == tail) {
                    tail = node;  // If the target was the tail, update the tail
                } else {
                    current.getNext().setPrevious(node);  // Update the next node's previous to the new node
                }

                current.setNext(node);  // Set the target node's next to the new node
                count++;  // Increment element count
                modCount.increment();  // Increment modification count to track changes
                found = true;  // Mark target as found
            }
            current = current.getNext();  // Move to the next node in the list
        }

        // If target was not found, throw ElementNotFoundException
        if (!found) {
            throw new ElementNotFoundException("Target element not found");
        }
    }
}
