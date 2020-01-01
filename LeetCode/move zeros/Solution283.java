import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class Solution283 {
    public static void moveZeros(int[] nums) {
        int n = nums.length;

        // Count the zeros
        int numZeros = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                numZeros++;
            }
        }

        // Make all the non-zero elements retain their original order.
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                ans.add(nums[i]);
            }
        }

        while (numZeros > 0) {
            ans.add(0);
            numZeros--;
        }

        for (int i = 0; i < n; i++) {
            nums[i] = ans.get(i);
        }
    }

    public static void moveZeros2(int[] nums) {
        int lastNonZeroFoundAt = 0;

        // If the current element is non-zero, then we need to append it
        // just in front of last non-zero element we found.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt++] = nums[i];
            }
        }

        // After we have finished processing new elements,
        // all the non-zero elements are already at beginning of array.
        // we just need to fill remaining array with 0's.
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void moveZeros3(int[] nums) {
        for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                int temp = nums[cur];
                nums[cur] = nums[lastNonZeroFoundAt];
                nums[lastNonZeroFoundAt++] = temp;
            }
        }
    }

    public static class Solution283Test {

        @Test
        public void testMain() {
            int[] arr = {0,1,0,3,12};
            int[] ans = {1,3,12,0,0};
            moveZeros(arr);
            assertArrayEquals(ans, arr);
        }

        @Test
        public void testMain2() {
            int[] arr = {0,1,0,3,12};
            int[] ans = {1,3,12,0,0};
            moveZeros2(arr);
            assertArrayEquals(ans, arr);
        }

        @Test
        public void testMain3() {
            int[] arr = {0,1,0,3,12};
            int[] ans = {1,3,12,0,0};
            moveZeros3(arr);
            assertArrayEquals(ans, arr);
        }
    }
}
