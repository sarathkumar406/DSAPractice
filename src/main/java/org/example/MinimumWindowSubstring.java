package org.example;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public static void main(String[] args) {

        String s = "ADOBECODEBANC", t = "ABC";

        String res  = minimumWindowSubString(s, t);
        System.out.println(res);
    }

    private static String minimumWindowSubString(String s, String t) {

        if(s == null || t == null || s.length() < t.length() ) return "";

        //frequency map for string t

        Map<Character, Integer> need = new HashMap<>();
        for(char c : t.toCharArray()){
            need.put(c, need.getOrDefault(c,0)+1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, minLength = Integer.MAX_VALUE;

        while(right < s.length()){
            char c = s.charAt(right);
            right++;

            //update window counts
            if(need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0)+1);
                if(window.get(c).intValue() == need.get(c).intValue()){
                    valid++;
                }
            }

            //shrink the window when its match the all chars
            while(valid == need.size()){
                if(right - left < minLength){
                    start = left;
                    minLength = right - left;
                }

                char d = s.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(window.get(d).intValue() == need.get(d).intValue()){
                        valid--;
                    }
                    window.put(d, window.get(d)-1);
                }
            }




        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }
}
