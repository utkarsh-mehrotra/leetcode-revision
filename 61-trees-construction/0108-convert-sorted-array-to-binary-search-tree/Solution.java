/**
 * LeetCode 108. Convert Sorted Array to Binary Search Tree
 * Approach: Recursively pick the middle element of each [lo, hi] range
 * as the subtree root (guaranteeing height balance since both halves are
 * as close to equal size as possible), then recurse on the left and
 * right halves.
 * Time: O(n) | Space: O(log n) recursion stack (excluding output)
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = build(nums, lo, mid - 1);
        node.right = build(nums, mid + 1, hi);
        return node;
    }
}
