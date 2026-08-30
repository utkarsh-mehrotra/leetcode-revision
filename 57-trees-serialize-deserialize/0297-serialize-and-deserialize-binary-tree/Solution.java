import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 297. Serialize and Deserialize Binary Tree
 * Approach: Pre-order traversal with an explicit "#" marker for null
 * children, comma-separated. Deserialization replays the same pre-order
 * recursion, consuming one token per call from a queue built from the
 * split string.
 * Time: O(n) serialize and deserialize | Space: O(n)
 */
class Codec {

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.val).append(',');
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    public TreeNode deserialize(String data) {
        Deque<String> tokens = new ArrayDeque<>(Arrays.asList(data.split(",")));
        return buildTree(tokens);
    }

    private TreeNode buildTree(Deque<String> tokens) {
        String token = tokens.poll();
        if (token.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = buildTree(tokens);
        node.right = buildTree(tokens);
        return node;
    }
}
