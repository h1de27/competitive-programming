# [Compare Strings by Frequency of the Smallest Character](https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character/)

## Question

Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.

Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

Example 1:
 
 ```text
Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
```

Example 2:

```text
Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
```

## Solution 

We can solve this question by using following steps:

1. Count the frequency of the smallest character for each queries and words and store them to the array. 

2. Sort array that stores the frequency of the smallest character for each words. 

3. Use binary search and find the smallest index which satisfy f(queries[i]) < f(W). 

```java
public int[] numSmallerByFrequency(String[] queries, String[] words) {
    int[] q = new int[queries.length], w = new int[words.length];
    for (int i = 0; i < queries.length; i++) {
        q[i] = freqOfSmallest(queries[i]);
    }
    for (int i = 0; i < words.length; i++) {
        w[i] = freqOfSmallest(words[i]);
    }
    Arrays.sort(w);
    int[] ans = new int [q.length];

    // Binary Search
    for (int i = 0; i < q.length; i++) {
        int l = 0, r = w.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (w[mid] <= q[i]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        ans[i] = w.length - l;
    }
    return ans;
}

private int freqOfSmallest(String str) {
    int[] arr = new int[26];
    for (char ch : str.toCharArray()) {
        arr[ch - 'a']++;
    }
    for (int i = 0; i < 26; i++) {
        if (arr[i] != 0) {
            return arr[i];
        }
    }
    return 0;
}
```

### Time complexity

O(N).

### Space complexity

O(q+w). q is the length of queries and q is the length of words.

This question was not fun... lol