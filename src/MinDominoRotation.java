/*
1007. Minimum Domino Rotations For Equal Row
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
(A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
We may rotate the i-th domino, so that A[i] and B[i] swap values.
Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
If it cannot be done, return -1.

Example 1:

Input: A = [2,1,2,4,2,2],
       B = [5,2,6,2,3,2]
Output: 2
Explanation:
The first figure represents the dominoes as given by A and B: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.

Example 2:

Input: A = [3,5,1,2,3],
       B = [3,6,3,3,4]
Output: -1
Explanation:
In this case, it is not possible to rotate the dominoes to make one row of values equal.


Note:

1 <= A[i], B[i] <= 6
2 <= A.length == B.length <= 20000
*/

public class MinDominoRotation {
    public static int minDominoRotations(int[] A, int[] B) {
        if (A.length != B.length) return -1;
        int rot = 0;
        int[] countA = new int[6];
        int[] countB = new int[6];
        int maxA = 0, maxADot = 0;
        int maxB = 0, maxBDot = 0;
        for (int i = 0; i < A.length; i++) {
            int dotA = A[i] - 1, dotB = B[i] - 1;
            countA[dotA]++;
            countB[dotB]++;
            if (countA[dotA] > maxA) {
                maxA = countA[dotA];
                maxADot = dotA + 1;
            }
            if (countB[dotB] > maxB) {
                maxB = countB[dotB];
                maxBDot = dotB + 1;
            }
        }
        if (maxA > maxB) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] != maxADot) {
                    if (B[i] == maxADot) {
                        rot++;
                    } else {
                        return -1;
                    }
                }
            }
        } else {
            for (int i = 0; i < B.length; i++) {
                if (B[i] != maxBDot) {
                    if (A[i] == maxBDot) {
                        rot++;
                    } else {
                        return -1;
                    }
                }
            }
        }

        return rot;
    }

    public static void main(String[] args) {
        int[] A = {2, 1, 2, 4, 2, 2};
        int[] B = {5, 2, 6, 2, 3, 2};
        System.out.println(minDominoRotations(A, B));
        int[] C = {3, 5, 1, 2, 3};
        int[] D = {3, 6, 3, 3, 4};
        System.out.println(minDominoRotations(C, D));
    }
}
