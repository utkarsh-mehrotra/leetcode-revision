import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LeetCode 295. Find Median from Data Stream
 * Approach: Two heaps split the stream into a "lower half" (max-heap,
 * so its largest element is at the top) and an "upper half" (min-heap,
 * smallest at the top) -- conceptually the two-pointer-into-two-arrays
 * pattern applied to a running partition instead of two fixed arrays.
 * Every insert goes through the lower heap first, then its top is moved
 * to the upper heap to keep values properly partitioned; rebalancing
 * afterward keeps the two heaps within one element of each other's size,
 * so the median is always at one or both heap tops.
 * Time: O(log n) add, O(1) findMedian | Space: O(n)
 */
class MedianFinder {
    private final PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> upperHalf = new PriorityQueue<>();

    public void addNum(int num) {
        lowerHalf.offer(num);
        upperHalf.offer(lowerHalf.poll());
        if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }

    public double findMedian() {
        if (lowerHalf.size() > upperHalf.size()) return lowerHalf.peek();
        return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
    }
}
