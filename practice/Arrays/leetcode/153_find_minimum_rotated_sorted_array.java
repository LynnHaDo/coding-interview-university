class Solution153 {
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }

        int min = nums[0];
        int high = nums.length - 1;
        int low = 0;

        // Binary search
        while (low <= high) {
            int mid = low + (high - low)/2;
            
            if (nums[mid] == nums[low] && nums[mid] == nums[high]) {
                low++;
                high--;
            } // Right is sorted 
            else if (nums[mid] > nums[high]) {
                low = mid + 1;
            } // Left is sorted 
            else {
                high = mid - 1;
            }

            min = Math.min(min, nums[mid]);
        }

        return min;
    }
}