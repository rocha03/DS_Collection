package DataStructs.List.Iterators;

/**
 * A class that tracks the modification count of a data structure.
 * This is primarily used to detect concurrent modifications, ensuring that 
 * iterators or other components can detect when the data structure is 
 * modified during iteration or access.
 */
public class ModCount {

    /**
     * The modification count. Each time the data structure is modified, this value is incremented.
     */
    protected int value;

    /**
     * Constructs a new ModCount instance, initializing the modification count to 0.
     */
    public ModCount() {
        this.value = 0;
    }

    /**
     * Returns the current modification count.
     *
     * @return the current modification count value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Increments the modification count by 1. 
     * This should be called whenever the data structure undergoes a structural modification.
     */
    public void increment() {
        this.value++;
    }
}
