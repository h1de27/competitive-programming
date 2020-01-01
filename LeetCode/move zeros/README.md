# Solution 

The 2 requirement of the question are:

1. Move all the 0's to the end of the array.

2. All the non-zero elements must retain their original order. 

## Approach 1: Space Sub-optional 

There are 4 steps to get the answer.

1. Count the number of zeros.

2. Create new array and put all the non-zero numbers retaining their original order.

3. Add 0's n times (n is the number of zeros showing up on the original array.).

4. Switch each elements of the original array with each elements of the new array.

```java
public static void moveZeros(int[] nums) {
    int n = nums.length;

    // Count the zeros
    int numZeros = 0;
    for (int i = 0; i < n; i++) {
        if (nums[i] == 0) {
            numZeros++;
        }
    }

    // Make all the non-zero elements retain their original order.
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        if (nums[i] != 0) {
            ans.add(nums[i]);
        }
    }

    while (numZeros > 0) {
        ans.add(0);
        numZeros--;
    }

    for (int i = 0; i < n; i++) {
        nums[i] = ans.get(i);
    }
}
```

### Time Complexity

*O(n)* because we need to traverse all elements in the array to conduct each operations above.

### Space Complexity

*O(n)* because we need to create new array which has the same size of the original array.

## Approach 2: Space Optimal, Operation Sub-Optimal

Approach 1 is not a clever way, so we will think about the other approach which uses two pointer.

The two pointers are:

1. Keep track the last index that the non-zero elements shows up.

2. i that traverses all elements in the given array.

```java
public static void moveZeros(int[] nums) {
    int lastNonZeroFoundAt = 0;

    // If the current element is non-zero, then we need to append it
    // just in front of last non-zero element we found.
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
            nums[lastNonZeroFoundAt++] = nums[i];
        }
    }

    // After we have finished processing new elements,
    // all the non-zero elements are already at beginning of array.
    // we just need to fill remaining array with 0's.
    for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
        nums[i] = 0;
    }
}
```

### Time complexity

*O(n)* because we need to traverse all elements in the array.

### Space complexity

*O(1)*. Only constant space is used.

## Approach 3: Optimal

Approach 2 is sub-optimal. For example, the array which has all (except last) leading zeroes: `[0, 0, 0, ..., 0, 1]`.
It means that we need to satisfy two invariants to make optimized solution.

1. All elements before the slow pointer (lastNonZeroFoundAt) are non-zeroes.

2. All elements between the current and slow pointer are zeroes.

Therefore, when we encounter a non-zero element, we need to swap elements pointed by current and slow pointer, then advance both pointers.
If it's zero element, we just advance current pointer.

```java
public static void moveZeros(int[] nums) {
    for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.length; cur++) {
        if (nums[cur] != 0) {
            int temp = nums[cur];
            nums[cur] = nums[lastNonZeroFoundAt]; 
            nums[lastNonZeroFoundAt++] = temp;    
        }       
    }
}
```

### Time complexity

*O(n)*. However, the total number of operations are optimal. The total operations (array writes) that code does is Number of non-0 elements.
This gives us a much better best-case (when most of the elements are 0) complexity than last solution. However, the worst-case (when all elements are non-0) complexity for both the algorithms is same.

### Space complexity

*O(1)*. Only constant space is used.