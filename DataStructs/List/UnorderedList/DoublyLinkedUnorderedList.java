package DataStructs.List.UnorderedList;

import DataStructs.List.DoublyLinkedList;
import DataStructs.Nodes.ListNodes.DoubleNode;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;

/**
 * 
 */
public class DoublyLinkedUnorderedList<T> extends DoublyLinkedList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        DoubleNode<T> node = new DoubleNode<T>(element);
        if (head == null) {
            head = tail = node;
        } else {
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        }
        count++;
        modCount.increment();
    }

    @Override
    public void addToRear(T element) {
        DoubleNode<T> node = new DoubleNode<T>(element);
        if (tail == null) {
            head = tail = node;
        } else {
            node.setPrevious(tail);
            tail.setNext(node);
            tail = node;
        }
        count++;
        modCount.increment();
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        DoubleNode<T> current = head;
        boolean found = false;
        while (current != null && !found) {
            if (current.getElement().equals(target)) {
                DoubleNode<T> node = new DoubleNode<T>(element);
                node.setNext(current.getNext());
                node.setPrevious(current);
                if (current == tail) {
                    tail = node;
                } else {
                    current.getNext().setPrevious(node);
                }
                current.setNext(node);
                count++;
                modCount.increment();
                found = true;
            }
            current = current.getNext();
        }
    }

}
