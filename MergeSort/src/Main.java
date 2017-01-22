/**
 * Created by anybi on 22.01.2017.
 */

public class Main {
    public static void main(String args[]) throws Exception {
      /*  int[] A = {45, 1, 0, 34, 2, 67, 233, 643, 11, 53, 2};
        System.out.println("Initialized array: \n");
        for (int i=0; i<A.length; i++){
            System.out.print(A[i]+" ");
        }
        MergeSort(A,0,A.length-1);
        System.out.println(" \n Sorted array: \n");
        for (int i=0; i<A.length; i++){
            System.out.print(A[i]+" ");
        }
        */
        MergeSorterTest tester = new MergeSorterTest();
        try {
            tester.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tester.testMergeSort();
        tester.itWorksRepeatably();
        tester.testStandartSort();


    }

    public static void MergeSort(int[] A, int p, int r) {
        int q;
        if (p < r) {
            q = (p + r) / 2;
            MergeSort(A, p, q);
            MergeSort(A, q + 1, r);
            Merge(A, p, q, r);

        }
    }

    public static void Merge(int[] A, int p, int q, int r) {
        int[] L, R;
        L = new int[q - p + 1+1];
        R = new int[r - q+1];

        for (int i = 0; i < L.length-1; i++) {
            L[i] = A[p + i];
        }
        for (int i = 0; i < R.length-1; i++) {
            R[i] = A[q + 1 + i];
        }
        L[L.length-1] = 9999999;
        R[R.length-1] = 9999999;
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
