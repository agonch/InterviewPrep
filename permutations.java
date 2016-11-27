import java.util.Arrays;

public class permutations {

	public static int SIZE = 4;
	
	public static void main(String[] args) {
		int[] arr = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			arr[i] = i + 1;
		}
		perm(arr, SIZE, 0, 0);
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