package org.example;

import java.util.Arrays;

public class MoveZerosToEnd {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};

        int[] res = moveZeros(nums);
        System.out.println(Arrays.toString(res));
    }

    private static int[] moveZeros(int[] nums) {
        int n = nums.length;
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != 0){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        while(slow < n){
            nums[slow++] = 0;
        }
        return nums;
    }
}
