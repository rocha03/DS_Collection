package DataStructs.List.Iterators;

/**
 * 
 */
public class ModCount {
    /**
     * 
     */
    protected int value;

    /**
     * 
     */
    public ModCount() {
        this.value = 0;
    }

    /**
     * 
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * 
     * @param value
     */
    public void increment() {
        this.value++;
    }
}
