import java.util.*;

public class Solution {
    Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap;
    String magazine, note;
    
    public Solution(String magazine, String note) {
        this.magazine = magazine;
        this.note = note;
        this.magazineMap = new HashMap<String, Integer>();
        // this.noteMap = new HashMap<String, Integer>();
    }
    
    public boolean solve() {
        String[] magazineArr = this.magazine.split("\\s+");
        int magazine_indx = 0;
        
        for (String noteStr : this.note.split("\\s+")) {
            if (!this.magazineMap.containsKey(noteStr) || this.magazineMap.get(noteStr) == 0) {
                // search through magazine till find noteStr (and count other strings)
                while (magazine_indx < magazineArr.length && !magazineArr[magazine_indx].equals(noteStr)) {
                    updateCount(magazineArr[magazine_indx], this.magazineMap, 1);
                    magazine_indx++;
                }
                if (magazine_indx == magazineArr.length) {
                    return false; // reach end not having found noteStr in magazine
                }
                if (magazineArr[magazine_indx].equals(noteStr)) {
                    updateCount(noteStr, this.magazineMap, 1);
                    magazine_indx++;
                }
            }
            updateCount(noteStr, this.magazineMap, -1);
        }
        return true;  // reached end of ransom note having found all words in magazine
    }
    
    private void updateCount(String s, Map<String, Integer> map, int update) {
        if (map.containsKey(s)) {
            map.put(s, map.get(s) + update);
        } else {
            map.put(s, update);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        
        // Eat whitespace to beginning of next line
        scanner.nextLine();
        
        Solution s = new Solution(scanner.nextLine(), scanner.nextLine());
        scanner.close();
        
        boolean answer = s.solve();
        if(answer)
            System.out.println("Yes");
        else System.out.println("No");
      
    }
}
