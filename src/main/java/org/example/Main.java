package org.example;

import java.util.*;

class Solution {
    public String findTheLongestSubstring(String s) {
        // Initialize the vowel-to-bit mapping (a:0, e:1, i:2, o:3, u:4)
        Map<Character, Integer> vowelMap = new HashMap<>();
        vowelMap.put('a', 0);
        vowelMap.put('e', 1);
        vowelMap.put('i', 2);
        vowelMap.put('o', 3);
        vowelMap.put('u', 4);

        // Map to store the first occurrence of each bitmask
        Map<Integer, Integer> maskToIndex = new HashMap<>();
        maskToIndex.put(0, -1);  // Initialize with the default case (empty prefix)

        int mask = 0;  // This will store the current bitmask
        int maxLength = 0;  // To track the longest valid substring
        int start = 0;  // To track the start index of the longest substring

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the character is a vowel, flip the corresponding bit
            if (vowelMap.containsKey(c)) {
                mask ^= (1 << vowelMap.get(c));
            }

            // Check if we've seen this mask before
            if (maskToIndex.containsKey(mask)) {
                int prevIndex = maskToIndex.get(mask);
                int length = i - prevIndex;
                if (length > maxLength) {
                    maxLength = length;
                    start = prevIndex + 1;
                }
            } else {
                // Store the first occurrence of this mask
                maskToIndex.put(mask, i);
            }
        }

        // Return the longest valid substring
        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.findTheLongestSubstring("eleetminicoworoep");
        System.out.println("Result: " + result);  // Output: "leetminicowor"
    }
}
