import java.util.List;

/**
 * LeetCode 841. Keys and Rooms
 * Approach: Plain DFS from room 0, following every key found in each
 * newly visited room; all rooms are visitable iff DFS reaches every room.
 * Time: O(rooms + totalKeys) | Space: O(rooms)
 */
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms, 0, visited);
        for (boolean v : visited) if (!v) return false;
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int room, boolean[] visited) {
        if (visited[room]) return;
        visited[room] = true;
        for (int key : rooms.get(room)) {
            dfs(rooms, key, visited);
        }
    }
}
