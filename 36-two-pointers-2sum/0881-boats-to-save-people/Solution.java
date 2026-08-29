import java.util.Arrays;

/**
 * LeetCode 881. Boats to Save People
 * Approach: Sort, then converge two pointers from both ends. Always try
 * to pair the heaviest remaining person (right) with the lightest
 * remaining person (left) in one boat: if they fit together, both are
 * seated; if not, the heaviest person needs the boat alone (pairing them
 * with ANY lighter person would still exceed the limit, since the
 * lightest available is the best possible partner). Either way one boat
 * is used per step.
 * Time: O(n log n) | Space: O(1) extra
 */
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int boats = 0;
        while (left <= right) {
            if (people[left] + people[right] <= limit) left++;
            right--;
            boats++;
        }
        return boats;
    }
}
