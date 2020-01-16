public class Solution443 {
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
}
