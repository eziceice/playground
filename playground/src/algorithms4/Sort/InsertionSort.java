package algorithms4.Sort;

/**
 * Created by Ryan on 2017/4/5.
 */
public class InsertionSort {

    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length ; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j-1]))
                    exch(a,j,j-1);
            }
        }
    }

    public void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void show(Comparable[] a) {
        for (int i = 0; i < a.length ; i++)
            System.out.println(a[i]);
    }

    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length ; i++) {
            if (less(a[i], a[i-1]))
                return false;
        }
        return true;
    }
}
