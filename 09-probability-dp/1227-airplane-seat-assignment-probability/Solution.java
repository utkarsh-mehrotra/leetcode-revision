/**
 * LeetCode 1227. Airplane Seat Assignment Probability
 * Approach: Not a DP recursion -- this is a closed-form probability
 * insight. By symmetry, whenever the first passenger's seat is taken by
 * someone displaced earlier, the eventual choice for the n-th passenger's
 * own seat is equally likely to be their own seat or the first
 * passenger's seat, collapsing every n > 1 to exactly 0.5; n = 1 has no
 * randomness at all.
 * Time: O(1) | Space: O(1)
 */
class Solution {
    public double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1.0 : 0.5;
    }
}
