import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 1203. Sort Items by Groups Respecting Dependencies
 * Approach: Ungrouped items each get their own fresh singleton group.
 * Build two Kahn's-algorithm topological sorts: one over items (from
 * beforeItems), and a coarser one over groups (an edge group(a)->group(b)
 * whenever item a must precede item b in different groups). Combine by
 * walking the group order and, within each group, listing its items in
 * the order they appeared in the item-level topological sort.
 * Time: O(V + E) | Space: O(V + E)
 */
class Solution {
    public int[] sortItems(int n, int m, int[] group, int[][] beforeItems) {
        int groupCount = m;
        int[] realGroup = group.clone();
        for (int i = 0; i < n; i++) {
            if (realGroup[i] == -1) realGroup[i] = groupCount++;
        }

        List<List<Integer>> itemGraph = new ArrayList<>();
        int[] itemIndegree = new int[n];
        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());

        List<List<Integer>> groupGraph = new ArrayList<>();
        int[] groupIndegree = new int[groupCount];
        for (int i = 0; i < groupCount; i++) groupGraph.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            for (int before : beforeItems[i]) {
                itemGraph.get(before).add(i);
                itemIndegree[i]++;
                if (realGroup[before] != realGroup[i]) {
                    groupGraph.get(realGroup[before]).add(realGroup[i]);
                    groupIndegree[realGroup[i]]++;
                }
            }
        }

        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, groupCount);
        if (itemOrder.size() < n || groupOrder.size() < groupCount) return new int[0];

        Map<Integer, List<Integer>> itemsByGroup = new HashMap<>();
        for (int item : itemOrder) {
            itemsByGroup.computeIfAbsent(realGroup[item], k -> new ArrayList<>()).add(item);
        }

        int[] result = new int[n];
        int idx = 0;
        for (int g : groupOrder) {
            for (int item : itemsByGroup.getOrDefault(g, new ArrayList<>())) {
                result[idx++] = item;
            }
        }
        return result;
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int n) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int next : graph.get(node)) {
                if (--indegree[next] == 0) queue.add(next);
            }
        }
        return order;
    }
}
