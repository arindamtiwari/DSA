import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n];
        List<int[]>[] rev = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
        }
        
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new int[]{v, w});
            rev[v].add(new int[]{u, w});
        }
        
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        
        dist[0] = 0;
        pq.add(new long[]{0, 0});
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long d = curr[1];
            
            if (d > dist[u]) continue;
            
            // normal edges
            for (int[] e : graph[u]) {
                int v = e[0], w = e[1];
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.add(new long[]{v, dist[v]});
                }
            }
            
            // reversed edges (using switch at u)
            for (int[] e : rev[u]) {
                int v = e[0], w = e[1];
                if (dist[v] > d + 2L * w) {
                    dist[v] = d + 2L * w;
                    pq.add(new long[]{v, dist[v]});
                }
            }
        }
        
        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}