package DataStructs.Tree;

import java.util.Iterator;

import DataStructs.List.UnorderedList.ArrayUnorderedList;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;
import Interfaces.Tree.BinaryTreeADT;

/**
 * ArrayBinaryTree implements the BinaryTreeADT interface.
 */
public abstract class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    /**
     * Constant to represent the default capacity of the array.
     */
    private final int CAPACITY = 50;
    /**
     * int that represents both the number of elements and the next
     * available position in the array.
     */
    protected int count;
    /**
     * Array of generic elements to represent the tree.
     */
    protected T[] tree;

    /**
     * Creates an empty binary tree.
     */
    public ArrayBinaryTree() {
        count = 0;
        tree = (T[]) new Object[CAPACITY];
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root
     *                of the new tree.
     */
    public ArrayBinaryTree(T element) {
        count = 1;
        tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
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
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++)
            if (targetElement.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }
        if (!found)
            throw new ElementNotFoundException("binary tree");
        return temp;
    }

    @Override
    public T getRoot() {
        return tree[0];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        preorder(0, templist);
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        inorder(0, templist);
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        postorder(0, templist);
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        UnorderedListADT<T> tempList = new ArrayUnorderedList<T>();
        for (int oldp = 0; oldp < count; oldp++)
            if (tree[oldp] != null)
                tempList.addToRear(tree[oldp]);
        return tempList.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void preorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length)
            if (tree[node] != null) {
                templist.addToRear(tree[node]);
                preorder(node * 2 + 1, templist);
                preorder((node + 1) * 2, templist);
            }
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void inorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length)
            if (tree[node] != null) {
                inorder(node * 2 + 1, templist);
                templist.addToRear(tree[node]);
                inorder((node + 1) * 2, templist);
            }
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void postorder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length)
            if (tree[node] != null) {
                postorder(node * 2 + 1, templist);
                postorder((node + 1) * 2, templist);
                templist.addToRear(tree[node]);
            }
    }

    @Override
    public int size() {
        return count;
    }
}
