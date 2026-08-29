import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 131. Palindrome Partitioning
 * Approach: Top-down memoized recursion -- partitionsFrom(start) is every
 * way to split s[start:] into palindromic pieces, built by trying every
 * first-piece end point whose prefix is a palindrome and prepending it to
 * each partition of the remainder. isPalindrome is memoized separately.
 * Time: O(n * 2^n) worst case | Space: O(n * 2^n) worst case
 */
class Solution {
    private String s;
    private int n;
    private Boolean[][] palDp;
    private List<List<String>>[] partitionDp;

    @SuppressWarnings("unchecked")
    public List<List<String>> partition(String s) {
        this.s = s;
        this.n = s.length();
        this.palDp = new Boolean[n][n];
        this.partitionDp = new List[n + 1];
        return partitionsFrom(0);
    }

    private List<List<String>> partitionsFrom(int start) {
        if (start == n) {
            List<List<String>> base = new ArrayList<>();
            base.add(new ArrayList<>());
            return base;
        }
        if (partitionDp[start] != null) return partitionDp[start];
        List<List<String>> result = new ArrayList<>();
        for (int end = start; end < n; end++) {
            if (isPalindrome(start, end)) {
                String piece = s.substring(start, end + 1);
                for (List<String> rest : partitionsFrom(end + 1)) {
                    List<String> combined = new ArrayList<>();
                    combined.add(piece);
                    combined.addAll(rest);
                    result.add(combined);
                }
            }
        }
        partitionDp[start] = result;
        return result;
    }

    private boolean isPalindrome(int lo, int hi) {
        if (lo >= hi) return true;
        if (palDp[lo][hi] != null) return palDp[lo][hi];
        boolean result = s.charAt(lo) == s.charAt(hi) && isPalindrome(lo + 1, hi - 1);
        palDp[lo][hi] = result;
        return result;
    }
}
