class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;
        
        // 1. increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        
        // p must not be at start or end
        if (i == 0 || i == n - 1) return false;
        
        // 2. decreasing
        int j = i;
        while (j + 1 < n && nums[j] > nums[j + 1]) {
            j++;
        }
        
        // must have decreasing part
        if (j == i || j == n - 1) return false;
        
        // 3. increasing again
        while (j + 1 < n && nums[j] < nums[j + 1]) {
            j++;
        }
        
        // must reach end
        return j == n - 1;
    }
}