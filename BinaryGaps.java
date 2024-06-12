/* 
  A binary gap within a positive integer N is any maximal sequence of consecutive zeros that 
  is surrounded by ones at both ends in the binary representation of N. Find the largest gap.
  (1001) -> 2
  (1000010001) -> 4
  (10100) -> 1
  (1111) -> 0
  (100000) -> 0
*/

import java.util.*;

class Solution { // O(n)
  
    public int solution(int N) {
        if (N == 0) {
            return 0;
        }

        N = N >>> countConsecutiveLSBs(N, 0);

        int max_gap = 0;
        int curr_gap = 0;
        N = N >>> 1;
        while (N != 0) {
            curr_gap = countConsecutiveLSBs(N, 0);
            max_gap = Math.max(max_gap, curr_gap);
            N = N >>> (curr_gap + countConsecutiveLSBs(N, 1));
        }
        return max_gap;
    }

    // Count consecutive LSB in N of given val:
    //   (0b1011, 1) -> 2 
    //   (0b1000, 0) -> 3 
    private static int countConsecutiveLSBs(int N, int val) {
        int count = 0;
        int lsb = N & 1;
        while (N != 0 && lsb == val) {
            count++;
            N = N >>> 1;
            lsb = N & 1;
        }
        return count;
    }
}
