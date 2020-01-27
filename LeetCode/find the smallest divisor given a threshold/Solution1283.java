public class Solution1283 {
    // 全探索にすべきか、二分探索にすべきかの判断
    public int smallestDivisor(int[] nums, int threshold) {
        int ng = -1;
        int ok = nums[nums.length - 1] + 1;
        while (Math.abs(ok - ng) > 1) {
            int sum = 0;
            int mid = (ok + ng) / 2;
            for (int i = 0; i < nums.length; i++) {
                sum += Math.ceil((double) nums[i] / mid);
            }
            if (sum > threshold) {
                ng = mid;
            } else {
                ok = mid;
            }
        }
        return ok;
    }
}