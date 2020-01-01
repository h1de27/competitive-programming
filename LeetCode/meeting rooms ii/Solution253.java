import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Solution253 {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0 || intervals == null) {
            return 0;
        }

        Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));

        pq.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pq.peek()[1]) {
                pq.poll();
            }
            pq.offer(intervals[i]);
        }

        return pq.size();
    }

    public static class Solution253Test {

        @Test
        public void testFalse(){
            int[][] arr = {
                    {0, 30},
                    {5, 10},
                    {15, 20}
            };
            assertEquals(2, minMeetingRooms(arr));
        }

        @Test
        public void testTrue(){
            int[][] arr = {
                    {7, 10},
                    {2, 4}
            };
            assertEquals(1, minMeetingRooms(arr));
        }
    }

}
