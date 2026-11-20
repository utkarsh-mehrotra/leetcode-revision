import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1907. Process Restricted Friend Requests
 * Approach: Union-Find, processing requests one at a time. Before
 * approving a request (u, v), check every restricted pair (a, b): if
 * granting the request would place a and b in the same component (i.e.
 * one of them is currently in u's component and the other in v's), the
 * request must be rejected; otherwise it's approved and u, v are unioned.
 * Time: O(requests * restrictions * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent;

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        boolean[] result = new boolean[requests.length];
        for (int i = 0; i < requests.length; i++) {
            int u = requests[i][0], v = requests[i][1];
            int ru = find(u), rv = find(v);
            if (ru == rv) {
                result[i] = true;
                continue;
            }
            boolean violates = false;
            for (int[] restriction : restrictions) {
                int ra = find(restriction[0]), rb = find(restriction[1]);
                if ((ra == ru && rb == rv) || (ra == rv && rb == ru)) {
                    violates = true;
                    break;
                }
            }
            if (!violates) {
                parent[ru] = rv;
                result[i] = true;
            }
        }
        return result;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
