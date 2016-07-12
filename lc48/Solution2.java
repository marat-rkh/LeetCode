public class Solution2 {
    public void rotate(int[][] matrix) {
        for(int i = 0; i < matrix.length - 1;  ++i) {
            for (int j = i + 1; j < matrix.length; ++j) {
                swap(matrix, i, j, j, i);
            }
        }
        for(int i = 0; i < matrix.length; ++i) {
            for(int j = 0; j < matrix.length / 2; ++j) {
                swap(matrix, i, j, i, matrix.length - j - 1);
            }
        }
    }
    
    private void swap(int[][] matrix, int i0, int j0, int i1, int j1) {
        int val0 = matrix[i0][j0];
        matrix[i0][j0] = matrix[i1][j1];
        matrix[i1][j1] = val0;
    }
}