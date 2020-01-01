import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class Solution252 {
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }

        return true;
    }

    public static class Solution252Test {

        @Test
        public void testFalse(){
            int[][] arr = {
                    {0, 30},
                    {5, 10},
                    {15, 20}
            };
            assertFalse(canAttendMeetings(arr));
        }

        @Test
        public void testTrue(){
            int[][] arr = {
                    {7, 10},
                    {2, 4}
            };
            assertTrue(canAttendMeetings(arr));
        }
    }

}
