
public class SpiralMatrixPrint {

    public static void main(String[] args) {
        int[][] M1 = {
            {1,  2,  3,  4,  5,  6},
            {11, 12, 13, 14, 15, 16},
            {21, 22, 23, 24, 25, 26},
        };
        int[][] M2 = {
            {1,  2,  3},
            {4,  5,  6},
            {7,  8,  9},
            {10, 11, 12},
            {13, 14, 15},
        };
        printMatrix(M1);
        printMatrix(M2);
        
        // Other solutions: https://www.programcreek.com/2013/01/leetcode-spiral-matrix-java/
    }

    private static void printMatrix(int[][] M) {
        int[] rc = {0, 0};
        int direction = 0; // 0 = right, 1 = down, 2 = left, 3 = up

        // While we have more stuff to print
        while (visit(M, rc)) {
            // Try to move once into a certain direction
            if (!walkIfCan(M, rc, direction)) {
                // Try moving once in the next direction
                direction = (direction + 1) % 4;
                walkIfCan(M, rc, direction);
            }
        }
    }

    private static boolean visit(int[][] M, int[] rc) {
        if (M[rc[0]][rc[1]] > 0) {
            System.out.print(M[rc[0]][rc[1]] + ", ");
            M[rc[0]][rc[1]] *= -1;
            return true;
        } else {
            System.out.println();
            return false;
        }
    }

    // Update coordinates if you can walk in that direction and return
    // true if moved, false otherwise
    private static boolean walkIfCan(int[][] M, int[] rc, int direction) {
        int rows_total = M.length;
        int cols_total = M[0].length;

        switch (direction) {
            case 0:
                if (rc[1] < cols_total - 1 && M[rc[0]][rc[1] + 1] > 0) {
                    rc[1]++;
                    return true;
                }
                return false;
            case 1:
                if (rc[0] < rows_total - 1 && M[rc[0] + 1][rc[1]] > 0) {
                    rc[0]++;
                    return true;
                }
                return false;
            case 2:
                if (rc[1] > 0 && M[rc[0]][rc[1] - 1] > 0) {
                    rc[1]--;
                    return true;
                }
                return false;
            case 3:
                if (rc[0] > 0 && M[rc[0] - 1][rc[1]] > 0) {
                    rc[0]--;
                    return true;
                }
                return false;
            default:
                throw new IllegalArgumentException("wrong direction: " + direction);
        }
    }

}