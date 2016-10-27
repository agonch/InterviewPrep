import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        if (k == 0 || k == a.length) {
            return a;
        }
        // return arrayLeftRotationUsingExtraArray(a, n, k);
        return arrayLeftRotationInPlaceUsingReverse(a, n, k);
        
    }
    
    public static int[] arrayLeftRotationInPlaceUsingReverse(int[] a, int n, int k) {
        reverse(a, 0, n);
        reverse(a, 0, n - k);
        reverse(a, n - k, n);
        return a;
    }
    
    // reverses an array between indices 'from' and [exclusive] 'to'
    public static void reverse(int[] a, int from, int to) {
        for (int i = from; i < (from + to) / 2; i++) {
            int temp = a[i];
            a[i] = a[to - 1 - (i - from)];
            a[to - 1 - (i - from)] = temp;
        }
    }
    
    public static int[] arrayLeftRotationUsingExtraArray(int[] a, int n, int k) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int pos = i - k;
            if (pos < 0) {
                pos += n;
            }
            res[pos] = a[i];
        }
        return res;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
      
        int[] output = new int[n];
        output = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(output[i] + " ");
      
        System.out.println();
      
    }
}
