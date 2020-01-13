# [Valid Tic-Tac-Toe State](https://leetcode.com/problems/valid-tic-tac-toe-state/)

## Question 

A Tic-Tac-Toe board is given as a string array board. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

1. Players take turns placing characters into empty squares (" ").
2. The first player always places "X" characters, while the second player always places "O" characters.
3. "X" and "O" characters are always placed into empty squares, never filled ones.
4. The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
5. The game also ends if all squares are non-empty.
6. No more moves can be played if the game is over.

## What I thought

So, the key point of this question is to break down the requirement to some small condition.
For this question, there are few conditions:

1. Either "XXX" or "OOO" will show up only once.

2. X shows up n times and O shows up m times (n >= 0 and m >= 0 and m + 1 >= n >= m)

Once we translate them to the code, it will be:

```java
public boolean validTicTacToe(String[] board) {
    int flagRow = 0;
    Map<Character, Integer> map = new HashMap<>();
    map.put('X', 0);
    map.put('O', 0);
    for (String s : board) {
        if (s.equals("XXX") || s.equals("OOO")) {
            flagRow++;
        }
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } 
        }
    }
    
    if (flagRow > 1) {
        return false;
    }
    
    if (map.get('X') < map.get('O') || map.get('X') > map.get('O') + 1) {
        return false;
    }
    
    return true;
}
```

Yes, of course, it is not valid code because we ignore tha case when "XXX" and "OOO" in the colum.
In addition, it doesn't cover the case when "O" shows up after "XXX" have already shown up.

Thus, we need to think different approach.

## Solution: Ad-Hoc

Okay, let's rethink the conditions for a tic-tac-toe board to be valid.

1. Since players take turns, the number of 'X's must be equal to or one greater than the number of 'O's.

2. A player wil win in the condition when:
    - If the first player wins, the number of 'X's is one more than the number of 'O's
    - If the second player wins, the number of 'X's is equal to the number of 'O's.
    
3. The board can't simultaneously have three 'X's and three 'O's in a row: once one player has won (on their move), there are no subsequent moves. 

The key point is to keep track of win case by using private method.

```java
public boolean validTicTacToe(String[] board) {
    int xCount = 0, oCount = 0;
    for (String row: board) {
        for (char c : row.toCharArray()) {
            if (c == 'X') xCount++;
            if (c == 'O') oCount++;
        }
    }
    
    if (oCount != xCount && oCount != xCount - 1) return false;
    if (win(board, 'X') && oCount != xCount - 1) return false;
    if (win(board, 'O') && oCount != xCount) return false;
    return true;
}

public boolean win(String[] B, char P) {
    // B: board, P: player
    for (int i = 0; i < 3; i++) {
        if (P == B[0].charAt(i) && P == B[1].charAt(i) && P == B[2].charAt(i))
            return true;
        if (P == B[i].charAt(0) && P == B[i].charAt(1) && P == B[i].charAt(2))
            return true;
    }
    if (P == B[0].charAt(0) && P == B[1].charAt(1) && P == B[2].charAt(2))
        return true;
    if (P == B[0].charAt(2) && P == B[1].charAt(1) && P == B[2].charAt(0))
        return true;
    return false;
}
```

