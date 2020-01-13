public class Solution151 {
    public String reverseWords(String s) {
        String[] arr = s.split("\\s+");
        String res = "";
        for (int i = arr.length - 1; i >= 0; i--) {
            res += arr[i] + " ";
        }
        return res.trim();
    }
}
