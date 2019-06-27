package algorithms4.Search;

/**
 * Created by Ryan on 2017/4/12.
 */
public class TwoArrayBinarySearch<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public TwoArrayBinarySearch(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
       if (isEmpty())
           return null;
       int i = rank(key);
       if (keys[i].equals(key))
           return values[i];
       return null;
    }

    public boolean isEmpty() {
        return N == 0;
    }


    public void put(Key key, Value value) {
        int i = rank(key);
        if (keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (key.compareTo(keys[mid]) > 0)
                lo = mid + 1;
            if (key.compareTo(keys[mid]) == 0)
                return mid;
            if (key.compareTo(keys[mid]) < 0)
                hi = mid - 1;
        }
        return lo;
    }

    public boolean delete(Key key) {
        int i = rank(key);
        if (keys[i].equals(key)) {
            for (int j = i; j < N; j++) {
                keys[j] = keys[j+1];
                values[j] = values[j+1];
            }
            return true;
        }
        return false;
    }
}
