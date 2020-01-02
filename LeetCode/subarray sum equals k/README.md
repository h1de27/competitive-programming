# [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

## Question 

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
```
Input:nums = [1,1,1], k = 2
Output: 2
```

Note:

1. The length of the array is in range `[1, 20,000]`.
2. The range of numbers in the array is `[-1000, 1000]` and the range of the integer k is `[-1e7, 1e7]`.

## Solution 

## Approach 1

The easiest way to solve this question is to consider every possible subarray by using two pointers pointing start position and end position.
Then, for every possible position of pointers, we will calculate the sum and check if it matches the given k.

However, its time complexity will be O(n^3) since we need to use three loops and it won't satisfy the requirement because "note" mentions that the length of the array is in range `[1, 20,000]`.

Okay, it means we need to optimize some part of this approach.

I think the key concept of the question is "subarray". A subarray is a contiguous part of the array, so if we fix the start point, we can find all sums that relate to all end positions while considering different end point.
i.e. We can choose a particular start point and while iterating over the end points, we can add the element corresponding to the end point to the sum formed till now. 

Thus, we can optimize the approach by using "previous calculation result" and the code will be...

```java
public int subarraySum(int[] nums, int k) {
    int count = 0;
    for (int s = 0; s < nums.length; s++) {
        int sum = 0;
        for (int e = s; e < nums.length; e++) {
            sum += nums[e];
            if (sum == k) {
                count++;
            }
        }
    }
    return count;
}
```

### Time complexity 

O(n^2) obviously.

### Space complexity

O(1). Constant space is used.

## Approach 2

"Hey man, it's super easy!". Yeah, I know... the approach 1 was not that hard to come up, but what if we need to achieve O(n) for time complexity?
I first came up with "cumulative sum" and calculate sum[j+1] − sum[i] (sum[i] is used to store the cumulative sum of nums array up to the element corresponding to the (i - 1)th index.), but its run time will be O(n^2).

Umm... how to reduce its run time to O(n)? The answer is to use a "Hash map".

We can make use of a hash map which is used to store the cumulative sum up to all the indices possible along with the number of times the same sum occurs since nums sometimes contain negative numbers.
This is actually a super elegant way to solve this question.

Also, one tricky thing is that we need to put (0,1) into the hash map to cover the case when the sum of nums[0, j] == k.

```java
public static int subarraySum(int[] nums, int k) {
    int count = 0, sum = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0,1);
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (map.containsKey(sum - k)) {
            count += map.get(sum - k);
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
}
```

This question made me rethink I need to practice "cumulative sum"...





 



