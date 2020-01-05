# [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)

## Question

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

Example: 
```text
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

## Solution 

This is the question we can use "Floyd's Tortoise and Hare".

```java
private ListNode getIntersection(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            return fast;
        }
    }
    return null;
}

public ListNode detectCycle(ListNode head) {
    if (head == null) {
        return null;
    }

    ListNode intersect = getIntersection(head);
    if (intersect == null) {
        return null;
    }

    ListNode ptr1 = head;
    ListNode ptr2 = intersect;
    while (ptr1 != ptr2) {
        ptr1 = ptr1.next;
        ptr2 = ptr2.next;
    }

    return ptr1;
}
```

### Time complexity 

The worst case runtime is O(n) since there are two cases that list has no cycle and list has a cycle.

### Space complexity

O(1).