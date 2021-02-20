import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class permutations {

	public static int SIZE = 4;
	
	public static void main(String[] args) {
		int[] arr = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			arr[i] = i + 1;
		}
		List<Integer> lst = Arrays.stream(arr).boxed().collect(Collectors.toList());

		Collections.shuffle(lst);
		arr = lst.stream().mapToInt(Integer::intValue).toArray();
		System.out.println("Finding perms of: " + Arrays.toString(arr));
		// perm(arr, SIZE, 0, 0);
		iterative_perm(arr);
	}

	public static void practiceDoubleArray() {
		double[] dl = {1.0};
		List<Number> ln = Arrays.stream(dl).boxed().collect(Collectors.toList());
		// what is collector?
		dl = ln.stream().mapToDouble(Number::doubleValue).toArray();
	}
	
	/* recursive function to generate permutations of the array 
	 * from element start to element size-1 */
	public static void perm (int arr[], int size, int start, int recursiveCall) {
		/* if we are at the end of the array, we have one permutation
		 * we can use (here we print it; or could be handed off elsewhere) */
		 String spacing = spacing(recursiveCall);
		if (start == size) {
			System.out.println(/*spacing + "Permutation: " + */Arrays.toString(arr));
		} else {
			/* recursively explore the permutations starting
			 * at index start going through index size-1 */
				for (int j = start; j < size; j++) {
					//System.out.println(spacing + "start = " + start + ", j = " + j + ", array = " + Arrays.toString(arr));
					// try the array with start and j switched
					swap (arr, start, j);
					perm (arr, size, start + 1, recursiveCall + 1);
					// swap them back the way they were
					swap (arr, start, j);
				}
			}
	}

	public static void iterative_perm(int perm[]) {
		Arrays.sort(perm);
		System.out.println(Arrays.toString(perm));
		int factorial = 1;
		for (int i = 1; i < perm.length + 1; i++) {
			factorial *= i;
		}
		while (factorial > 1) {
			nextPerm(perm);
			System.out.println(Arrays.toString(perm));
			factorial--;
		}
	}

	/**
	 * https://github.com/agonch/InterviewPrep/blob/master/Permutations.pdf
	 * Iterative Permutation, using a 'successor function' that finds
	 * the next permutation in lexographical order. This in-place updates
	 * the array the following permutation.
	 * Ex.
	 * 	 1 2 3 4
	 *   1 2 4 3
	 *   1 3 4 2 --> 1 3 2 4
	 *   1 3 4 2
	 *   1 4 3 2 --> 1 4 2 3
	 *   1 4 3 2
	 *   2 1 3 4
	 *   2 1 4 3
	 *   2 3 1 4
	 *   2 3 4 1
	 *   2 4 3 1 --> 2 4 1 3
	 *   2 4 3 1
	 *   3 2 1 4 --> 3 1 2 4
	 */
	public static void nextPerm(int perm[]) {
		int left = perm.length - 2;
		while (left >= 0 && perm[left] > perm[left+1]) {
			// find first pair in ascending order from right side
			left--;
		}
		int next_smallest = left + 1;
		for (int i = left + 1; i < perm.length; i++) {
			// find next smallest item to swap
			if (perm[i] > perm[left] && perm[i] < perm[next_smallest]) {
				next_smallest = i;
			}
		}
		swap(perm, left, next_smallest);
		Arrays.sort(perm, left+1, perm.length);
	}
	
	public static void swap (int a[], int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static String spacing(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			s += "  ";
		}
		return s;
	}
}