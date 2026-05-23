import java.util.*;

class Solution {

    public long maximumHappinessSum(int[] happiness, int k) {

        Arrays.sort(happiness);

        long ans = 0;

        int decrease = 0;

        // pick largest k elements
        for (int i = happiness.length - 1;
             i >= 0 && k > 0;
             i--, k--) {

            int value = happiness[i] - decrease;

            if (value > 0) {
                ans += value;
            }

            decrease++;
        }

        return ans;
    }
}