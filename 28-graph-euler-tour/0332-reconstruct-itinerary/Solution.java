import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 332. Reconstruct Itinerary
 * Approach: Hierholzer's algorithm for an Eulerian path. Every ticket is an
 * edge that must be used exactly once; a per-node min-heap of destinations
 * guarantees we always explore the lexicographically smallest option first.
 * Run iteratively (not recursively) so it doesn't stack-overflow: push
 * "JFK", and whenever the top of the stack has no more outgoing tickets,
 * it's finalized -- pop it to the front of the route. This is the standard
 * trick for building an Euler circuit/path without post-order recursion.
 * Time: O(E log E) | Space: O(E)
 */
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }

        LinkedList<String> route = new LinkedList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            String node = stack.peek();
            PriorityQueue<String> nextStops = graph.get(node);
            if (nextStops == null || nextStops.isEmpty()) {
                route.addFirst(stack.pop());
            } else {
                stack.push(nextStops.poll());
            }
        }
        return route;
    }
}
