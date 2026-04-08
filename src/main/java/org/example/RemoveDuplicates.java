package org.example;

public class RemoveDuplicates {

    public static void main(String[] args) {
        
        int[] arr = {1, 1, 2, 3, 4, 4, 5,5,6,7,7,8,9,9};
        int length = removeDuplicates(arr);
        for(int i =0;i<length-1;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println(length);
    }

    private static int removeDuplicates(int[] arr) {

        if (arr.length == 0) return 0;

        int slow = 0; // position of last unique element
        for (int fast = 1; fast < arr.length; fast++) {
            if (arr[fast] != arr[slow]) { // condition to "keep"
                slow++;
                arr[slow] = arr[fast];
            }
        }
        return slow + 1; // length of unique portion
    }

}
