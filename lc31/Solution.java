public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length > 1) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1])
                --i;
            if (i < 0) {
                reverseSubarray(nums, 0);
            } else {
                int j = nums.length - 1;
                while (nums[i] >= nums[j]) 
                    --j;
                swap(nums, i, j);
                reverseSubarray(nums, i + 1);
            }
        }
    }
    
    private void reverseSubarray(int[] arr, int start) {
        int end = arr.length - 1;
        while (start < end) {
            swap (arr, start, end);
            ++start;
            --end;
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int buff = arr[i];
        arr[i] = arr[j];
        arr[j] = buff;
    }
}
