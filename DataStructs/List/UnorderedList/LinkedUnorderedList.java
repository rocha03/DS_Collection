package DataStructs.List.UnorderedList;

import DataStructs.List.LinkedList;
import DataStructs.Nodes.ListNodes.LinearNode;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;

/**
 * 
 */
public class LinkedUnorderedList<T> extends LinkedList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        LinearNode<T> node = new LinearNode<T>(element);
        node.setNext(head);
        head = node;
        count++;
        modCount.increment();
    }

    @Override
    public void addToRear(T element) {
        LinearNode<T> node = new LinearNode<T>(element);
        tail.setNext(node);
        tail = node;
        count++;
        modCount.increment();
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        LinearNode<T> current = head;
        boolean found = false;
        while (current != null && !found) {
            if (current.getElement().equals(target)) {
                if (current == head) {
                    addToFront(element);
                } else if (current == tail) {
                    addToRear(element);
                } else {
                    LinearNode<T> node = new LinearNode<T>(element);
                    node.setNext(current.getNext());
                    current.setNext(node);
                    count++;
                    modCount.increment();
                }
                found = true;
            }
        }
    }
    
}
