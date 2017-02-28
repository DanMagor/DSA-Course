/**
 * Created by Anton Skudarnov on 22.02.2017.
 */
public class MergeSort {
    /**
     *
     * @param A unsorted array.
     * @param p left border of sorting
     * @param r right border of sorting
     */
    public static void sort(double[] A, int p, int r) {
        int q;
        if (p < r) {
            q = (p + r) / 2;
            sort(A, p, q);
            sort(A, q + 1, r); //recursive call for left and right parts
            merge(A, p, q, r);

        }
    }

    /**
     *
     * @param A input array
     * @param p left index
     * @param q middle index
     * @param r right index
     */
    public static void merge(double[] A, int p, int q, int r) {
        double[] L, R;
        L = new double[q - p + 1+1];
        R = new double[r - q+1];

        for (int i = 0; i < L.length-1; i++) {
            L[i] = A[p + i];
        }
        for (int i = 0; i < R.length-1; i++) {
            R[i] = A[q + 1 + i];
        }
        L[L.length-1] = Double.POSITIVE_INFINITY;
        R[R.length-1] = Double.POSITIVE_INFINITY; // flags. They are shows the end of arrays.
        int i = 0;  int j = 0;
        for (int k = p; k <= r; k++) {

            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }


        }
    }
}
