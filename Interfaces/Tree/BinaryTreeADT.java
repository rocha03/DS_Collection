package Interfaces.Tree;

import java.util.Iterator;

import Exceptions.ElementNotFoundException;

/**
 * Defines the interface for a Binary Tree Abstract Data Type (ADT).
 * Provides core operations and various traversal mechanisms for a binary tree.
 *
 * @param <T> the type of elements held in this binary tree
 */
public interface BinaryTreeADT<T> {
    /**
     * Returns a reference to the root element.
     *
     * @return a reference to the root.
     */
    public T getRoot();

    /**
     * Returns true if this binary tree is empty and false otherwise.
     *
     * @return true if this binary tree is empty.
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this binary tree.
     *
     * @return the integer number of elements in this tree.
     */
    public int size();

    /**
     * Returns true if the binary tree contains an element that
     * matches the specified element and false otherwise.
     *
     * @param targetElement the element being sought in the tree.
     * 
     * @return true if the tree contains the target element.
     */
    public boolean contains(T targetElement);

    /**
     * RFinds and returns a reference to the specified element in this binary tree.
     *
     * @param targetElement the element being sought in the tree.
     * @return a reference to the specified element.
     * @throws ElementNotFoundException if the specified element not found.
     */
    public T find(T targetElement) throws ElementNotFoundException;

    /**
     * Returns the string representation of the binary tree.
     * The format and details of the representation are implementation-specific.
     *
     * @return a string representation of the binary tree.
     */
    @Override
    public String toString();

    /**
     * Performs an in-order traversal of the tree and returns an iterator over its elements.
     *
     * @return an iterator for an in-order traversal of the tree
     */
    public Iterator<T> iteratorInOrder();

    /**
     * Performs a pre-order traversal of the tree and returns an iterator over its elements.
     *
     * @return an iterator for a pre-order traversal of the tree
     */
    public Iterator<T> iteratorPreOrder();

    /**
     * Performs a post-order traversal of the tree and returns an iterator over its elements.
     *
     * @return an iterator for a post-order traversal of the tree
     */
    public Iterator<T> iteratorPostOrder();

    /**
     * Performs a level-order traversal of the tree and returns an iterator over its elements.
     *
     * @return an iterator for a level-order traversal of the tree
     */
    public Iterator<T> iteratorLevelOrder();
}
