import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1104. Path In Zigzag Labelled Binary Tree
 * Approach: At each level L, labels run over [2^(L-1), 2^L - 1], either
 * left-to-right or mirrored depending on parity. Reflecting the current
 * label within its level's bounds (low + high - label) recovers what its
 * position would be in a normal (non-zigzag) numbering; halving that
 * position lands directly on the zigzag-labelled parent one level up.
 * Repeat from the target label back to the root, prepending each value.
 * Time: O(log label) | Space: O(log label) for the output path
 */
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> path = new ArrayList<>();
        int level = (int) (Math.log(label) / Math.log(2)) + 1;
        while (label >= 1) {
            path.add(0, label);
            int low = 1 << (level - 1);
            int high = (1 << level) - 1;
            label = (low + high - label) / 2;
            level--;
        }
        return path;
    }
}
