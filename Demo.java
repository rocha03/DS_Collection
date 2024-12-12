import java.util.Iterator;

import DataStructs.List.UnorderedList.LinkedUnorderedList;
import Interfaces.List.UnorderedListADT;

/**
 * Demo
 */
public class Demo {

    public static void main(String[] args) {
        // Tests

        UnorderedListADT<String> list = new LinkedUnorderedList<>();

        list.addToRear("1");
        list.addToRear("2");
        list.addToRear("3");
        list.addToRear("4");
        list.addToRear("5");
        list.addToRear("6");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
    }
}