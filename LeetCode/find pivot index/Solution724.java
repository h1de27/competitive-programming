public class Solution724 {
    public int pivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        int left = nums[0];
        int right = sum;
        int idx = 0;
        while (left != right) {
            idx++;
            if (idx == nums.length) {
                return -1;
            }
            left += nums[idx];
            right -= nums[idx - 1];
        }

        return idx;
    }
}
