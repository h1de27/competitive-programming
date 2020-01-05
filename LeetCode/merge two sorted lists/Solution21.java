import java.util.List;
import java.util.PriorityQueue;

public class Solution21 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ListNode res = new ListNode(0);
        ListNode head = res;

        while (l1 != null) {
            pq.offer(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            pq.offer(l2.val);
            l2 = l2.next;
        }

        while (!pq.isEmpty()) {
            res.next = new ListNode(pq.poll());
            res = res.next;
        }

        return head.next;
    }
}
