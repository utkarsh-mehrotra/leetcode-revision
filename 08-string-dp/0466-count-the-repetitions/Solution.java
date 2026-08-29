import java.util.Arrays;

/**
 * LeetCode 466. Count The Repetitions
 * Approach: Not a natural fit for value-memoized recursion -- the useful
 * "memo" here is a cycle in the sequence of s2-start-indices produced by
 * repeatedly scanning one copy of s1, since that index can only take
 * s2.length() distinct values. One pass of s1 starting at s2-index `start`
 * always advances to the same next index and match count (a pure
 * function of `start`), so once an index repeats mid-scan we've found a
 * cycle and can fast-forward through the remaining s1 copies
 * arithmetically instead of simulating them one by one.
 * Time: O(n1 * s2.length()) worst case (short-circuits once a cycle is
 * found, typically within s2.length()+1 passes) | Space: O(n1 + s2.length())
 */
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len2 = s2.length();
        // For each starting index into s2, one pass over s1 yields a fixed
        // (nextStartIndex, s2CharsMatched) -- precompute both.
        int[] nextIndex = new int[len2];
        int[] matchedChars = new int[len2];
        for (int start = 0; start < len2; start++) {
            int j = start;
            int matched = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    j++;
                    matched++;
                    if (j == len2) j = 0;
                }
            }
            nextIndex[start] = j;
            matchedChars[start] = matched;
        }

        // visitedAtPass[idx] = the 1-indexed pass number at whose START we
        // previously had this s2-index (0 = not yet seen).
        int[] visitedAtPass = new int[len2];
        long[] charsAfterPass = new long[n1 + 1];
        int index = 0;

        for (int pass = 1; pass <= n1; pass++) {
            if (visitedAtPass[index] != 0) {
                int cycleStartPass = visitedAtPass[index];
                long charsBeforeCycle = charsAfterPass[cycleStartPass - 1];
                long charsPerCycle = charsAfterPass[pass - 1] - charsBeforeCycle;
                int cycleLength = pass - cycleStartPass;

                int remainingPasses = n1 - (cycleStartPass - 1);
                int fullCycles = remainingPasses / cycleLength;
                int leftoverPasses = remainingPasses % cycleLength;
                long charsFromLeftover = charsAfterPass[cycleStartPass - 1 + leftoverPasses] - charsBeforeCycle;

                long totalChars = charsBeforeCycle + (long) fullCycles * charsPerCycle + charsFromLeftover;
                return (int) ((totalChars / len2) / n2);
            }
            visitedAtPass[index] = pass;
            charsAfterPass[pass] = charsAfterPass[pass - 1] + matchedChars[index];
            index = nextIndex[index];
        }

        return (int) ((charsAfterPass[n1] / len2) / n2);
    }
}
