/**
 * LeetCode 457. Circular Array Loop
 * Approach: For each unvisited starting index, run slow/fast pointers
 * along the "next index" function (wrapping circularly), but restricted
 * to moves that keep the SAME sign as the start (a loop can't switch
 * between forward and backward jumps). A cycle of length > 1 is a valid
 * loop; a fixed point (next index == current index) is a length-1
 * self-loop, which is explicitly disallowed. Every index touched during
 * a failed attempt is marked visited, since it can't be part of a valid
 * loop found from any other start either (its direction is fixed).
 * Time: O(n) | Space: O(1) extra (input reused for visited marking)
 */
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;

            int slow = i, fast = i;
            boolean forward = nums[i] > 0;
            while (true) {
                slow = next(nums, slow, forward);
                if (slow == -1) break;
                fast = next(nums, fast, forward);
                if (fast == -1) break;
                fast = next(nums, fast, forward);
                if (fast == -1) break;
                if (slow == fast) {
                    if (isSelfLoop(nums, slow)) break; // length-1 self-loop, invalid
                    return true;
                }
            }

            // Mark every index visited on this failed attempt as dead (direction fixed).
            int mark = i;
            while (nums[mark] != 0 && (nums[mark] > 0) == forward) {
                int nextIdx = next(nums, mark, forward);
                nums[mark] = 0;
                if (nextIdx == -1) break;
                mark = nextIdx;
            }
        }
        return false;
    }

    // Returns the next index in the same direction, or -1 if invalid (wrong
    // direction, already dead, or a self-loop).
    private int next(int[] nums, int idx, boolean forward) {
        if (nums[idx] == 0 || (nums[idx] > 0) != forward) return -1;
        if (isSelfLoop(nums, idx)) return -1;
        int n = nums.length;
        return ((idx + nums[idx]) % n + n) % n;
    }

    private boolean isSelfLoop(int[] nums, int idx) {
        int n = nums.length;
        return ((idx + nums[idx]) % n + n) % n == idx;
    }
}
