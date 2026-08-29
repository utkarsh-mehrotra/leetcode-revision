/**
 * LeetCode 1025. Divisor Game
 * Approach: Alice wins iff n is even. Proof sketch: if n is even, Alice can
 * always subtract 1, handing Bob an odd number; every divisor of an odd n is
 * odd, so subtracting one from an odd n always yields an even number for the
 * opponent -- by induction the player facing an odd n always loses.
 * Time: O(1) | Space: O(1)
 */
class Solution {
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
