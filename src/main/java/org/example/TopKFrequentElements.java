package org.example;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {

        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        int[] res = topKFrequentElements(nums, k);
        System.out.println(Arrays.toString(res));

        int[] res1 = topKFrequentBucketSort(nums,k);
        System.out.println(Arrays.toString(res1));
    }

    private static int[] topKFrequentBucketSort(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        //frequency count
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        List<Integer>[] buckets = new List[nums.length +1];
        for(int i =0;i<=nums.length;i++){
            buckets[i]  = new ArrayList<>();
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int number = entry.getKey();
            int frequency = entry.getValue();
            buckets[frequency].add(number);
        }

        List<Integer> resultList = new ArrayList<>();

        for(int i = buckets.length - 1; i >=0 && resultList.size() < k ; i-- ){
            resultList.addAll(buckets[i]);
        }

        int[] res = new int[k];
        for(int i =0;i<k;i++){
            res[i]  = resultList.get(i);
        }
    return  res;
    }

    private static int[] topKFrequentElements(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        //frequency count
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        System.out.println(" frequency map : "+map);
        // use a min-heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry:: getValue)
        );



        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            priorityQueue.offer(entry);
            if(priorityQueue.size() > k){
                priorityQueue.poll();
            }
        }
        System.out.println("Min heap map : "+priorityQueue);
        // Extract k Elements
        int[] res = new int[k];
        int i = 0;
        while(!priorityQueue.isEmpty()){
            res[i++] = priorityQueue.poll().getKey();
        }

        return res;

    }
}
