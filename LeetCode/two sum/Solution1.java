import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class Solution1 {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static class Solution1Test {

        @Test
        public void testMain() {
            int[] arr = {2, 7, 11, 15};
            int target = 9;
            int[] ans = {0, 1};
            assertArrayEquals(ans, twoSum(arr, target));
        }

        @Test (expected = IllegalArgumentException.class)
        public void testException() {
            int[] arr = {2, 7, 11, 15};
            int target = 1;
            twoSum(arr, target);
        }
    }
}
