# [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)

## Question 

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

```text
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```

Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

## Solution 

The first idea I came up with is to use greedy algorithm. The key point is to pick the locally optimal move at each step, and that will lead to the globally optimal solution.

```java
class Solution {
  public int maxSubArray(int[] nums) {
    int n = nums.length;
    int currSum = nums[0], maxSum = nums[0];

    for(int i = 1; i < n; ++i) {
      currSum = Math.max(nums[i], currSum + nums[i]);
      maxSum = Math.max(maxSum, currSum);
    }
    return maxSum;
  }
}
```

### TIme Complexity 

O(N) since it's one pass along the array.

### Space Complexity

O(1).