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
        System.out.println(" recurse_1: " +  island.countIslands_1(map) + " iterative_2: " + island.countIslands_2(map));
        
        pp(map2);
        System.out.println(" recurse_1: " +  island.countIslands_1(map2) + " iterative_2: " + island.countIslands_2(map2));
        
        pp(map3);
        System.out.println(" recurse_1: " +  island.countIslands_1(map3) + " iterative_2: " + island.countIslands_2(map3));
    }

    public static void pp(int[][] arrs) {
        System.out.println("[");
        for(int[] arr : arrs) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("]");
    }

    private boolean isSafe(int M[][], int r, int c, boolean visited[][]) {
        return r >= 0 && r < ROW && c >= 0 && c < COL 
          && (M[r][c] == 1 && !visited[r][c]); 
    }

    public int countIslands_1(int M[][]) {
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

    public int countIslands_2(int M[][]) {
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

    public void DFS_recursive(int M[][], int r, int c, boolean visited[][]) {
        // These arrays are used to get row and column numbers 
        // of 4 neighbors of a given cell 
        int rowNbr[] = new int[] {-1,  0, 0, 1}; 
        int colNbr[] = new int[] { 0, -1, 1, 0}; 
  
        // Mark this cell as visited 
        visited[r][c] = true; 
  
        // Recur for all connected unvisited neighbours 
        for (int k = 0; k < 4; k++) {
            if (isSafe(M, r + rowNbr[k], c + colNbr[k], visited)) {
                DFS_recursive(M, r + rowNbr[k], c + colNbr[k], visited);
            }
        }
    }

    public void DFS_iterative(int M[][], Stack<Coord> worklist, boolean visited[][]) {
        int rowNbr[] = new int[] {-1,  0, 0, 1}; 
        int colNbr[] = new int[] { 0, -1, 1, 0};
        while(!worklist.isEmpty()) {
            Coord next = worklist.pop();
            // For all unvisited safe neighbors...
            for (int k = 0; k < 4; k++) {
                if (isSafe(M, next.row + rowNbr[k], next.col + colNbr[k], visited)) {
                    Coord child = new Coord(next.row + rowNbr[k], next.col + colNbr[k]);
                    visited[child.row][child.col] = true;
                    worklist.push(child);
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
