package org.example;

import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatingNumber {

    public static void main(String[] args) {
        String s = "loveleetcode";

        Character res = usingStream(s);
        System.out.println("Using Streams "+ res);

        Map<Character, Integer> map = new LinkedHashMap<>();

        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+ 1);
        }

        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                System.out.println(entry.getKey());
                break;
            }
        }
    }

    private static Character usingStream(String s) {

        Character res = s.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                )).entrySet()
                .stream()
                .filter(c-> c.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        return res;
    }
}
