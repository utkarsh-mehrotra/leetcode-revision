import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 2092. Find All People With Secret
 * Approach: Union-Find, processing meetings grouped by identical time.
 * Within a time group, union every pair that meets; but a meeting only
 * spreads the secret if it eventually links back to person 0's
 * component AT THAT SAME MOMENT -- so after each time group, anyone not
 * connected to 0's component is un-merged (reset to their own singleton)
 * before moving to the next time, since their connections this round
 * don't actually carry the secret forward.
 * Time: O(meetings log meetings * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent;

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        union(0, firstPerson);

        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        int i = 0;
        while (i < meetings.length) {
            int j = i;
            Set<Integer> peopleInGroup = new HashSet<>();
            while (j < meetings.length && meetings[j][2] == meetings[i][2]) {
                union(meetings[j][0], meetings[j][1]);
                peopleInGroup.add(meetings[j][0]);
                peopleInGroup.add(meetings[j][1]);
                j++;
            }
            for (int person : peopleInGroup) {
                if (find(person) != find(0)) {
                    parent[person] = person;
                }
            }
            i = j;
        }

        List<Integer> result = new ArrayList<>();
        for (int person = 0; person < n; person++) {
            if (find(person) == find(0)) result.add(person);
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

    private void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra != rb) parent[ra] = rb;
    }
}
