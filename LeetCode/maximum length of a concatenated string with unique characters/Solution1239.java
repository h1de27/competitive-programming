import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Solution1239 {
    public static int maxLength(List<String> arr) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        int res = 0;
        for (String s: arr) {
            int a = 0, dup = 0;
            for (char c: s.toCharArray()) {
                // Check one string has the duplicate characters.
                dup |= a & (1 << (c - 'a'));
                //
                a |= 1 << (c - 'a');
            }
            if (dup > 0) {
                continue;
            }
            for (int i = dp.size() - 1; i >= 0; --i) {
                if ((dp.get(i) & a) > 0) continue;
                dp.add(dp.get(i) | a);
                res = Math.max(res, Integer.bitCount(dp.get(i) | a));
                System.out.println("res: " + res);
            }
        }
        return res;
    }

    public static class solution1239Test {
        @Test
        public void mainTest() {
            List<String> arr = new ArrayList<>(Arrays.asList("cha","r","act","ers"));
            assertEquals(6, maxLength(arr));
        }
    }
}
