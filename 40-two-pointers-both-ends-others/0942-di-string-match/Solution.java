/**
 * LeetCode 942. DI String Match
 * Approach: Two pointers converging from both ends of the value range
 * [0, n]. Reading the pattern left to right, an 'I' is satisfied by
 * assigning the smallest unused value (so the next value is guaranteed
 * larger) and a 'D' by assigning the largest unused value (so the next
 * value is guaranteed smaller); whichever pointer is used advances/
 * retreats. The single value left over at the end satisfies the last
 * character automatically.
 * Time: O(n) | Space: O(n) output
 */
class Solution {
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] result = new int[n + 1];
        int low = 0, high = n;
        for (int i = 0; i < n; i++) {
            result[i] = s.charAt(i) == 'I' ? low++ : high--;
        }
        result[n] = low; // low == high here
        return result;
    }
}
