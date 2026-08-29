import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 1311. Get Watched Videos by Your Friends
 * Approach: Multi-source-style level BFS from `id`, expanding one
 * friendship-hop at a time for exactly `level` hops; the set of people
 * found at that exact level are the ones whose watched videos count,
 * tallied by frequency and returned sorted (ties broken alphabetically).
 * Time: O(V + E + videos log videos) | Space: O(V + videos)
 */
class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        visited[id] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(id);

        for (int step = 0; step < level && !queue.isEmpty(); step++) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int person = queue.poll();
                for (int friend : friends[person]) {
                    if (!visited[friend]) {
                        visited[friend] = true;
                        queue.add(friend);
                    }
                }
            }
        }

        Map<String, Integer> freq = new HashMap<>();
        for (int person : queue) {
            for (String video : watchedVideos.get(person)) {
                freq.merge(video, 1, Integer::sum);
            }
        }
        List<String> result = new ArrayList<>(freq.keySet());
        result.sort((a, b) -> {
            int cmp = freq.get(a) - freq.get(b);
            return cmp != 0 ? cmp : a.compareTo(b);
        });
        return result;
    }
}
