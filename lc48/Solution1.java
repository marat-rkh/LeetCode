public class Solution1 {
    public void rotate(int[][] matrix) {
        int halfLen = matrix.length / 2;
        for(int j = 0; j < halfLen;  ++j) {
            for (int i = j; i < (matrix.length - j) - 1; ++i) {
                int[][] tuple = { { i, j }, { matrix[j][i] } };
                int[] curIJ;
                int curVal;
                for (int k = 0; k < 4; ++k) {
                    curIJ = tuple[0];
                    curVal = tuple[1][0];
                    tuple = rotatePixel(matrix, curIJ[0], curIJ[1], curVal);
                }
            }
        }
    }
    
    private int[][] rotatePixel(int[][] matrix, int i, int j, int ijVal) {
        int[] x1y1 = toCentralizedCoords(i, j, matrix.length);
        int[] x2y2 = rotate90(x1y1[0], x1y1[1]);
        int[] i2j2 = toArrayCoords(x2y2[0], x2y2[1], matrix.length);
        int i2j2OldVal = matrix[i2j2[1]][i2j2[0]];
        matrix[i2j2[1]][i2j2[0]] = ijVal;
        int[][] res = { i2j2, { i2j2OldVal } };
        return res;
    }
    
    private int[] toCentralizedCoords(int i, int j, int matLen) {
        int halfLen = matLen / 2;
        if (matLen % 2 == 1) {
            return pair(i - halfLen, j - halfLen);
        } else {
            int x = i < halfLen ? i - halfLen : i - (halfLen - 1);
            int y = j < halfLen ? j - halfLen : j - (halfLen - 1);
            return pair(x, y);
        }
    }
    
    private int[] rotate90(int x, int y) {
        return pair(-y, x);
    }
    
    private int[] toArrayCoords(int x, int y, int matLen) {
        int halfLen = matLen / 2;
        if (matLen % 2 == 1) {
            return pair(x + halfLen, y + halfLen);
        } else {
            int i = x < 0 ? x + halfLen : x + (halfLen - 1);
            int j = y < 0 ? y + halfLen : y + (halfLen - 1);
            return pair(i, j);
        }
    }
    
    private int[] pair(int a, int b) {
        int[] pair = { a, b };
        return pair;
    }
}