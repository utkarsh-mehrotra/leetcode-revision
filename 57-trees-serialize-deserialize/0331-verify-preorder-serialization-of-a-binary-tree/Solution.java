/**
 * LeetCode 331. Verify Preorder Serialization of a Binary Tree
 * Approach: Slot counting -- the root needs 1 available slot. Every
 * token (node or "#") consumes one slot; a non-null node then opens two
 * new slots for its children. The sequence is valid iff the slot count
 * never goes negative mid-scan and lands at exactly 0 once every token
 * is consumed.
 * Time: O(n) | Space: O(n) for the split array
 */
class Solution {
    public boolean isValidSerialization(String preorder) {
        int slots = 1;
        for (String token : preorder.split(",")) {
            slots--;
            if (slots < 0) return false;
            if (!token.equals("#")) slots += 2;
        }
        return slots == 0;
    }
}
