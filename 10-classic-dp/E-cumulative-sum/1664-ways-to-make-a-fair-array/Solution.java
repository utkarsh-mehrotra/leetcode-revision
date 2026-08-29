/**
 * LeetCode 1664. Ways to Make a Fair Array
 * Approach: Top-down memoized recursion builds prefix sums restricted to
 * even indices and to odd indices. Removing index i shifts every later
 * element's parity, so the resulting even/odd totals are recombined from
 * the prefix-before-i (unshifted) and the suffix-after-i (shifted) parts;
 * an index is valid iff those two recombined totals match.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private int n;
    private Integer[] evenPrefixDp;
    private Integer[] oddPrefixDp;

    public int waysToMakeFair(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.evenPrefixDp = new Integer[n + 1];
        this.oddPrefixDp = new Integer[n + 1];
        int totalEven = evenPrefix(n);
        int totalOdd = oddPrefix(n);

        int count = 0;
        for (int i = 0; i < n; i++) {
            int leftEven = evenPrefix(i), leftOdd = oddPrefix(i);
            int rightEvenOriginal = totalEven - evenPrefix(i + 1);
            int rightOddOriginal = totalOdd - oddPrefix(i + 1);
            // After removing index i, elements after it swap parity roles.
            int newEven = leftEven + rightOddOriginal;
            int newOdd = leftOdd + rightEvenOriginal;
            if (newEven == newOdd) count++;
        }
        return count;
    }

    private int evenPrefix(int i) {
        if (i == 0) return 0;
        if (evenPrefixDp[i] != null) return evenPrefixDp[i];
        int result = evenPrefix(i - 1) + ((i - 1) % 2 == 0 ? nums[i - 1] : 0);
        evenPrefixDp[i] = result;
        return result;
    }

    private int oddPrefix(int i) {
        if (i == 0) return 0;
        if (oddPrefixDp[i] != null) return oddPrefixDp[i];
        int result = oddPrefix(i - 1) + ((i - 1) % 2 == 1 ? nums[i - 1] : 0);
        oddPrefixDp[i] = result;
        return result;
    }
}
