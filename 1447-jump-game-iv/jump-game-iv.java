import java.util.*;

class Solution {

    public int minJumps(int[] arr) {

        int n = arr.length;

        if (n == 1) return 0;

        // value -> indices
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> q = new LinkedList<>();

        boolean[] visited = new boolean[n];

        q.offer(0);

        visited[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int i = q.poll();

                if (i == n - 1) {
                    return steps;
                }

                // i - 1
                if (i - 1 >= 0 && !visited[i - 1]) {

                    visited[i - 1] = true;

                    q.offer(i - 1);
                }

                // i + 1
                if (i + 1 < n && !visited[i + 1]) {

                    visited[i + 1] = true;

                    q.offer(i + 1);
                }

                // same value jumps
                List<Integer> next = map.get(arr[i]);

                if (next != null) {

                    for (int idx : next) {

                        if (!visited[idx]) {

                            visited[idx] = true;

                            q.offer(idx);
                        }
                    }

                    // IMPORTANT OPTIMIZATION
                    map.remove(arr[i]);
                }
            }

            steps++;
        }

        return -1;
    }
}