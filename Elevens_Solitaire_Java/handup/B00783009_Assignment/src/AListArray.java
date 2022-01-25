import java.util.Arrays;

public class AListArray <T> implements ListInterface <T>{

    private T[] list;
    private int numEntries;
    private int capacity;
    private final int DEFAULT_SIZE = 100;

    private void addCapacity(){
        capacity += DEFAULT_SIZE;
        list = Arrays.copyOf(list, capacity +1);
    }

    public AListArray(){
        T[] tempList =(T[]) new Object[DEFAULT_SIZE + 1];
        numEntries = 0;
        list = tempList;
        capacity = DEFAULT_SIZE;
    }
    @Override
    public void add(T newEntry) {
        if (numEntries == capacity) addCapacity();
        numEntries++;
        list[numEntries] = newEntry;
    }

    @Override
    public void add(int newPos, T newEntry) {
        if (newPos >= 1 && newPos <= numEntries +1){
            if (numEntries == capacity) addCapacity();
            for (int i = numEntries; i >= newPos; i--) list[i+1] = list[i];
            list[newPos] = newEntry;
            numEntries++;
        } else throw new IndexOutOfBoundsException("Error: OUT OF BOUNDS");

    }

    @Override
    public T remove(int position) {
        if (position >= 1 && position <= numEntries){
            T valueToReturn = list[position];
            for (int i = position; i < numEntries; i++) list[i] = list[i + 1];
            numEntries--;
            return valueToReturn;
        } else throw new IndexOutOfBoundsException("Error: OUT OF BOUNDS");
    }

    @Override
    public void clear() {
        numEntries = 0;
    }

    @Override
    public T replace(int position, T newEntry) {
        if (position >= 1 && position <= numEntries){
            T valueToReturn = list[position];
            list[position] = newEntry;
            return valueToReturn;
        } else throw new IndexOutOfBoundsException("Error: OUT OF BOUNDS");
    }

    @Override
    public T getEntry(int position) {
        if (position >= 1 && position <= numEntries){
            return list[position];
        } else throw new IndexOutOfBoundsException("Error: OUT OF BOUNDS");
    }

    public T[] toArray(){
        T[] array = (T[]) new Object[numEntries];
        System.arraycopy(list, 1, array, 0, numEntries);
        return array;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        int i = 1;
        while (i++ <= numEntries && !found)
            if (list[i].equals(anEntry)) found =true;
        return found;
    }

    // Method to return the position of an entry in the array
    public int getPos(T anEntry) {
        // Setting the index to an impossible value to indicate an error if it is returned in this form
        int index = -1;
        int i = 1;
        while (i++ <= numEntries)
            if (list[i].equals(anEntry)) index = i;
        System.out.println(index);
        return index;
    }

    public int getLength(){
        return numEntries;
    }

    @Override
    public boolean isEmpty() {
        return numEntries == 0;
    }
}
