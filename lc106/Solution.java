/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length == 0) {
            return null;
        }
        Roots roots = new Roots(postorder);
        return buildSubtree(inorder, 0, inorder.length - 1, roots);
    }
    
    private TreeNode buildSubtree(int[] inorder, int beg, int end, Roots roots) {
        if (beg > end) {
            return null;
        }
        int root = roots.nextRoot();
        int inRootPos = findElem(inorder, beg, end, root);
        TreeNode rootNode = new TreeNode(root);
        if (beg != end) {
            rootNode.right = buildSubtree(inorder, inRootPos + 1, end, roots);
            rootNode.left = buildSubtree(inorder, beg, inRootPos - 1, roots);
        }
        return rootNode;
    }
    
    private int findElem(int[] arr, int beg, int end, int elem) {
        for(int i = beg; i <= end; ++i) {
            if(arr[i] == elem) {
                return i;
            }
        }
        throw new IllegalStateException("array must contain searched element");
    }
    
    private static class Roots {
        int[] postorder;
        int nextRootPos;
        
        public Roots(int[] postorder) {
            this.postorder = postorder;
            this.nextRootPos = postorder.length - 1;
        }
        
        public int nextRoot() {
            int nextRoot = postorder[nextRootPos];
            nextRootPos -= 1;
            return nextRoot;
        }
    }
}