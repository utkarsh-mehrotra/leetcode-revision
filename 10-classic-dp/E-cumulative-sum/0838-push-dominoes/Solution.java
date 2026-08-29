/**
 * LeetCode 838. Push Dominoes
 * Approach: Not a DP recursion -- pad the string with sentinel 'L'/'R' at
 * the ends, then process the gap between each pair of consecutive forces
 * once: equal forces push their whole gap that direction, opposite R..L
 * forces fall symmetrically toward the middle (with the true center
 * standing if the gap is odd), and L..R leaves the gap standing. No
 * overlapping subproblem to cache -- each gap is resolved in one pass.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public String pushDominoes(String dominoes) {
        String padded = "L" + dominoes + "R";
        char[] result = padded.toCharArray();
        int prev = 0;
        for (int i = 1; i < padded.length(); i++) {
            if (padded.charAt(i) == '.') continue;
            char left = padded.charAt(prev);
            char right = padded.charAt(i);
            if (left == right) {
                for (int j = prev + 1; j < i; j++) result[j] = left;
            } else if (left == 'R' && right == 'L') {
                int lo = prev + 1, hi = i - 1;
                while (lo < hi) {
                    result[lo++] = 'R';
                    result[hi--] = 'L';
                }
            }
            prev = i;
        }
        return new String(result, 1, dominoes.length());
    }
}
