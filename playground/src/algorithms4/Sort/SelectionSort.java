package algorithms4.Sort;

/**
 * Created by Ryan on 2017/4/5.
 */
public class SelectionSort {
    public void sort(Comparable[] a) {
        int min = 0;
        for (int i = 0; i < a.length ; i++) {
            for (int j = 1; j < a.length; j++) {
                if (less(a[min], a[j]))
                    min = j;
            }
            exch(a,i,min);
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
