package DataStructs.Nodes;

/**
 * BinaryTreeNode represents a node in a binary tree with a left and right
 * child.
 */
public class BinaryTreeNode<T> {
    /**
     * The element stored in this node.
     */
    private T element;
    /**
     * The left child of this node.
     */
    private BinaryTreeNode<T> left;
    /**
     * The right child of this node.
     */
    private BinaryTreeNode<T> right;

    /**
     * Creates a new tree node with the specified data.
     *
     * @param obj the element that will become a part of
     *            the new tree node
     */
    public BinaryTreeNode(T obj) {
        element = obj;
        left = null;
        right = null;
    }

    /**
     * Returns the number of non-null children of this node.
     *
     * @return the integer number of non-null children of this node
     */
    public int numChildren() {
        int children = (left != null ? 1 + left.numChildren() : 0) + (right != null ? 1 + right.numChildren() : 0);
        return children;
    }

    /**
     * Returns the element stored in this node.
     * 
     * @return the element stored in this node.
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     * 
     * @param element the new element to be stored in this node.
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Returns the left child of this node.
     * 
     * @return the left child of this node.
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child of this node.
     * 
     * @param left the new left child to be set.
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Returns the right child of this node.
     * 
     * @return the right child of this node.
     */
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /**
     * Sets the right child of this node.
     * 
     * @param right the new right child to be set.
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode [element=" + element + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        BinaryTreeNode<?> other = (BinaryTreeNode<?>) obj;

        return (element != null ? element.equals(other.element) : other.element == null)
                && (left == null ? other.left == null : left.equals(other.left))
                && (right == null ? other.right == null : right.equals(other.right));
    }
}
