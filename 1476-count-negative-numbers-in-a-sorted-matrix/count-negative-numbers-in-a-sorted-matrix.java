class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        int i = m - 1; // bottom row
        int j = 0;     // left column
        
        int count = 0;
        
        while (i >= 0 && j < n) {
            if (grid[i][j] < 0) {
                count += (n - j); // all elements to right are negative
                i--; // move up
            } else {
                j++; // move right
            }
        }
        
        return count;
    }
}