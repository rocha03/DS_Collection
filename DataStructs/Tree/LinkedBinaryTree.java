package DataStructs.Tree;

import java.util.Iterator;

import DataStructs.Nodes.BinaryTreeNode;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;
import Interfaces.Tree.BinaryTreeADT;

/**
 * LinkedBinaryTree implements the BinaryTreeADT interface.
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    /**
     * Number of elements in the tree.
     */
    private int count;
    /**
     * Root element form the tree.
     */
    private BinaryTreeNode<T> root;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the
     *                new binary tree.
     */
    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    @Override
    public boolean contains(T targetElement) {
        try {
            find(targetElement);
        } catch (ElementNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null)
            throw new ElementNotFoundException("binary tree");

        return (current.getElement());
    }

    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.
     *
     * @param targetElement the element being sought in this tree.
     * 
     * @param next          the element to begin searching from.
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null)
            return null;

        if (next.getElement().equals(targetElement))
            return next;

        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if (temp == null)
            temp = findAgain(targetElement, next.getRight());

        return temp;
    }

    @Override
    public T getRoot() {
        return root.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedListADT<T> tempList = new ArrayUnorderedList<T>();
        inorder(root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node to be used as the root
     *                 for this traversal.
     * 
     * @param tempList the temporary list for use in this traversal
     */
    protected void inorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inorder(node.getRight(), tempList);
        }
    }

    @Override
    public int size() {
        return count;
    }

}
