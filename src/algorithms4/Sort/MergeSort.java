package algorithms4.Sort;

/**
 * Created by Ryan on 2017/4/6.
 */
public class MergeSort {
}

class TopToBottomMergeSort {
    private Comparable[] aux;
    InPlaceMerge inPlaceMergeSort = new InPlaceMerge();

    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    public void sort(Comparable[] a, int low, int high) {
        if (high <= low)
            return;
        int mid = low + (high - low)/2;
        sort(a,low,mid);
        sort(a,mid+1,high);
        inPlaceMergeSort.merge(a,low,mid,high);
    }


    public static void main(String[] args) {
        String[] strings = new String[16];
        strings[0] = "M";
        strings[1] = "E";
        strings[2] = "R";
        strings[3] = "G";
        strings[4] = "E";
        strings[5] = "S";
        strings[6] = "O";
        strings[7] = "R";
        strings[8] = "T";
        strings[9] = "E";
        strings[10] = "X";
        strings[11] = "A";
        strings[12] = "M";
        strings[13] = "P";
        strings[14] = "L";
        strings[15] = "E";
        TopToBottomMergeSort topToBottomMergeSort = new TopToBottomMergeSort();
        topToBottomMergeSort.sort(strings);
        for (int i = 0; i <strings.length ; i++) {
            System.out.println(strings[i]);
        }

    }


}
class InPlaceMerge {

    public void merge(Comparable[] a, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        Comparable[] aux = new Comparable[a.length];
        for (int k = low; k <= high ; k++) {
            aux[k] = a[k];
        }

        for (int k = low; k <= high ; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > high)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

}

class BottomToTopMergeSort {
    private Comparable[] aux;
    InPlaceMerge inPlaceMergeSort = new InPlaceMerge();

    public void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz+sz) {
                inPlaceMergeSort.merge(a,lo,lo+sz-1, Math.min(lo+sz+sz-1,N-1));
            }
        }
    }
}
