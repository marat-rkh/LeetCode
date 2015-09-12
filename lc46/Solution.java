public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        return permute(nums, nums.length);
    }
    
    private List<List<Integer>> permute(int[] nums, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n == 0) {
            return res;
        } else if (n == 1) {
            List<Integer> single = new ArrayList<Integer>();
            single.add(nums[0]);
            res.add(single);
            return res;
        } else {
            for (int i = 0; i < n; ++i) {
                swap(nums, i, n - 1);
                List<List<Integer>> perms = permute(nums, n - 1);
                appendLast(perms, nums[n - 1]);
                swap(nums, i, n - 1);
                res.addAll(perms);
            }
            return res;
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int buf = arr[i];
        arr[i] = arr[j];
        arr[j] = buf;
    }
    
    private void appendLast(List<List<Integer>> ls, int elem) {
        for (List<Integer> l : ls) {
            l.add(l.size(), elem);
        }
    }
}
