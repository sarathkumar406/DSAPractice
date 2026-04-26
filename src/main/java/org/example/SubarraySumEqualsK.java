package org.example;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    public static void main(String[] args) {

        int[] num = {1,2,3};
        int k = 3;

       int res =  subarraySumBruteForce(num, k);
        System.out.println("Brute force Solution : "+res);
        int res1 = subarraySumOptimize(num, k);
        System.out.println("Optimize Solution using prefix sum : "+res1);
    }

    private static int subarraySumOptimize(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        int count =0;
        int prefixSum = 0;
        for(int num : nums) {
            prefixSum += num;
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }
        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    private static int subarraySumBruteForce(int[] num, int k) {

        int n = num.length;
        int res  = 0;
        for(int start   =0;start < n;start++){
            int sum = 0;
            for(int j = start; j< n;j++){
                sum = sum + num[j];
                if(sum == k){
                    res++;
                }

            }
        }

        return res;

    }
}
