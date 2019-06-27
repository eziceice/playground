package algorithms4.Sort;

/**
 * Created by Ryan on 2017/4/6.
 */
public class ShellSort {
    public void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N/3)
            h = 3*h + 1;
        while (h >= 1) {
            for (int i = h; i < N ; i++) {
                for (int j = i; j >= h ; j -= h) {
                    if (less(a[j],a[j-h]))
                        exch(a, j, j-h);
                }
            }
            h = h/3;
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
