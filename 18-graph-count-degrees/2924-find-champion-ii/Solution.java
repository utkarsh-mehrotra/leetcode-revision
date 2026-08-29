/**
 * LeetCode 2924. Find Champion II
 * Approach: A "stronger than" edge u->v means v can't be the champion.
 * The champion (if unique and determinable) must have indegree 0 --
 * nobody beats them. If more than one node has indegree 0, the strength
 * ordering among them is ambiguous from the given edges alone, so the
 * answer is undefined (-1).
 * Time: O(n + edges) | Space: O(n)
 */
class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] indegree = new int[n];
        for (int[] e : edges) {
            indegree[e[1]]++;
        }
        int champion = -1;
        int zeroCount = 0;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                champion = i;
                zeroCount++;
            }
        }
        return zeroCount == 1 ? champion : -1;
    }
}
