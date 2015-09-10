public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[][] inits = new int[m][n];
        
        // compute inits
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int neighbMinInit = 
                    (i == m - 1) 
                    ? 
                        (j == n - 1)
                        ? 1 
                        : inits[i][j + 1]
                    : 
                        (j == n - 1)
                        ? inits[i + 1][j]
                        : Math.min(inits[i + 1][j], inits[i][j + 1]);
                inits[i][j] = Math.max(neighbMinInit - dungeon[i][j], 1);
            }
        }
        return inits[0][0];
    }
}
