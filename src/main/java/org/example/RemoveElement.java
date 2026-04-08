package org.example;

public class RemoveElement {
    public static void main(String[] args) {

        int[] arr = {0,1,2,2,3,0,4,2};
        int val = 2;

       int res = removeElement(arr,val);
        System.out.println("length is "+res);
       for(int i= 0;i<res;i++){
           System.out.print(arr[i] +" ");
       }

    }

    private static int removeElement(int[] arr, int val) {

        int slow = 0;
        for(int fast = 0;fast<arr.length;fast++){
            if(arr[fast] != val){
                arr[slow] = arr[fast];
                slow++;
            }
        }
return slow ;

    }
}
