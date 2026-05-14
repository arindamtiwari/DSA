class Solution {

    public boolean isGood(int[] nums) {

        int n = 0;

        // find max
        for (int x : nums) {
            n = Math.max(n, x);
        }

        // length must be n + 1
        if (nums.length != n + 1) {
            return false;
        }

        int[] freq = new int[n + 1];

        // count frequencies
        for (int x : nums) {

            if (x > n) return false;

            freq[x]++;
        }

        // check 1 to n-1 appear once
        for (int i = 1; i < n; i++) {

            if (freq[i] != 1) {
                return false;
            }
        }

        // n must appear twice
        return freq[n] == 2;
    }
}