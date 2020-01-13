# [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

## Question 

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

![](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

```text
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
```

## What I though & Solution

According to the question, what we need to do is that for each element in the array, we find the max level of water it can trap the rain, which is equal to the min of max height of bars on both the sides minus its own height.

Of course, we can traverse from both side which will have O(n^2) runtime, but we can optimize to only one traversal.

Let's think about the requirement again.

If the left max bar is lower than the right max bar, then the amount of trapped water depends on the left max bar and vice versa.

So, we can use two pointer and keep track of the left max bar, the right max bar and current positions.

```java
public int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int ans = 0;
    int leftMax = 0;
    int rightMax = 0;
    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= leftMax) {
                leftMax = height[left];
            } else {
                ans += (leftMax - height[left]);
            }
            left++;
        } else {
            if (height[right] >= rightMax) {
                rightMax = height[right];    
            } else {
                ans += (rightMax - height[right]);  
            } 
            right--;
        }
    }
    return ans;
}
```