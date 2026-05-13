class Solution {

    public int minMoves(int[] nums, int limit) {

        int n = nums.length;

        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {

            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);

            // default: 2 moves
            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            // 1 move range
            diff[a + 1] -= 1;
            diff[b + limit + 1] += 1;

            // 0 move exact sum
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
        }

        int ans = Integer.MAX_VALUE;

        int curr = 0;

        for (int s = 2; s <= 2 * limit; s++) {

            curr += diff[s];

            ans = Math.min(ans, curr);
        }

        return ans;
    }
}