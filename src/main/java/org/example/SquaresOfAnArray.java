package org.example;

import java.util.Arrays;

public class SquaresOfAnArray {

    public static void main(String[] args) {

        int[] arr = {-4,-1,0,3,10};
//        squaresArray(arr);
        sortedSquaresInPlace(arr);
    }

    private static void sortedSquaresInPlace(int[] arr) {
        int n = arr.length;
        int left = 0, right = n - 1;
        int pos = n-1;
        int[] res = new int[n];

        while(left <= right){
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];

            if(leftSquare > rightSquare){
                res[pos] = leftSquare;
                left++;
            } else {
                res[pos] = rightSquare;
                right--;
            }
            pos--;
        }
        System.out.println(Arrays.toString(res));
    }

//    private static void squaresArray(int[] arr) {
//        for(int fast = 0;fast<arr.length;fast++){
//            arr[fast] = arr[fast] * arr[fast];
//        }
//
//        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
//    }
}
