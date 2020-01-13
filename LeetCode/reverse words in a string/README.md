# [Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/)

## Question

Given an input string, reverse the string word by word.

Example 1:

```text
Input: "the sky is blue"
Output: "blue is sky the"
```

Example 2:

```text
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
```

Example 3:

```text
Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
```

Note:

1. A word is defined as a sequence of non-space characters.
2. Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
3. You need to reduce multiple spaces between two words to a single space in the reversed string.

## What I though & Solution 

Let's split string by white space, tab, and any blank then, construct a new string in the reverse order.

According to the [stack overflow](https://stackoverflow.com/questions/225337/how-do-i-split-a-string-with-any-whitespace-chars-as-delimiters), the `\\s` is equivalent to `[ \\t\\n\\x0B\\f\\r]`, so I use this to split a string.

```java
public String reverseWords(String s) {
    String[] arr = s.split("\\s+");
    String res = "";
    for (int i = arr.length - 1; i >= 0; i--) {
        res += arr[i] + " ";
    }
    return res.trim();
}
```

## Time complexity

O(N), where N is a number of characters in the input string.

## Space complexity

O(N), to store the result of split by spaces.