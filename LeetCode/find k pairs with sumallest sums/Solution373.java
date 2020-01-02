import org.junit.Test;

import java.util.*;

public class Solution373 {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }
        PriorityQueue<List<Integer>> pq =
                new PriorityQueue<>((o1, o2) ->
                        nums1[o1.get(0)] + nums2[o2.get(0)] - nums1[o1.get(1)] - nums2[o1.get(1)]);
        TreeSet<List<Integer>> added = new TreeSet<>((o1, o2) -> {
            if (o1.get(0) != o2.get(0)) {
                return o1.get(0) - o2.get(0);
            }
            return o1.get(1) - o2.get(1);
        });

        pq.add(new ArrayList<>(Arrays.asList(0, 0)));
        added.add(new ArrayList<>(Arrays.asList(0, 0)));

        while (res.size() < k && !pq.isEmpty()) {
            List<Integer> ans = pq.poll();
            int i = ans.get(0);
            int j = ans.get(1);
            res.add(Arrays.asList(nums1[i], nums2[j]));
            if (i + 1 < nums1.length && !added.contains(new ArrayList<>(Arrays.asList(i + 1, j)))) {
                pq.add(new ArrayList<>(Arrays.asList(i + 1, j)));
                added.add(new ArrayList<>(Arrays.asList(i + 1, j)));
            }
            if (j + 1 < nums2.length && !added.contains(new ArrayList<>(Arrays.asList(i, j + 1)))) {
                pq.add(new ArrayList<>(Arrays.asList(i, j + 1)));
                added.add(new ArrayList<>(Arrays.asList(i, j + 1)));
            }
        }
        return res;
    }

    public static class Solution373Test {

        @Test
        public void testMain() {

        }
    }
}
