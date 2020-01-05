# [Merge two sorted lists](https://leetcode.com/problems/merge-two-sorted-lists/)

## Question

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

```text
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```

## Solution 

My idea is to use sort values inside both list nodes by using priority queue and re-input them to new list node.

```java
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
```

### Time complexity

O((n+m)log(n+m)). n is the length of l1 and m is the length of l2. Remove n+m times from the priority queue.

### Space complexity

O(n+m).