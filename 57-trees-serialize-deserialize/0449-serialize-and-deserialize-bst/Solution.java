/**
 * LeetCode 449. Serialize and Deserialize BST
 * Approach: A BST's shape is fully determined by its value ordering, so
 * pre-order values alone (no null markers needed) are enough to
 * reconstruct it. Deserialization replays pre-order construction with a
 * (lower, upper) bound per call: the next token becomes the subtree root
 * only while it fits the bound, exactly recovering the original split
 * between left and right children.
 * Time: O(n) serialize and deserialize | Space: O(n)
 */
class Codec {

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.val).append(',');
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    private int pos;

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] tokens = data.split(",");
        pos = 0;
        return build(tokens, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(String[] tokens, int lower, int upper) {
        if (pos == tokens.length) return null;
        int val = Integer.parseInt(tokens[pos]);
        if (val < lower || val > upper) return null;
        pos++;
        TreeNode node = new TreeNode(val);
        node.left = build(tokens, lower, val);
        node.right = build(tokens, val, upper);
        return node;
    }
}
