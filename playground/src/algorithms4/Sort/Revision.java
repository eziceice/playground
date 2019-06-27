package algorithms4.Sort;

/**
 * Created by Ryan on 2017/4/23.
 */
public class Revision {
}

class SeSort {

    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length ; i++) {
            Comparable min = a[i];
            for (int j = i; j < a.length ; j++) {
                if (min.compareTo(a[j]) > 0)
                    min = a[j];
            }
            exch(a[i], min);
        }
    }

    public static void exch(Comparable a, Comparable b) {
        Comparable temp = b;
        b = a;
        a = temp;
    }
}

class InseSort{
    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length ; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j-1].compareTo(a[j]) > 0)
                    SeSort.exch(a[j-1],a[j]);
            }
        }
    }
}

class SheSort {
    public void sort(Comparable[] a) {
        int h = 1;
        int N = a.length;

        while (h < N/3) {
            h = 3 * h + 1;
        }

        while (h > 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h ; j -= h) {
                    if (a[j].compareTo(a[j-h]) < 0)
                        SeSort.exch(a[j],a[j-h]);
                }
            }
            h = h/3;
        }
    }
}

class MeSort{
    public void Merge(Comparable[] a,Comparable[] aux,int lo, int hi) {

        int mid = lo + (hi-lo)/2;
        int i = lo;
        int j = mid + 1;

        for (int k = 0; k < a.length; k++) {
            aux[k] = a[k];
        }

        for (int k = 0; k < a.length; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) > 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public void sortR(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sortR(a,aux,0,a.length);
    }

    private void sortR(Comparable[] a, Comparable[] aux, int lo,int hi) {
        if (lo >= hi)
            return;
        int mid = lo + (hi - lo)/2;
        sortR(a,aux,lo,mid);
        sortR(a,aux,mid + 1, lo);
        Merge(a,aux,lo,hi);
    }

    public void sortNR(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        for (int i = 1; i < a.length ; i += i) {
            for (int j = 0; j < a.length; j += i + i) {
                int lo = j;
                int hi = j + 2*i - 1;
                int mid = lo + (hi - lo)/2;
                Merge(a,aux,j,Math.min(hi,a.length-1));
            }
        }
    }
}

class QSort {
    public int partition(Comparable[] a, int lo, int hi) {
       int i = lo;
       int j = hi;
       int mid = lo + (hi - lo)/2;
       Comparable v = a[lo];
       while (true) {
           while (a[++i].compareTo(v) < 0) {
               if (i == hi)
                   break;
           }
           while (a[j--].compareTo(v) > 0) {
           }
           if (i >= j)
               break;
           SeSort.exch(i,j);
       }
       SeSort.exch(lo,j);
       return j;
    }

    public void sort(Comparable[] a,int lo, int hi) {
        if (lo >= hi)
            return;
        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
}

class HSort{
    int N;
    Comparable[] a = new Comparable[N];

    public void swim(int k) {
        while (k > 1) {
            if (a[k].compareTo(a[k/2]) > 0) {
                SeSort.exch(a[k],a[k/2]);
                k = k/2;
            }
        }
    }

    public void sink(int k, int N) {
        while (2*k < N) {
            int j = 2 * k;
            while (a[j].compareTo(a[j+1]) < 0)
                j++;
            if (a[k].compareTo(a[j]) > 0)
                break;
            SeSort.exch(a[k],a[j]);
        }
    }

    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = a.length/2 ; i > 1 ; i--) {
            sink(i,N);
        }
        while (N > 1) {
            SeSort.exch(a[1],a[N--]);
            sink(1,N);
        }
    }
}