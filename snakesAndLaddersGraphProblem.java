import java.io.*;
import java.util.*;

public class Solution {
    
    public static final int BOARD_SIDE_LEN = 10;
    public static final int ROLL = 6;

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int test = Integer.parseInt(br.readLine()); test > 0; test--) {
            int[][] board = makeNewGameBoard();
            int numLadders = Integer.parseInt(br.readLine());
            for (int i = 0; i < numLadders; i++) {
                String[] line = br.readLine().split(" ");
                int startOfLadder = Integer.parseInt(line[0]) - 1;
                int endOfLadder = Integer.parseInt(line[1]) - 1;
                board[cellToX(startOfLadder)][cellToY(startOfLadder)] = endOfLadder;
            }
            int numSnakes = Integer.parseInt(br.readLine());
            for (int i = 0; i < numSnakes; i++) {
                String[] line = br.readLine().split(" ");
                int startOfSnake = Integer.parseInt(line[0]) - 1;
                int endOfSnake = Integer.parseInt(line[1]) - 1;
                board[cellToX(startOfSnake)][cellToY(startOfSnake)] = endOfSnake;
            }
            printBoard(board);
            System.out.println("-------------------");
            System.out.println(solve(board));
        } 
    }
    
    private static int solve(int[][] board) {
        // max heap
        Queue<Integer> worklist = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) { return b.compareTo(a); }
        });
        worklist.add(board[cellToX(0)][0]);
        Set<Integer> seen = new HashSet<Integer>();
        Map<Integer, Integer> numMoves = new HashMap<Integer, Integer>();
        for (int i = 1; i <= 99; i++) {
            numMoves.put(i, 101);
        }
        numMoves.put(0, 0);

        while (!worklist.isEmpty()) {
            int n = worklist.remove();
            System.out.println("WORKLIST: " + worklist);
            System.out.println("n: " + n);
            int movesOld = numMoves.get(n);
            System.out.println("numMoves for n: " + movesOld);
            if (n == 99) {
                System.out.println("made it " + numMoves.get(99));
                return numMoves.get(99);
            }
            System.out.println("Neighbors:  " + getNeigborIndices(board, n));
            for (int w : getNeigborIndices(board, n)) {
                System.out.println("w: " + w + " already seen? " + seen.contains(w) 
                                    + "  numMoves for w: " + numMoves.get(w));
                if (!seen.contains(w)) { // perhaps seen is not needed, since we won't every end up in a cycle
                    if (movesOld + 1 < numMoves.get(w)) {
                        numMoves.put(w, movesOld + 1);
                        System.out.println("updating moves for w to " + (movesOld + 1));
                        worklist.add(w);
                        seen.add(w);
                    }
                }
            }
            
        }
        return -1;
    }
    
    private static List<Integer> getNeigborIndices(int[][] board, int from) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 1; i <= ROLL; i++) {
            if (from + i < 100) {
                neighbors.add(board[cellToX(from + i)][cellToY(from + i)]);
            }
        }
        return neighbors;
    }
    private static void printBoard(int[][] board) {
        for(int[] arr : board) {
            System.out.println(Arrays.toString(arr));
        }
    }
    private static int cellToX (int cell) {
        return BOARD_SIDE_LEN - 1 - cell / 10;
    }
    private static int cellToY (int cell) {
        return cell % 10;
    }
    private static int[][] makeNewGameBoard() {
        // initialize board
        final int[][] board = new int[BOARD_SIDE_LEN][BOARD_SIDE_LEN];
        for (int i = 0; i < 100; i++) {
            board[cellToX(i)][cellToY(i)] = i;
        }
        return board;
    }
}
