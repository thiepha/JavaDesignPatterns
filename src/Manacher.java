public class Manacher {
    public static int longestPal(String s) {
        if (s == null || s.length() == 0) return 0;
        s = makeInput(s);
        int c = 0, r = 0, longest = 0;
        int[] p = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int mr = 2 * c - i;
            if (i < r) {
                p[i] = Math.min(r - i, p[mr]);
            }
            int a = i + p[i] + 1;
            int b = i - p[i] - 1;
            while (b >= 0 && a < s.length() && s.charAt(a) == s.charAt(b)) {
                p[i]++;
                a++;
                b--;
            }

            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
            if (p[i] > longest) {
                longest = p[i];
            }
        }
        return longest;
    }

    private static String makeInput(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append("#");
            sb.append(c);
        }
        sb.append("#");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestPal("aba"));
        System.out.println(longestPal("cabbac"));
    }
}
