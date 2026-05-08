import java.util.*;

class Solution {

    public int minJumps(int[] nums) {

        int n = nums.length;

        if (n == 1) return 0;

        int MAX = 1000000;

        // Prime sieve
        boolean[] isPrime = new boolean[MAX + 1];

        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= MAX; i++) {

            if (isPrime[i]) {

                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // prime -> indices divisible by prime
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int val = nums[i];

            // Find divisors efficiently
            for (int d = 1; d * d <= val; d++) {

                if (val % d == 0) {

                    int d1 = d;
                    int d2 = val / d;

                    if (isPrime[d1]) {
                        map.computeIfAbsent(d1, k -> new ArrayList<>()).add(i);
                    }

                    if (d1 != d2 && isPrime[d2]) {
                        map.computeIfAbsent(d2, k -> new ArrayList<>()).add(i);
                    }
                }
            }
        }

        // BFS
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

                // left
                if (i - 1 >= 0 && !visited[i - 1]) {

                    visited[i - 1] = true;

                    q.offer(i - 1);
                }

                // right
                if (i + 1 < n && !visited[i + 1]) {

                    visited[i + 1] = true;

                    q.offer(i + 1);
                }

                int val = nums[i];

                // teleport only if current value itself is prime
                if (isPrime[val]) {

                    List<Integer> next = map.get(val);

                    if (next != null) {

                        for (int idx : next) {

                            if (!visited[idx]) {

                                visited[idx] = true;

                                q.offer(idx);
                            }
                        }

                        // CRITICAL optimization
                        map.remove(val);
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}