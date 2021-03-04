// C++ program for Merge Sort
#include <iostream>
using namespace std;

// Merges two subarrays of arr[].
// First subarray is arr[l..m]
// Second subarray is arr[m+1..r]
void merge(int arr[], int l, int m, int r)
{
	// Copy data to temp array L[]. An alternative way to reduce space 
    // overhead to n/2 to maintain left and right as a combined structure, 
    // copy only the left part of arr into temporary space, and to direct 
    // the merge routine to place the merged output into arr
    int nL = m - l + 1;
    int L[nL];
	for (int i = 0; i < nL; i++) {
		L[i] = arr[l + i];
    }

	// Merge the temp arrays back into arr[l..r]

	int i = 0; 	   // Initial index of first subarray
	int j = m + 1; // Initial index of second subarray
	int k = l;     // Initial index of merged subarray

	while (i < nL && j <= r) {
		if (L[i] <= arr[j]) {
			arr[k] = L[i];
			i++;
		}
		else {
			arr[k] = arr[j];
			j++;
		}
		k++;
	}

	// Copy the remaining elements of L[], if there are any
	while (i < nL) {
		arr[k] = L[i];
		i++;
		k++;
	}
}

// l is for left index and r is
// right index of the sub-array
// of arr to be sorted */
void mergeSort(int arr[],int l,int r){
	if(l>=r){
		return;//returns recursively
	}
	int m =l+ (r-l)/2;
	mergeSort(arr,l,m);
	mergeSort(arr,m+1,r);
	merge(arr,l,m,r);
}

// UTILITY FUNCTIONS
// Function to print an array
void printArray(int A[], int size)
{
	for (int i = 0; i < size; i++)
		cout << A[i] << " ";
}

bool isSorted(int A[], int size) {
    for (int i = 0; i < size - 1; i++) {
        if (A[i] > A[i+1]) {
            return false;
        }
    }
    return true;
}

// Driver code
int main()
{
	int arr[] = { 12, 11, 13, 5, 6, 7, 1, 4, 45, 1, 45, 12, 3, 3 };
	int arr_size = sizeof(arr) / sizeof(arr[0]);

	cout << "Given array is \n";
	printArray(arr, arr_size);

	mergeSort(arr, 0, arr_size - 1);

	cout << "\nSorted array is \n";
	printArray(arr, arr_size);

    cout << "\nIs Sorted: " << isSorted(arr, arr_size);
	return 0;
}

// This code is contributed by Mayank Tyagi
