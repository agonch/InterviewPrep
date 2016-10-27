import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        PriorityQueue<Integer> minH = new PriorityQueue<Integer>(n);
        PriorityQueue<Integer> maxH = new PriorityQueue<Integer>(n, new Comparator<Integer>() {
            public int compare(Integer l, Integer r) {
                return r - l;
            }
        });
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
            // For the first two elements add smaller one to the maxHeap on the left, 
            // and bigger one to the minHeap on the right.
            if (a_i == 1) {
                minH.add(Math.max(a[0], a[1]));
                maxH.add(Math.min(a[0], a[1]));
                System.out.println((a[0] + a[1]) / 2.0);
            } else if (a_i > 1) {
                /*
                1: Add next item to one of the heaps
                  - if next item is smaller than maxHeap root add it to maxHeap, else add it to minHeap
                2: Balance the heaps (size of both heaps differ by at most 1 after)
                  - remove root element from the one containing more elements and add to the other one 
                */
                if ((double) a[a_i] < maxH.peek()) {
                    maxH.add(a[a_i]);
                } else {
                    minH.add(a[a_i]);
                }
                if (Math.abs(minH.size() - maxH.size()) == 2) {
                    if (minH.size() < maxH.size()) {
                        minH.add(maxH.poll());
                    } else {
                        maxH.add(minH.poll());
                    }
                }
                System.out.println(getMedian(minH, maxH));
            } else {
                // index 0 case
                System.out.println((double) a[0]);
            }
        }
    }
    
    public static double getMedian(PriorityQueue<Integer> minH, PriorityQueue<Integer> maxH) {
        if (minH.size() > maxH.size()) {
            return (double) minH.peek();
        } else if (minH.size() < maxH.size()) {
            return (double) maxH.peek();
        } else {
            return (minH.peek() + maxH.peek()) / 2.0;
        }
    }
}
