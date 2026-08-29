import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1298. Maximum Candies You Can Get From Boxes
 * Approach: BFS/worklist simulation -- start with the initially-owned,
 * already-open boxes queued up; opening a box yields candies, more
 * boxes, and keys. A box or key discovered before its box is unlockable
 * (or before we own it) is held in a pending set and re-checked whenever
 * a new key or ownership arrives, since the search only terminates once
 * a full pass finds nothing newly openable.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] hasBox = new boolean[n];
        boolean[] opened = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();

        for (int box : initialBoxes) {
            hasBox[box] = true;
            if (status[box] == 1) queue.add(box);
        }

        int totalCandies = 0;
        while (!queue.isEmpty()) {
            int box = queue.poll();
            if (opened[box]) continue;
            opened[box] = true;
            totalCandies += candies[box];

            for (int key : keys[box]) {
                status[key] = 1;
                if (hasBox[key] && !opened[key]) queue.add(key);
            }
            for (int contained : containedBoxes[box]) {
                hasBox[contained] = true;
                if (status[contained] == 1 && !opened[contained]) queue.add(contained);
            }
        }
        return totalCandies;
    }
}
