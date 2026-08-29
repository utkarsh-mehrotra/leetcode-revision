import java.util.Arrays;

/**
 * LeetCode 475. Heaters
 * Approach: Sort both arrays, then walk them with independent pointers
 * starting from the beginning. For each house, advance the heater
 * pointer while the NEXT heater is a better (closer) fit than the
 * current one; the required radius for that house is its distance to
 * whichever heater pointer lands on, and the answer is the max over all
 * houses (the smallest radius that still covers every house).
 * Time: O(n log n + m log m) | Space: O(1) extra
 */
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int heaterIdx = 0;
        int result = 0;
        for (int house : houses) {
            while (heaterIdx + 1 < heaters.length
                    && Math.abs(heaters[heaterIdx + 1] - house) <= Math.abs(heaters[heaterIdx] - house)) {
                heaterIdx++;
            }
            result = Math.max(result, Math.abs(heaters[heaterIdx] - house));
        }
        return result;
    }
}
