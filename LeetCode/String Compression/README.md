# [String Compression](https://leetcode.com/problems/string-compression/)

## Question 

Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.

Follow up:
Could you solve it using only O(1) extra space?

Example 1:

```text
Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
```

Example 2:
```text
Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
```

Example 3:
```text
Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
```

Note:

1. All characters have an ASCII value in [35, 126].
2. 1 <= len(chars) <= 1000.

## What I though & Solution 

For this question, what I came up with is to use the two pointers.
Let's say there are two pointers called, "search" and "update".
The "search" pointer will searches how many same characters show up on the array and "update" pointer indicates the point we updated the array.

Since the character that shows up once will not be changed and "update" pointer will not overcome "search" pointer, we can manage this by O(1) extra space.

```java
public int compress(char[] chars) {
    int search = 0, update = 0;
    while (search < chars.length) {
        char curr = chars[search];
        int count = 0;
        while (curr < chars.length && chars[search] == curr) {
            search++;
            count++;
        }
        chars[update++] = curr;
        if (count != 1) {
            for (char c : Integer.toString(count).toCharArray()) {
                chars[update++] = c;
            }
        }
    }
    return update;
}
```

## Time complexity

O(N)

## Space complexity

O(1)