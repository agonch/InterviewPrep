package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * Input: grid = [
 *  ["1","1","0","1","1"],
 *  ["1","1","0","1","0"],
 *  ["1","1","0","0","1"],
 *  ["0","0","0","1","1"]
 * ]
 * Output 3:
 * Variation of standard problem "Count number of connected components in undirected graph"
 * Solve by applying DFS() on each component. After a component or a sub-graph is visited,
 * we call DFS on the next un-visited component. The number of DFS() calls gives the number
 * connected componenets.
 */
public class IslandCounts2DArray {
    public final int ROW = 5, COL = 5;

    // These arrays are used to get row and column numbers 
    // of 4 neighbors of a given cell.
    private int rowNbr[] = new int[] {-1,  0, 0, 1}; 
    private int colNbr[] = new int[] { 0, -1, 1, 0}; 

    public static void main(String[] args) {
        IslandCounts2DArray island = new IslandCounts2DArray();
        int[][] map = {
            {1,1,0,1,1},
            {1,1,0,1,0},
            {1,1,0,0,1},
            {0,0,0,1,1},
            {0,0,0,1,1}
        }; // Output 3
        int[][] map2 = {
            {1,0,1,0,1},
            {1,0,1,0,1},
            {1,1,1,1,1},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }; // Output 1
        int[][] map3 = {
            {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1}
        }; // Output 5
        pp(map);
        System.out.println(
            " recurse_1: " +  island.count_islands_recursive(map) 
            + " iterative_2: " + island.count_islands_iterative(map)
            + " iterative_in_place_3: " + island.count_islands_iterative_in_place(map));
        pp(map2);
        System.out.println(
            " recurse_1: " +  island.count_islands_recursive(map2) 
            + " iterative_2: " + island.count_islands_iterative(map2)
            + " iterative_in_place_3: " + island.count_islands_iterative_in_place(map2));        
        pp(map3);
        System.out.println(
            " recurse_1: " +  island.count_islands_recursive(map3) 
            + " iterative_2: " + island.count_islands_iterative(map3)
            + " iterative_in_place_3: " + island.count_islands_iterative_in_place(map3));
        }

    // Pretty print
    public static void pp(int[][] arrs) {
        System.out.println("[");
        for(int[] arr : arrs) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("]");
    }

    private boolean isSafeLand(int M[][], int r, int c) {
        return r >= 0 && r < ROW && c >= 0 && c < COL && M[r][c] == 1; 
    }

    public int count_islands_recursive(int M[][]) {
        // Make a bool array to mark visited cells. 
        boolean visited[][] = new boolean[ROW][COL]; 
        int count = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) { 
                if (M[i][j] == 1 && !visited[i][j]) {
                    // If a cell with value 1 is not visited yet, a new island is found!
                    // Visit all cells in this island and increment island count 
                    DFS_recursive(M, i, j, visited); 
                    count++; 
                }
            }
        }
        return count;
    }

    public int count_islands_iterative(int M[][]) {
        boolean visited[][] = new boolean[ROW][COL];
        Stack<Coord> worklist = new Stack<Coord>();
        int count = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) { 
                if (M[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    worklist.push(new Coord(i, j));
                    // If a cell with value 1 is not visited yet, a new island is found!
                    // Visit all cells in this island and increment island count 
                    DFS_iterative(M, worklist, visited); 
                    count++;
                }
            }
        }
        return count;
    }

    public int count_islands_iterative_in_place(int M[][]) {
        Stack<Coord> worklist = new Stack<Coord>();
        int count = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) { 
                if (M[i][j] == 1) {
                    worklist.push(new Coord(i, j));
                    DFS_iterative_in_place(M, worklist); 
                    count++;
                }
            }
        }
        return count;
    }

    public void DFS_recursive(int M[][], int row, int col, boolean visited[][]) {
        // Mark this cell as visited 
        visited[row][col] = true;
  
        // Recur for all connected unvisited neighbours 
        for (int k = 0; k < 4; k++) {
            int neighborRow = row + rowNbr[k];
            int neighborCol = col + colNbr[k];
            if (isSafeLand(M, neighborRow, neighborCol) && !visited[neighborRow][neighborCol]) {
                DFS_recursive(M, neighborRow, neighborCol, visited);
            }
        }
    }

    public void DFS_iterative(int M[][], Stack<Coord> worklist, boolean visited[][]) {
        while(!worklist.isEmpty()) {
            Coord next = worklist.pop();
            // For all unvisited safe neighbors...
            for (int k = 0; k < 4; k++) {
                int neighborRow = next.row + rowNbr[k];
                int neighborCol = next.col + colNbr[k];
                if (isSafeLand(M, neighborRow, neighborCol) && !visited[neighborRow][neighborCol]) {
                    visited[neighborRow][neighborCol] = true;
                    worklist.push(new Coord(neighborRow, neighborCol));
                }
            }
        }
    }

    // Use the map matrix as the visisted matrix as well. (-1 = visisted land)
    public void DFS_iterative_in_place(int M[][], Stack<Coord> worklist) {
        while(!worklist.isEmpty()) {
            Coord next = worklist.pop();
            // For all unvisited safe neighbors...
            for (int k = 0; k < 4; k++) {
                int neighborRow = next.row + rowNbr[k];
                int neighborCol = next.col + colNbr[k];
                if (isSafeLand(M, neighborRow, neighborCol)) {
                    M[neighborRow][neighborCol] = -1;
                    worklist.push(new Coord(neighborRow, neighborCol));
                }
            }
        }
    }

    public static class Coord {
        public int row, col;

        public Coord(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }
}
