package DataStructs.Tree.Heap;

import DataStructs.Tree.ArrayBinaryTree;
import Exceptions.EmptyCollectionException;
import Interfaces.Tree.HeapADT;

/**
 * ArrayHeap provides an array implementation of a minheap.
 *
 */
public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {
    public ArrayHeap() {
        super();
    }

    @Override
    public void addElement(T element){
        // Implementação do método para inserir elementos no heap
        if (count == size())
            //extends();
        tree[count] = element;
        count++;

        if (count > 1)
            heapifyAdd();
    }

    @Override
    public T removeMin() throws EmptyCollectionException {
        // Implementação do método para remover o menor elemento
        if (isEmpty())
            throw new EmptyCollectionException("Empty Heap");

        T minElement = tree[0];

        tree[0] = tree[count - 1];
        heapifyRemove();
        count--;
        return minElement;
    }

    private void heapifyRemove() {
        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null))
            next = count;
        else if (tree[left] == null)
            next = right;
        else if ((tree[right] == null) || (((Comparable) tree[left]).compareTo(tree[right]) < 0))
            next = left;
        else
            next = right;

        while ((next < count) && (((Comparable) tree[next]).compareTo(tree[node]) < 0)) {
            temp = tree[node];
            tree[node] = tree[next];
            tree[next] = temp;
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);
            if ((tree[left] == null) && (tree[right] == null))
                next = count;
            else if (tree[left] == null)
                next = right;
            else if (tree[right] == null)
                next = left;
            else if (((Comparable) tree[left]).compareTo(tree[right]) < 0)
                next = left;
            else
                next = right;
        }
    }

    @Override
    public T findMin() throws EmptyCollectionException {
        // Implementação do método para remover o menor elemento
        if (isEmpty())
            throw new EmptyCollectionException("Empty Heap");

        return tree[0];
    }

    private void heapifyAdd() {
        T temp;

        int next = count - 1;
        while ((next != 0) && (((Comparable) tree[next]).compareTo(tree[(next - 1) / 2]) < 0)) {
            temp = tree[next];
            tree[next] = tree[(next - 1) / 2];
            tree[(next - 1) / 2] = temp;
            next = (next - 1) / 2;
        }
    }
}
