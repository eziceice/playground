package algorithms4.Sort;

/**
 * Created by Ryan on 2017/4/9.
 */
public class HeapSort {
    private Comparable[] pq;
    private int N = 0; // 数组中含有的Object数量
    public HeapSort(int maxSize) {
        pq = new Comparable[maxSize];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Comparable temp = pq[j];
        pq[j] = pq[i];
        pq[i] = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2,k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k, int length) {
        while (2*k <= length) {
            int j = 2*k;
            if (less(j,j+1))
                j++;
            if (!less(k,j))
                break;
            exch(k,j);
            k = j;
        }
    }

    public boolean isEmpty() {
        return pq.length == 0;
    }

    public void insert(Comparable a) {
        pq[++N] = a;
        swim(N);
    }

    public Comparable delMax() {
        Comparable max = pq[1];
        exch(1, N);
        pq[N] = null;
        N--;
        sink(1, N);
        return max;
    }


    public void sort(Comparable[] a) {
        int length = a.length;
        for (int i = length/2; i > 1 ; i--)
            sink(i,length);
        while (length > 1) {
            exch(1,length--);
            sink(1,length);
        }
    }
}
