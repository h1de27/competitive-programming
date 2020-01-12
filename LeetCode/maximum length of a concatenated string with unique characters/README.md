## Preface

I have never seen the question using bit-manipulation to manage characters.

This question is from the question list sorted by Microsoft Interview Question.

# [Maximum Length of a Concatenated String with Unique Characters](https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/)

## Question 

Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which has unique characters.

Return the maximum possible length of s.

 
Example 1:

```text
Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
```

Example 2:

```text
Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
```

Example 3:

```text
Input: a rr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
```text

Constraints:

1. 1 <= arr.length <= 16
2. 1 <= arr[i].length <= 26
3. arr[i] contains only lower case English letters.

## Solution 

1. Initial the result res to include the case of an empty string "".
res include all possible combinations we find during we iterate the input.

2. Iterate the input strings, but skip the word that has duplicate characters. The examples are kind of misleading, but the input string can have duplicate characters, no need to considerate these strings.

3. For each string, we check if it's a conflict with the combination that we found. If they have an intersection of characters, we skip it. If not, we append this new combination to the result.

4. return the maximum length from all combinations.

```java
public int maxLength(List<String> arr) {
    // Create the list of integer that stores strings.
    List<Integer> dp = new ArrayList<>();
    dp.add(0);
    int res = 0;
    for (String s: arr) {
        int a = 0, dup = 0;
        for (char c: s.toCharArray()) {
            // Check one string has the duplicate characters.
            dup |= a & (1 << (c - 'a'));
            // Represent the length of the string by using bit-operation.
            a |= 1 << (c - 'a');
        }
        // If there are duplicate characters in a string, then skip that string.
        if (dup > 0) {
            continue;
        }
        // Check two strings has duplicate characters. If so, skip that combination.
        // Otherwise, add two and get max length of the concatenated string.
        for (int i = dp.size() - 1; i >= 0; --i) {
            if ((dp.get(i) & a) > 0) continue;
            dp.add(dp.get(i) | a);
            res = Math.max(res, Integer.bitCount(dp.get(i) | a));
        }
    }
    return res;
}
```