package DataStructs.Tree;

import java.util.Iterator;

import DataStructs.List.UnorderedList.ArrayUnorderedList;
import DataStructs.Nodes.BinaryTreeNode;
import DataStructs.Queue.LinkedQueue;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;
import Interfaces.List.UnorderedListADT;
import Interfaces.Tree.BinaryTreeADT;

/** LinkedBinaryTree implements the BinaryTreeADT interface. */
public abstract class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    /** Number of elements in the tree. */
    protected int count;

    /** Root element form the tree. */
    protected BinaryTreeNode<T> root;

    /** Creates an empty binary tree. */
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
        UnorderedListADT<T> tempList = new ArrayUnorderedList<T>();
        preorder(root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedListADT<T> tempList = new ArrayUnorderedList<T>();
        inorder(root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedListADT<T> tempList = new ArrayUnorderedList<T>();
        postorder(root, tempList);

        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        UnorderedListADT<T> tempList = new ArrayUnorderedList<T>();
        QueueADT<BinaryTreeNode<T>> queue = new LinkedQueue<BinaryTreeNode<T>>();
        BinaryTreeNode<T> node = root;

        if (node == null)
            return tempList.iterator();

        // enqueue the root elem
        queue.enqueue(node);

        // check if there are still elements in the queue
        while (!queue.isEmpty()) {
            try {
                // extract and insert in the list the next element
                node = queue.dequeue();
                tempList.addToRear(node.getElement());

                // check if the node has children and if yes enqueue them
                if (node.getLeft() != null)
                    queue.enqueue(node.getLeft());
                if (node.getRight() != null)
                    queue.enqueue(node.getRight());
            } catch (EmptyCollectionException e) {
                // Auto-generated catch block
                e.printStackTrace();
            }
        }
        return tempList.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node to be used as the root
     *                 for this traversal.
     * @param tempList the temporary list for use in this traversal.
     */
    protected void preorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preorder(node.getLeft(), tempList);
            preorder(node.getRight(), tempList);
        }
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node to be used as the root
     *                 for this traversal.
     * @param tempList the temporary list for use in this traversal.
     */
    protected void inorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inorder(node.getRight(), tempList);
        }
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node     the node to be used as the root
     *                 for this traversal.
     * @param tempList the temporary list for use in this traversal.
     */
    protected void postorder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            postorder(node.getLeft(), tempList);
            postorder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    @Override
    public int size() {
        return count;
    }

}
