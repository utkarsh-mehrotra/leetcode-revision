/**
 * LeetCode 1405. Longest Happy String
 * Approach: Not a natural fit for memoized recursion -- the state would
 * be (remaining a/b/c counts, last two characters) and the "value" cached
 * would be an entire string, which isn't a compact reusable subproblem
 * result. The standard solution is a greedy: at each step, append the
 * count-largest letter that wouldn't create a run of 3, falling back to
 * the next-largest if it would; this greedy choice is provably optimal.
 * Time: O(a + b + c) | Space: O(a + b + c) for the output
 */
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int[] counts = {a, b, c};
        char[] letters = {'a', 'b', 'c'};
        while (true) {
            int best = -1;
            for (int i = 0; i < 3; i++) {
                if (counts[i] <= 0) continue;
                boolean wouldRepeat = sb.length() >= 2
                    && sb.charAt(sb.length() - 1) == letters[i]
                    && sb.charAt(sb.length() - 2) == letters[i];
                if (wouldRepeat) continue;
                if (best == -1 || counts[i] > counts[best]) best = i;
            }
            if (best == -1) break;
            sb.append(letters[best]);
            counts[best]--;
        }
        return sb.toString();
    }
}
