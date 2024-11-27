package DataStructs.Tree.Heap;

import DataStructs.Nodes.HeapNode;
import DataStructs.Tree.LinkedBinaryTree;
import Exceptions.EmptyCollectionException;
import Interfaces.Tree.HeapADT;

/**
 * Heap implements a heap.
 * LinkedHeap implementes a heap using a linked binarry tree.
 *
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeapADT<T> {
    private HeapNode<T> lastNode;
    private HeapNode<T> root;

    public LinkedHeap() {
        super();
    }

    /**
     * Adds the specified element to this heap in the
     * appropriate position according to its key value
     * Note that equal elements are added to the right.
     * 
     * @param obj the element to be added to this head
     */
    public void addElement(T obj) {
        HeapNode<T> node = new HeapNode<>(obj);

        if (root == null) {
            root = node;
            lastNode = node;
        } else {
            HeapNode<T> next_parent = getNextParentAdd();
            
            // Set the new node as left or right child using ternary
            if (next_parent.getLeft() == null)
                next_parent.setLeft(node);
            else
                next_parent.setRight(node);
            
            // Set the parent of the new node
            node.setParent(next_parent);

            lastNode = node;
            count++;

            if (count > 1)
                heapifyAdd();
        }
    }

    /**
     * Returns the node that will be the parent of the new node
     *
     * @return the node that will be a parent of the new node
     */
    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;

        while (result != root && result.getParent().getLeft() != result)
            result = result.getParent();

        if (result != root)
            if (result.getParent().getRight() == null)
                result = result.getParent();
            else {
                result = (HeapNode<T>) result.getParent().getRight();
                while (result.getLeft() != null)
                    result = (HeapNode<T>) result.getLeft();
            }
        else
            while (result.getLeft() != null)
                result = (HeapNode<T>) result.getLeft();

        return result;
    }

    /**
     * Reorders this heap after adding a node.
     */
    private void heapifyAdd() {
        HeapNode<T> next = lastNode;
        T temp;

        temp = next.getElement();

        while ((next != root) && (((Comparable) temp).compareTo(next.getParent().getElement()) < 0)) {
            next.setElement(next.getParent().getElement());
            next = next.getParent();
        }
        next.setElement(temp);
    }

    /**
     * Remove the element with the lowest value in this heap and
     * returns a reference to it.
     * Throws an EmptyCollectionException if the heap is empty.
     *
     * @return the element with the lowest value in this heap
     * 
     * @throws EmptyCollectionException if an empty collection
     *                                  exception occurs
     */
    public T removeMin() throws EmptyCollectionException {

        if (isEmpty())
            throw new EmptyCollectionException("Empty Heap");
        T minElement = root.getElement();
        // T minElement = root.element;
        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> next_last = getNewLastNode();
            if (lastNode.getParent().getLeft() == lastNode)
                lastNode.getParent().setLeft(null);
            else
                lastNode.getParent().setRight(null);
            root.setElement(lastNode.getElement());
            /*
             * (lastNode.getParent().getLeft() == lastNode) ? (lastNode.getParent().setLeft(null)) : (lastNode.getParent().setRight(null));
             * root.setElement(lastNode.getElement());
             */
            lastNode = next_last;
            heapifyRemove();
        }
        count--;
        return minElement;
    }

    /**
     * Returns the node that will be the new last node after
     * a remove.
     *
     * @return the node that willbe the new last node after
     *         a remove
     */
    private HeapNode<T> getNewLastNode() {
        HeapNode<T> result = lastNode;
        while ((result != root) && (result.getParent().getLeft() == result))
            result = result.getParent();

        if (result != root)
            result = (HeapNode<T>) result.getParent().getLeft();
        while (result.getRight() != null)
            result = (HeapNode<T>) result.getRight();
        return result;
    }

    /**
     * Reorders this heap after removing the root element.
     */
    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = (HeapNode<T>) root;
        HeapNode<T> left = (HeapNode<T>) node.getLeft();
        HeapNode<T> right = (HeapNode<T>) node.getRight();
        HeapNode<T> next;
        

        if ((left == null) && (right == null))
            next = null;
        else if (right == null || ((Comparable) left.getElement()).compareTo(right.getElement()) < 0)
            next = left;
        else if (left == null)
            next = right;
        else
            next = right;
        
        next = (left == null) ? right :
                (right == null) ? left : 
                    (((Comparable) left.getElement()).compareTo(right.getElement()) < 0 ? left : right);

        temp = node.getElement();

        while ((next != null) && (((Comparable) next.getElement()).compareTo(temp) < 0)) {
            node.setElement(next.getElement());
            node = next;
            left = (HeapNode<T>) node.getLeft();
            right = (HeapNode<T>) node.getRight();

            if ((left == null) && (right == null))
                next = null;
            else if (left == null)
                next = right;
            else if (right == null)
                next = left;
            else if (((Comparable) left.getElement()).compareTo(right.getElement()) < 0)
                next = left;
            else
                next = right;
        }
        node.setElement(temp);
    }

    @Override
    public T findMin() {
        if (isEmpty())
            throw new UnsupportedOperationException("Unimplemented method 'findMin'");
        return root.getElement();
    }
}
