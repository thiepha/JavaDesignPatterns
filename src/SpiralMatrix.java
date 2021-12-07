/*
54. Spiral Matrix
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:
Input:
[
[ 1, 2, 3 ],
[ 4, 5, 6 ],
[ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input:
[
[1, 2, 3, 4],
[5, 6, 7, 8],
[9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();
        if (matrix.length == 0) return ret;
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        while (l <= r && t <= b) {
            int i = t, j = l;
            for (j = l; j <= r; j++) {
                ret.add(matrix[i][j]);
            }
            j--;
            for (i = t + 1; i <= b; i++) {
                ret.add(matrix[i][j]);
            }
            i--;
            for (j = r - 1; j >= l && b - t >= 1; j--) {
                ret.add(matrix[i][j]);
            }
            j++;
            for (i = b - 1; i > t && r - l >= 1; i--) {
                ret.add(matrix[i][j]);
            }
            l++;
            r--;
            t++;
            b--;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(spiralOrder(m1));
        int[][] m2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(m2));
        int[][] m3 = {{1, 2}, {3, 4}};
        System.out.println(spiralOrder(m3));
    }
}
