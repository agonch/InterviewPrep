#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>


/* output each array element's value */
void printArray(int* arr, int k) {
  int j;
  printf("[ ");
  for (j = 0; j < k; j++ ) {
    printf("%d ", arr[j]);
  }
  printf("]\n");
}

void printArray__v2(int arr[], int k) {
  int j;
  printf("[ ");
  for (j = 0; j < k; j++ ) {
    printf("%d ", arr[j]);
  }
  printf("]\n");
}

void print2DArray(int* m, int r, int c) {
    int i;
    printf("{\n");
    for (i = 0; i < r; i++) {
        printArray(m + i*c, c);
    }
    printf("}\n");
}

void print2DArray__v2(int r, int c, int m[][c]) {
    int i,j;
    printf("{\n");
    for (i = 0; i < r; i++) {
        printArray(m[i], c);
    }
    printf("}\n");
}

int main() {
    int a[7] = {1, 2, 3, 4, 5, 6, 7};
    int m[2][4] = {
        {8, 9, 10, 11},
        {12, 13, 14, 15}
    };
    printArray(a, 7);
    printArray__v2(a, 7);
    printArray(m[0], 8);
    printArray__v2(m[0], 8);
    printf("Sizes: %d %d\n", sizeof(a) / sizeof(int), sizeof(m) / sizeof(int));
    printf("--------\n");
    print2DArray(a, 2, 3);
    print2DArray__v2(2, 3, (int (*)[(int)(3)]) a);
    printf("--------\n");
    print2DArray(&(m[0][0]), 2, 4);
    print2DArray((int *)m, 2, 4);
    print2DArray__v2(2, 4, m);
    return 0;
}