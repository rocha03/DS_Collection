package DataStructs.Nodes;

/**
 * BinaryTreeNode represents a node in a binary tree with a left and
 * right child.
 */
public class BinaryTreeNode<T> {
    /**
     * 
     */
    private T element;
    /**
     * 
     */
    private BinaryTreeNode<T> left;
    /**
     * 
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
     * This method may be able to be written more efficiently.
     *
     * @return the integer number of non-null children of this node
     */
    public int numChildren() {
        int children = 0;
        if (left != null)
            children = children + 1 + left.numChildren();
        if (right != null)
            children = children + 1 + right.numChildren();
        return children;
    }

    /**
     * 
     * @return
     */
    public T getElement() {
        return element;
    }

    /**
     * 
     * @param element
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * 
     * @return
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /**
     * 
     * @param left
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * 
     * @return
     */
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /**
     * 
     * @param right
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BinaryTreeNode<?> other = (BinaryTreeNode<?>) obj;

        if (element != null ? !element.equals(other.element) : other.element != null) {
            return false;
        }

        boolean leftEquals = (left == null && other.left == null) || (left != null && left.equals(other.left));
        boolean rightEquals = (right == null && other.right == null) || (right != null && right.equals(other.right));

        return leftEquals && rightEquals;
    }
}
