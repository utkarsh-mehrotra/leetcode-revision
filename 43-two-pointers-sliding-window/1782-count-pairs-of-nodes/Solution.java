import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 1782. Count Pairs of Nodes
 * Approach: incident(a,b) = degree[a] + degree[b] - edgeCount(a,b), which
 * is always <= the raw degree sum (edges are allowed to repeat between
 * the same pair, so edgeCount can exceed 1). For each query, first count
 * pairs by RAW degree sum > query using a sliding two-pointer scan over
 * the sorted degree array (classic caterpillar pair-sum counting). That
 * overcounts exactly the connected pairs whose actual incident value
 * dropped to or below the query -- for a pair with degree sum S and
 * edgeCount c, that happens for every query in the range
 * [S - c, S - 1]. Those ranges are recorded once as a difference map
 * over all unique connected pairs, converted to a sorted prefix-sum
 * table, and looked up per query via binary search in O(log E).
 * Time: O((n + E) log(n + E) + Q log E) | Space: O(n + E)
 */
class Solution {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] degree = new int[n + 1];
        long divisor = n + 1;
        Map<Long, Integer> pairEdgeCount = new HashMap<>();
        for (int[] e : edges) {
            degree[e[0]]++;
            degree[e[1]]++;
            int u = Math.min(e[0], e[1]), v = Math.max(e[0], e[1]);
            long key = (long) u * divisor + v;
            pairEdgeCount.merge(key, 1, Integer::sum);
        }

        int[] sortedDegree = java.util.Arrays.copyOfRange(degree, 1, n + 1);
        java.util.Arrays.sort(sortedDegree);

        TreeMap<Integer, Integer> diff = new TreeMap<>();
        for (Map.Entry<Long, Integer> entry : pairEdgeCount.entrySet()) {
            long key = entry.getKey();
            int u = (int) (key / divisor);
            int v = (int) (key % divisor);
            int edgeCount = entry.getValue();
            int degSum = degree[u] + degree[v];
            diff.merge(degSum - edgeCount, 1, Integer::sum);
            diff.merge(degSum, -1, Integer::sum);
        }

        int[] positions = new int[diff.size()];
        int[] prefixCorrection = new int[diff.size()];
        int idx = 0, running = 0;
        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            running += entry.getValue();
            positions[idx] = entry.getKey();
            prefixCorrection[idx] = running;
            idx++;
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int raw = countPairsAboveRawSum(sortedDegree, queries[i]);
            int correction = correctionAt(positions, prefixCorrection, queries[i]);
            result[i] = raw - correction;
        }
        return result;
    }

    // Cumulative correction applicable at exactly q: the sum of every
    // range-delta whose interval [lo, hi) contains q.
    private int correctionAt(int[] positions, int[] prefixCorrection, int q) {
        int lo = 0, hi = positions.length - 1, best = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (positions[mid] <= q) {
                best = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return best == -1 ? 0 : prefixCorrection[best];
    }

    private int countPairsAboveRawSum(int[] sortedDegree, int target) {
        int left = 0, right = sortedDegree.length - 1;
        int count = 0;
        while (left < right) {
            if (sortedDegree[left] + sortedDegree[right] > target) {
                count += right - left;
                right--;
            } else {
                left++;
            }
        }
        return count;
    }
}
