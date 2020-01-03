public class Solution299 {
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
}
