import java.util.*;

class Solution {
    
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}}; // up, right, down, left
    
    // allowed directions per street
    int[][] type = {
        {},                 // dummy (0 index)
        {3,1},              // 1
        {0,2},              // 2
        {3,2},              // 3
        {1,2},              // 4
        {3,0},              // 5
        {1,0}               // 6
    };
    
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        boolean[][] vis = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[]{0,0});
        vis[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            
            if (x == m-1 && y == n-1) return true;
            
            for (int d : type[grid[x][y]]) {
                int nx = x + dirs[d][0];
                int ny = y + dirs[d][1];
                
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (vis[nx][ny]) continue;
                
                // check reverse connection
                int rev = (d + 2) % 4;
                for (int nd : type[grid[nx][ny]]) {
                    if (nd == rev) {
                        vis[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                        break;
                    }
                }
            }
        }
        
        return false;
    }
}