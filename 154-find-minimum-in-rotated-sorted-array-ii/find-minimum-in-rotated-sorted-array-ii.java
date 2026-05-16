class Solution {

    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            // minimum in right half
            if (nums[mid] > nums[right]) {

                left = mid + 1;
            }

            // minimum in left half or at mid
            else if (nums[mid] < nums[right]) {

                right = mid;
            }

            // duplicates: cannot decide
            else {

                right--;
            }
        }

        return nums[left];
    }
}