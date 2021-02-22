package HackerRank;

import java.util.Arrays;
import java.util.List;

public class NonDivisibleSubset {
    public static void main(String[] args) {
        nonDivisibleSubset(1, Arrays.asList(19, 10, 12, 10, 24, 25, 22));
        nonDivisibleSubset(2, Arrays.asList(19, 10, 12, 10, 24, 25, 22));
        nonDivisibleSubset(3, Arrays.asList(19, 10, 12, 10, 24, 25, 22));
        nonDivisibleSubset(4, Arrays.asList(19, 10, 12, 10, 24, 25, 22));
        nonDivisibleSubset(5, Arrays.asList(19, 10, 12, 10, 24, 25, 22));
        nonDivisibleSubset(6, Arrays.asList(19, 10, 12, 10, 24, 25, 22));
        nonDivisibleSubset(7, Arrays.asList(19, 10, 12, 10, 24, 25, 22));
    }

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        if (k < 1) {
            throw new IllegalArgumentException("Illegal k: " + k);
        }
        int[] f = new int[k]; // freuency of modulo values
        for (int e: s) {
            f[e % k]++;
        }
        int res = f[0] == 0 ? 0 : 1; // Can't have two elements in subset that divide by k
        if (k % 2 == 0 && f[k/2] > 1) {
            f[k/2] = 1;
        }
        for (int i = 1; i <= k / 2; i++) {
            //  Given a number whose modulus is m: if m is even, two k/2 mods will sum
            //  and be divisibile by k. Summing m and k-m will also be divisible by k.
            //  Choosing all numbers with modulus m won't have any two that sum to something
            //  divisible by k, but this eliminates all elements from k-m bucket. We
            //  handled the k/2 case above, ensuring only one (if any) gets added.
            res += Math.max(f[i], f[k-i]);
        }       

        System.out.println("k = " + k + " s = " + s.toString() + " res = " + res + " f(index is %k, value is count) = " + Arrays.toString(f));
        return res;
    }
}
