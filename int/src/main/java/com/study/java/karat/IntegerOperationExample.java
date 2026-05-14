package com.study.java.karat;

import java.util.*;

public class IntegerOperationExample {
    /*public static void main(String[] args) {
        Integer number = 123456789;
        int divider = 10;
        int divisior = number%10;
        int remainingNum = number;
        int reverseNum = 0;
        while(remainingNum != 0) {
            int remainder = remainingNum%10;
            remainingNum /= 10;
            System.out.println(remainder);
            reverseNum += remainder;
            if (remainingNum != 0)
                reverseNum *= 10;
            System.out.println(reverseNum);
            System.out.println(remainingNum);

        }
        System.out.println(reverseNum);
    }*/

/*    public static void main(String[] args) {
        int[] num = {2, 5, 8, 9};
        //System.out.println(Arrays.toString(twoSum(num, 11)));
        System.out.println(Arrays.toString(twoSumWithTwoPointerNoSpace(num, 11)));

        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3 ("abc")
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // 1 ("b")
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // 3 ("wke")
        System.out.println(lengthOfLongestSubstring("abba"));     // 2 ("ab" or "ba")
        System.out.println(lengthOfLongestSubstring(""));         // 0
    }*/

    public static int[] twoSum(int[] num, int target) {
        HashMap<Integer, Integer> valueToIndexMap = new HashMap<>();
        int[] returnArray = null;
        int i = 0;
        for(int number : num) {
            valueToIndexMap.put(number, i++);
        }
        for(i =0; i < num.length; i++) {
            int diff = target - num[i];
            if(valueToIndexMap.containsKey(diff)) {
               int index = valueToIndexMap.get(diff);
               int first = i;
               int secondIndex = index;
                if (i > index) {
                    secondIndex = i;
                    first = index;
                }
                returnArray = new int[]{first, secondIndex};
            }

        }
        return returnArray;
    }

    public static int[] twoSumWithTwoPointerNoSpace(int[] num, int target) {
        int left = 0;
        int right = num.length - 1;
        int[] returnArray = null;
        while (left != right) {
            int targetNumber = num[left] + num[right];
            if(targetNumber == target)
                returnArray = new int[]{left+1, right+1};
            if(targetNumber > target) right--;
            else left++;
        }
        return returnArray;
    }

        public static int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> lastIndex = new HashMap<>();
            int left = 0;      // start of sliding window
            int maxLen = 0;

            for (int right = 0; right < s.length(); right++) {
                char ch = s.charAt(right);

                // If ch was seen and is inside the current window, move left pointer
                if (lastIndex.containsKey(ch) && lastIndex.get(ch) >= left) {
                    left = lastIndex.get(ch) + 1;
                }

                // update last seen index of ch
                lastIndex.put(ch, right);

                // update maximum length
                maxLen = Math.max(maxLen, right - left + 1);
            }

            return maxLen;
        }



    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        int n = nums.length;
        if (k == 1) return nums; // every element is the max of its window

        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices, not values

        for (int i = 0; i < n; i++) {

            // 1) Remove indices that are out of this window (i - k + 1 .. i)
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // 2) Maintain decreasing order in deque:
            // Remove indices from the back while current value is greater
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // 3) Add current index
            dq.offerLast(i);

            // 4) Window is formed when i >= k - 1, record max (front of deque)
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] ans = maxSlidingWindow(nums, k);
        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println("k = " + k);
        System.out.println("max in each window = " + Arrays.toString(ans));
        // Output: [3, 3, 5, 5, 6, 7]
    }

}


