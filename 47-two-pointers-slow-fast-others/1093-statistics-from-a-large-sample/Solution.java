/**
 * LeetCode 1093. Statistics from a Large Sample
 * Approach: `count[v]` gives the frequency of each value 0..255 directly,
 * so min/max are just the first/last nonzero buckets and mean/mode fall
 * out of a single weighted pass. The median is the interesting part: one
 * forward scan accumulates a running total while tracking two target
 * thresholds (the lower-middle and upper-middle 1-indexed ranks, which
 * coincide for an odd total) -- whichever bucket the running total first
 * reaches or passes each threshold in is that rank's value, found in
 * bulk per bucket rather than by stepping one sample at a time.
 * Time: O(256) | Space: O(1)
 */
class Solution {
    public double[] sampleStats(int[] count) {
        int min = -1, max = -1, mode = 0;
        long total = 0;
        double weightedSum = 0;

        for (int v = 0; v < 256; v++) {
            if (count[v] == 0) continue;
            if (min == -1) min = v;
            max = v;
            total += count[v];
            weightedSum += (double) v * count[v];
            if (count[v] > count[mode]) mode = v;
        }

        long lowerTarget = (total + 1) / 2;
        long upperTarget = total / 2 + 1;
        int lowerMedianVal = -1, upperMedianVal = -1;
        long cumulative = 0;
        for (int v = 0; v < 256 && upperMedianVal == -1; v++) {
            cumulative += count[v];
            if (lowerMedianVal == -1 && cumulative >= lowerTarget) lowerMedianVal = v;
            if (cumulative >= upperTarget) upperMedianVal = v;
        }

        double mean = weightedSum / total;
        double median = (lowerMedianVal + upperMedianVal) / 2.0;
        return new double[]{min, max, mean, median, mode};
    }
}
