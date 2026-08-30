import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1028. Recover a Tree From Preorder Traversal
 * Approach: Scan the string once, counting leading dashes to get each
 * node's depth. A stack mirrors the current root-to-node path: pop it
 * down to size == depth (dropping ancestors we've fully backtracked
 * past), attach the new node as the left child of the stack's top if
 * that slot is free, else as the right child, then push it.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public TreeNode recoverFromPreorder(String traversal) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int i = 0;
        int n = traversal.length();
        while (i < n) {
            int depth = 0;
            while (i < n && traversal.charAt(i) == '-') {
                depth++;
                i++;
            }
            int start = i;
            while (i < n && Character.isDigit(traversal.charAt(i))) i++;
            int val = Integer.parseInt(traversal.substring(start, i));
            TreeNode node = new TreeNode(val);
            while (stack.size() > depth) stack.pop();
            if (!stack.isEmpty()) {
                TreeNode parent = stack.peek();
                if (parent.left == null) parent.left = node;
                else parent.right = node;
            }
            stack.push(node);
        }
        TreeNode root = null;
        while (!stack.isEmpty()) root = stack.pop();
        return root;
    }
}
