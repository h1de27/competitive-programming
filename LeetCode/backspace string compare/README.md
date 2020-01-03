# [Backspace String Compare](https://leetcode.com/problems/backspace-string-compare/)

Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

```text
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
```

Example 2:

```text
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
```

Example 3:

```text
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
```

Example 4:

```text
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
```


Note:

1. 1 <= S.length <= 200
2. 1 <= T.length <= 200
3. S and T only contain lowercase letters and '#' characters.

## Solution 

My first idea is to use stack and build the final state of the string for both S and T and then, compare them.

```java
public static boolean backspaceCompare(String S, String T) {
    Stack<Character> sS = new Stack<>();
    Stack<Character> sT = new Stack<>();

    for (int i = 0; i < S.length(); i++) {
        if (S.charAt(i) == '#' && !sS.isEmpty()) {
            sS.pop();
        } else if (S.charAt(i) != '#') {
            sS.push(S.charAt(i));
        }
    }

    for (int i = 0; i < T.length(); i++) {
        if (T.charAt(i) == '#' && !sT.isEmpty()) {
            sT.pop();
        } else if (T.charAt(i) != '#') {
            sT.push(T.charAt(i));
        }
    }

    while (!sS.isEmpty() && !sT.isEmpty()) {
        if (sS.pop() != sT.pop()) {
            return false;
        }
    }
    return sS.isEmpty() && sT.isEmpty();
}
```

It works but it takes O(S.length() + T.length()) for time complexity and O(S.length() + T.length()) for space complexity.

How can we solve it in O(N) time and O(1) space?

The answer is to use two pointer and traverse each strings in reverse way. 

```java
public boolean backspaceCompare(String S, String T) {
    int i = S.length() - 1, j = T.length() - 1;
    int skipS = 0, skipT = 0;

    while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
        while (i >= 0) { // Find position of next possible char in build(S)
            if (S.charAt(i) == '#') {skipS++; i--;}
            else if (skipS > 0) {skipS--; i--;}
            else break;
        }
        while (j >= 0) { // Find position of next possible char in build(T)
            if (T.charAt(j) == '#') {skipT++; j--;}
            else if (skipT > 0) {skipT--; j--;}
            else break;
        }
        // If two actual characters are different
        if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
            return false;
        // If expecting to compare char vs nothing
        if ((i >= 0) != (j >= 0))
            return false;
        i--; j--;
    }
    return true;
}
```

### Time complexity 

O(S.length() + T.length())

### Space complexity

O(1).
