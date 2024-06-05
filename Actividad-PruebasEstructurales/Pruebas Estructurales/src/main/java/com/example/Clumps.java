package com.example;

public class Clumps {
    /**
    * @param nums
    *
    1 on y off points
    * @return …
    */
    public static int countClumps(int[] nums) {
        if (nums == null || nums.length == 0) { // 1
            return 0;
        }
        int count = 0;
        int prev = nums[0];
        boolean inClump = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev && !inClump) { // 2
                inClump = true;
                count += 1;
            }
            if (nums[i] != prev) { // 3
                prev = nums[i];
                inClump = false;
            }
        }
        return count;
    }
}
