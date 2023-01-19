import java.util.*;
import java.lang.*;

/**
 * Given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
 * Given A = [1, 3, 6, 4, 1, 2], should return 5.
 * Given A = [1, 2, 3], should return 4.
 * Given A = [−1, −3], should return 1.
 *
 * O(N) runtime, O(1) auxiliary storage!
 */

class FirstLargestPositiveInArray {

    private void swap(int[] A, int l, int r) {
        int temp = A[r];
        A[r] = A[l];
        A[l] = temp;
    }

    /** Get first non positive pivot point starting from given idx */
    private int getFirstPositiveIdx(int[] A, int start_i) {
        int lefti = start_i;
        while (lefti < A.length && A[lefti] <= 0) {
            lefti++;
        }
        return lefti;
    }

    public int solution(int[] A) {
        // move all negatives to the left side
        int lefti = getFirstPositiveIdx(A, 0);
        // keep this invariant: lefti is first positive index in A
        int righti = A.length - 1;
        while (righti > lefti) {
            if (A[righti] <= 0) {
                swap(A, lefti, righti);
                lefti = getFirstPositiveIdx(A, lefti + 1);
            }
            righti--;
        }

        int N = A.length - lefti; // length of positive section
        if (N == 0) {
            return 1;
        }
        
        // Use positive section of the array for in-place marking
        // of values. Zero-based index used to label existence.
        //
        // Example:
        // |---------- A.length----------------|
        //                |-------- N ---------|
        // [-1, 0, -2,    1,   3,  6,  4, 1,  2]  Before labeling
        // [-1, 0, -2,   -1,  -3, -6, -4, 1, -2]  After labeling
        //                ^           
        //                |        
        //                lefti               
        for (int i = 0; i < N; i++) {
            int e = Math.abs(A[lefti + i]);
            int e_idx = lefti + e - 1;
            // if not yet labeled, and within range...
            if (e <= N && A[e_idx] > 0) {
                // label as a negative IN PLACE
                A[e_idx] = -A[e_idx];
            }
        }
        
        // Now find first non-labeled value
        int min = 1;
        while (min <= N && A[lefti + min - 1] < 0) {
            min++;
        }
        return min;
    }
}
