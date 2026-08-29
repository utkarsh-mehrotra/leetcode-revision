import java.util.Arrays;

/**
 * LeetCode 354. Russian Doll Envelopes
 * Approach: Not a memoized-recursion fit at this problem's scale (up to
 * 10^5 envelopes makes an O(n^2) LIS-style recursion too slow). Sort by
 * width ascending (and height DESCENDING within equal widths, so same-
 * width envelopes can never nest with each other), then the answer is the
 * longest strictly increasing subsequence of heights, found via patience
 * sorting: maintain the smallest possible tail height for each achievable
 * subsequence length and binary-search where each new height slots in.
 * Time: O(n log n) | Space: O(n)
 */
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] tails = new int[envelopes.length];
        int size = 0;
        for (int[] envelope : envelopes) {
            int height = envelope[1];
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < height) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = height;
            if (lo == size) size++;
        }
        return size;
    }
}
