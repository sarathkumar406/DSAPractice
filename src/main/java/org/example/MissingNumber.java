package org.example;

public class MissingNumber {
    public static void main(String[] args) {

        int[] arr = {3,0,1};
        System.out.println(misssingNumber(arr));
    }

    private static int misssingNumber(int[] arr) {
        int xor = arr.length;
        for(int i = 0;i<arr.length;i++){
            xor ^= i ^ arr[i];
        }
        return xor;
    }
}
