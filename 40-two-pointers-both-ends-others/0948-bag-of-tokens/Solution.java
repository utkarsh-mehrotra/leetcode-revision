import java.util.Arrays;

/**
 * LeetCode 948. Bag of Tokens
 * Approach: Sort, then converge two pointers from both ends. Greedily
 * play the cheapest remaining token face up (spend power, gain a score
 * point) whenever affordable; whenever it isn't but at least one score
 * point has been earned, "borrow" by playing the most expensive
 * remaining token face down (spend a score point, gain power) to afford
 * more cheap tokens later. Track the best score seen at any point, since
 * a face-down play can lower the score.
 * Time: O(n log n) | Space: O(1) extra
 */
class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int left = 0, right = tokens.length - 1;
        int score = 0, best = 0;
        while (left <= right) {
            if (power >= tokens[left]) {
                power -= tokens[left++];
                score++;
                best = Math.max(best, score);
            } else if (score > 0 && left < right) {
                power += tokens[right--];
                score--;
            } else {
                break;
            }
        }
        return best;
    }
}
