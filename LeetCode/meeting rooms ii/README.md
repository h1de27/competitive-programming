# [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)

## Question 

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

```
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
```

Example 2:

```
Input: [[7,10],[2,4]]
Output: 1
```

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

## Solution 

## Approach 1 Priority Queue 

We can keep all the rooms in a min heap where the key for the min heap would be the ending time of meeting.

So, every time we want to check if any room is free or not, simply check the topmost element of the min heap as that would be the room that would get free the earliest out of all the other rooms currently occupied.

If the room we extracted from the top of the min heap isn't free, then no other room is. So, we can save time here and simply allocate a new room.

```java
public static int minMeetingRooms(int[][] intervals) {
    if (intervals.length == 0 || intervals == null) {
        return 0;
    }
    
    // 1. Sort the given meetings by their start time.
    Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));
    
    // 2. Init min heap
    PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
    
    // 3. For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free or not.
    //    If the room is free, then we extract the topmost element and add it back with the ending time of the current meeting we are processing.
    //    If not, then we allocate a new room and add it to the heap.
    pq.offer(intervals[0]);
    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] >= pq.peek()[1]) {
            pq.poll();
        }
        pq.offer(intervals[i]);
    }
    
    // 4. After processing all the meetings, the size of the heap will tell us the number of rooms allocated. This will be the minimum number of rooms needed to accommodate all the meetings.
    return pq.size();
}
```

### Time complexity

O(N log(N)). Array.sort takes O(N log(N)) to sort the given array. Extract-min operation on a heap takes O(log(N)).

### Space complexity

O(N) since we construct the min-heap and that can contain N elements in the worst case. 

## Approach 2: Chronological Ordering

 Omitted 