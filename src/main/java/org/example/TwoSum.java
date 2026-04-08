package org.example;

import java.util.Arrays;

public class TwoSum {

    public static void main(String[] args) {

        int[] arr = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(arr, target);
        if (result != null) {
            System.out.println("Indices: " + result[0] + ", " + result[1]);
        } else {
            System.out.println("No two sum solution found.");
        }

    }

    private static int[] twoSum(int[] arr, int target) {
        Arrays.sort(arr); // Sort the array first
        int left = 0;
        int right = arr.length - 1;

        while(left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[]{left, right}; // Return the indices of the two numbers
            } else if (sum < target) {
                left++; // Move left pointer to the right
            } else {
                right--; // Move right pointer to the left
            }
        }
        return new int[]{-1,-1}; // Return null if no solution is found
    }
}
