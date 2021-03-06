# [Meeting Rooms](https://leetcode.com/problems/meeting-rooms/)

## Question 

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

```
Input: [[0,30],[5,10],[15,20]]
Output: false
```

Example 2:

```
Input: [[7,10],[2,4]]
Output: true
```

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

## Solution 

The idea is to sort the meeting by starting time. Then, go through the meeting one by one and make sure that each meeting ends before the next one stars.

```java
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
```

### Time Complexity 

O(n log(n)). `Arrays.sort(Object[])` is based on the TimSort algorithm, giving us a time complexity of O(n log(n)).
In short, TimShort makes used of the insertion sort and the merge sort algorithms. However, it is still slower compared to other sorting algorithm like some of the quick sort implementation.

Reference: https://www.baeldung.com/arrays-sortobject-vs-sortint

### Space Complexity

O(1) since no additional space is allocated.