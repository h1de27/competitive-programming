# [Bulls and Cows](https://leetcode.com/problems/bulls-and-cows/)

## Question 

You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Ex1:
 
```text
Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
```

Ex2:

```text
Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
```

## Solution 

just solve by using hash table as a counter.

```java
public String getHint(String secret, String guess) {
        int[] counter = new int[10];
        int len = guess.length(), bulls = 0, cows = 0;

        for (int i = 0; i < len; i++) {
            char cs = secret.charAt(i);
            char cg = guess.charAt(i);
            if (cs == cg) {
                bulls++;
            } else {
                if (counter[cg - '0'] > 0) {
                    cows++;
                }
                counter[cg - '0']--;

                if (counter[cs - '0'] < 0) {
                    cows++;
                }
                counter[cs - '0']++;
            }
        }

        return bulls + "A" + cows + "B";
    }
```

### Time complexity

O(N).

### Space complexity

O(N).
