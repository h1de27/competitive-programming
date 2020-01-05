# [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

## Question 

Given a linked list, remove the n-th node from the end of list and return its head.

Example:

```text
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```

Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?

## Solution 

I solved this question by using two pointers that are slow and fast. It is much easier to see this image from the solution page.

![](https://assets.leetcode.com/static_assets/media/original_images/19_Remove_nth_node_from_end_of_listB.png)

In addition, it is important for this question to set a dummy head in front of the head since a dummy head makes it easy to mange when we need to remove 1st node.

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode slow = dummyHead;
    ListNode fast = dummyHead;
    for (int i = 0; i <= n; i++) {
        fast = fast.next;
    }
    while (fast != null) {
        slow = slow.next;
        fast = fast.next;
    }
    slow.next = slow.next.next;
    return dummyHead.next;
}
```

### Time complexity

O(L). L is the number of nodes in the head.

### Space complexity

O(1) since we manage head itself. 