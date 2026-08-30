/**
 * LeetCode 988. Smallest String Starting From Leaf
 * Approach: DFS building the path as a char array from root to the
 * current node; at each leaf, reverse it (leaf-to-root order is what the
 * problem wants) into a candidate string and keep the lexicographically
 * smallest one seen so far.
 * Time: O(n^2) worst case (string build per leaf) | Space: O(h)
 */
class Solution {
    private String best = null;

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return best;
    }

    private void dfs(TreeNode node, StringBuilder path) {
        if (node == null) return;
        path.append((char) ('a' + node.val));
        if (node.left == null && node.right == null) {
            String candidate = path.reverse().toString();
            path.reverse();
            if (best == null || candidate.compareTo(best) < 0) best = candidate;
        } else {
            dfs(node.left, path);
            dfs(node.right, path);
        }
        path.deleteCharAt(path.length() - 1);
    }
}
