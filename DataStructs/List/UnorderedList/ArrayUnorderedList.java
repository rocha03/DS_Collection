package DataStructs.List.UnorderedList;

import DataStructs.List.ArrayList;
import Exceptions.ElementNotFoundException;
import Interfaces.List.UnorderedListADT;

/**
 * 
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * 
     */
    public ArrayUnorderedList(){
        super();
    }

    /**
     * 
     * @param size
     */
    public ArrayUnorderedList(int size){
        super(size);
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException {
        if (size() == list.length)
            expand();
        int position = find(target);
        for (int i = position; i < size(); i++) 
            list[i] = list[i +1];
        list[position] = element;
        count++;
        modCount.increment();
    }

    @Override
    public void addToFront(T element) {
        if (size() == list.length)
            expand();
        for (int i = count; i > 0; i--)
            list[i] = list[i - 1];
        list[0] = element;
        count++;
        modCount.increment();
    }

    @Override
    public void addToRear(T element) {
        if (size() == list.length)
            expand();
        list[count++] = element;
        modCount.increment();
    }

}
