package org.example;

import java.util.Arrays;

public class MoveZeros {

    public static void main(String[] args) {

        int[] num = {0,1,0,3,2};

        moveZerosSolution(num);
    }

    private static void moveZerosSolution(int[] num) {

        int index = 0;

        for(int n : num){
            if(n != 0) {
                num[index++] = n;
            }
        }
        while(index < num.length){
            num[index++] = 0;
        }

        System.out.println(Arrays.toString(num));
    }
}
