import java.util.PriorityQueue;

/**
 * LeetCode 703. Kth Largest Element in a Stream
 * Approach: Maintain a min-heap capped at size k -- its smallest element
 * is always the k-th largest seen so far. Each add pushes the new value
 * then evicts the heap's minimum if the heap grows past k.
 * Time: O(log k) per add | Space: O(k)
 */
class KthLargest {
    private final PriorityQueue<Integer> heap = new PriorityQueue<>();
    private final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) add(num);
    }

    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) heap.poll();
        return heap.peek();
    }
}
