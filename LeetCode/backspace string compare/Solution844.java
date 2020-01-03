import java.util.Stack;

public class Solution844 {
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
}
