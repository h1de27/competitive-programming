# [House Robber](https://leetcode.com/problems/house-robber/)

## Question 

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

```text
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
```

Example 2:

```text
Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
```

## Solution 

This is obviously dp question, so what we have to do is to get max value while iterating through each houses.

Also, if we translate this question to mathematical equations, it is easy to understand. 

Let's say:

f(k) = Largest amount that we can rob from the first k houses.
Ai = Amount of money at the i-th house.

If n = 1, f(1) = A1.
If n = 2, f(2) = max(A1, A2).
If n = 3, we have two options. 

1. Rob the third house, and add its amount to the first house's amount.

2. Do not rob the third house, and stick with the max amount of the first two houses.

So we can say:

f(k) = max(f(k - 2) + Ak, f(k - 1))

```java
public int rob(int[] nums) {
    int prevMax = 0;
    int currMax = 0;
    for (int x: nums) {
        int temp = currMax;
        currMax = Math.max(prevMax + x, currMax);
        prevMax = temp;
    }
    return currMax;
}
```

This questions tell us how important translating question to mathematical formula is!