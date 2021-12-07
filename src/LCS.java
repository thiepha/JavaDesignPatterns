// Longest Common Subsequence

/**
 * A subsequence of a string is set of all the characters which are left to right order
 * and not necessarily contiguous. For example, string ABCDEG has ABC, ADG, EG, BCDEG subsequences;
 * whereas BDA is not a subsequence of the given string, even though all the characters
 * are present in the string, they do not appear in the same order.
 *
 * Given two strings X and Y, find longest common subsequence (LCS) Z.
 * For example, X = ABCDSEFGD Y = ACFEFXVGAB; LCS Z would be ACEFG.
 */
public class LCS {
    public static int lcsLengthRecursive(String s1, String s2, int pos1, int pos2) {
        // String s: position: 0 -> len - 1
        if (pos1 < 0 || pos2 < 0) {
            return 0;
        }
        if (s1.charAt(pos1) == s2.charAt(pos2)) {
            return 1 + lcsLengthRecursive(s1, s2, pos1 - 1, pos2 - 1);
        } else {
            return Math.max(lcsLengthRecursive(s1, s2, pos1 - 1, pos2), lcsLengthRecursive(s1, s2, pos1, pos2 - 1));
        }
    }

    public static int lcsLengthRCS(String s1, String s2) {
        if (s1 == null || s2 == null) return 0;
        return lcsLengthRecursive(s1, s2, s1.length() - 1, s2.length() - 1);
    }

    // use dynamic programming
    public static int lcsLengthDP(String s1, String s2) {
        if (s1 == null || s2 == null) return 0;
        int[][] lenArr = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) {
                    lenArr[i][j] = 0;
                } else {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        lenArr[i][j] = 1 + lenArr[i - 1][j - 1];
                    } else {
                        lenArr[i][j] = Math.max(lenArr[i - 1][j], lenArr[i][j - 1]);
                    }
                }
            }
        }

        return lenArr[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        System.out.println(LCS.lcsLengthDP("abcdsefgd", "acfefxvgab"));
        System.out.println(LCS.lcsLengthRCS("abcdsefgd", "acfefxvgab"));
    }
}
