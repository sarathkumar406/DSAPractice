package org.example;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {

        int[] nums = {100,4,200,1,3,2};

        int res = longestConsecutiveSeq(nums);
        System.out.println("Longest Consicutive Sequence :" + res);
    }

    private static int longestConsecutiveSeq(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums){
            numSet.add(num);
        }

        int longest = 0;

        for(int num : numSet){
            if(!numSet.contains(num - 1)){
                int currentNum = 1;
                int currentStreak  =1;

            while(numSet.contains(currentNum + 1)){
                currentNum++;
                currentStreak++;
            }
                longest = Math.max(longest, currentStreak);
            }
        }
        return longest;
    }
}
