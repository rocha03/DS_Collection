package DataStructs.Tree.SearchTree;

import DataStructs.Nodes.BinaryTreeNode;
import DataStructs.Tree.LinkedBinaryTree;
import Exceptions.ElementNotFoundException;
import Interfaces.Tree.SearchTree.BinarySearchTreeADT;

/**
 * A LinkedBinarySearchTree class implementing a Binary Search Tree (BST).
 */
public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {
    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new
     *                binary search tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    /**
     * Adds the specified object to the binary search tree in the
     * appropriate position according to its key value. Note that
     * equal elements are added to the right.
     *
     * @param element the element to be added to the binary search
     *                tree
     */
    public void addElement(T element) {
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;
        if (isEmpty())
            root = temp;
        else {
            BinaryTreeNode<T> current = root;
            boolean added = false;
            while (!added) {
                if (comparableElement.compareTo(current.getElement()) < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(temp);
                        added = true;
                    } else
                        current = current.getLeft();
                } else {
                    if (current.getRight() == null) {
                        current.setRight(temp);
                        added = true;
                    } else
                        current = current.getRight();
                }
            }
        }
        count++;
    }

    /**
     * Removes the first element that matches the specified target
     * element from the binary search tree and returns a reference to
     * it. Throws a ElementNotFoundException if the specified target
     * element is not found in the binary search tree.
     *
     * @param targetElement the element being sought in the binary
     *                      search tree
     * @throws ElementNotFoundException if an element not found
     *                                  exception occurs
     */
    public T removeElement(T targetElement) throws ElementNotFoundException {
        T result = null;
        if (!isEmpty()) {
            // if (((Comparable) targetElement).equals(root.element)) {
            // result = root.element;
            if (((Comparable) targetElement).equals(root.getElement())) {
                result = root.getElement();
                root = replacement(root);
                count--;
            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;
                // if (((Comparable) targetElement).compareTo(root.element) < 0)
                if (((Comparable) targetElement).compareTo(root.getElement()) < 0)
                    // current = root.left;
                    current = root.getLeft();
                else
                    // current = root.right;
                    current = root.getRight();
                while (current != null && !found) {
                    // if (targetElement.equals(current.element)) {
                    if (targetElement.equals(current.getElement())) {
                        found = true;
                        count--;
                        // result = current.element;
                        result = current.getElement();
                        // if (current == parent.left) {
                        if (current == parent.getLeft()) {
                            parent.setLeft(replacement(current));
                            // parent.left = replacement(current);
                        }

                        else {
                            parent.setRight(replacement(current));
                            // parent.right = replacement(current);
                        }
                    } else {
                        parent = current;
                        // if (((Comparable) targetElement).compareTo(current.element) < 0)
                        if (((Comparable) targetElement).compareTo(current.getElement()) < 0)
                            // current = current.left;
                            current = current.getLeft();
                        else
                            // current = current.right;
                            current = current.getRight();
                        /*
                         * current = (
                         * (((Comparable) targetElement).compareTo(current.getElement()) < 0) ?
                         * current.getLeft() : current.getRight()
                         * );
                         */

                    }
                } // while
                if (!found)
                    throw new ElementNotFoundException("binary search tree");
            }
        } // end outer if
        return result;
    }

    /**
     * Returns a reference to a node that will replace the one
     * specified for removal. In the case where the removed node has
     * two children, the inorder successor is used as its replacement.
     *
     * @param node the node to be removeed
     * @return a reference to the replacing node
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        // BinaryTreeNode<T> result = null;
        if (node.getLeft() == null && node.getRight() == null)
            return null;
        if (node.getLeft() == null)
            return node.getRight();
        if (node.getRight() == null)
            return node.getLeft();

        BinaryTreeNode<T> current = node.getRight();
        BinaryTreeNode<T> parent = node;

        while (current.getLeft() != null) {
            parent = current;
            current = current.getLeft();
        }
        if (node.getRight() == current)
            current.setLeft(node.getLeft());
        else {
            parent.setLeft(current.getRight());
            current.setRight(node.getRight());
            current.setLeft(node.getLeft());
        }
        return current;
    }

    @Override
    public void removeAllOccurrences(T targetElement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAllOccurrences'");
    }

    @Override
    public T removeMin() {
        if (isEmpty())
            return null;

        BinaryTreeNode<T> current = root;
        BinaryTreeNode<T> parent = null;

        while (current.getLeft() != null) {
            parent = current;
            current = current.getLeft();
        }

        if (parent == null) { // root node is the minimum
            root = root.getRight();
        } else {
            parent.setLeft(current.getRight());
        }

        count--;
        return current.getElement();
    }

    @Override
    public T removeMax() {
        if (isEmpty()) 
            return null;
        

        BinaryTreeNode<T> current = root;
        BinaryTreeNode<T> parent = null;

        while (current.getRight() != null) {
            parent = current;
            current = current.getRight();
        }

        if (parent == null) // root node is the maximum
            root = root.getLeft();
        else
            parent.setRight(current.getLeft());

        count--;
        return current.getElement();
    }

    @Override
    public T findMin() {
        if (isEmpty())
            return null;

        BinaryTreeNode<T> current = root;
        while (current.getLeft() != null)
            current = current.getLeft();
        
        return current.getElement();
    }

    @Override
    public T findMax() {
        if (isEmpty())
            return null;

        BinaryTreeNode<T> current = root;
        while (current.getRight() != null)
            current = current.getRight();

        return current.getElement();
    }
}
