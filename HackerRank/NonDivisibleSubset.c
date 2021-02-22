#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int max(int a, int b) {
  return a > b ? a : b;
}

/* output each array element's value */
void printArray(int* arr, int k) {
  int j;
  printf("[");
  for (j = 0; j < k; j++ ) {
    printf("%d ", arr[j]);
  }
  printf("]\n");
}

int nonDivisibleSubset(int k, int s_count, int* s) {
    int i;
    int f[k];
    memset(f, 0, sizeof(f));
    for (i = 0; i < s_count; i++) {
      f[s[i] % k]++;
    }
    if (k % 2 == 0 && f[k/2] > 1) {
      f[k/2] = 1;
    }
    int res = f[0] == 0 ? 0 : 1;
    for (i = 1; i <= k/2; i++) {
      res += max(f[i], f[k-i]);
    }
    return res;
}

int main() {
    int n = 7;
    int k = 4;
    int s[] = {19, 10, 12, 10, 24, 25, 22};
    int result = nonDivisibleSubset(k, n, s);
    printArray(s, n);
    printf("res=%d", result);
    return 0;
}