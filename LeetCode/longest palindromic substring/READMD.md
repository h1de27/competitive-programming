# [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

## Question 

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

```text
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```

Example 2:

```text
Input: "cbbd"
Output: "bb"
```

## What I though & Solution

It seem like DP question.

Let's translate the question to the math:

P(i, j) = 1. true if the substring Si...Sj is a palindrome. 2. false otherwise.

Therefore, P(i, j) = P(i - 1, j + 1) && Si == Sj

The base case are 

P(i, i) = true
P(i, i + 1) = (Si == Si+1)

```java
private int low, maxLen;

public String longestPalindrome(String s) {
    if (s.length() < 2) {
        return s;
    }

    for (int i = 0; i < s.length() - 1; i++) {
        expandPalindrome(s, i, i);
        expandPalindrome(s, i, i + 1);
    }

    return s.substring(low, low + maxLen);

}

private void expandPalindrome(String s, int j, int k) {
    while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
        j--;
        k++;
    }

    if (maxLen < k - j - 1) {
        low = j + 1;
        maxLen = k - j - 1;
    }
}
```

## Time complexity

O(n^2)

## Space complexity 

O(n^2)