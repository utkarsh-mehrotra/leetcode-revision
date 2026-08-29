import java.util.Arrays;

/**
 * LeetCode 1363. Largest Multiple of Three
 * Approach: Not a DP recursion -- sort digits descending for the largest
 * arrangement, then fix the digit sum's remainder mod 3 by greedily
 * dropping the fewest, smallest digits. Dropping one digit with the same
 * remainder as the total (smallest such digit) fixes remainder 1 or 2 in
 * one removal; failing that, dropping two digits whose remainders sum to
 * the total's remainder mod 3 (again smallest available) always works,
 * since at least two digits of the "other" remainder class are
 * guaranteed to exist whenever a single-digit fix doesn't.
 * Time: O(n log n) | Space: O(n)
 */
class Solution {
    public String largestMultipleOfThree(int[] digits) {
        Integer[] sorted = new Integer[digits.length];
        for (int i = 0; i < digits.length; i++) sorted[i] = digits[i];
        Arrays.sort(sorted, (a, b) -> b - a);

        int sum = 0;
        for (int d : sorted) sum += d;
        int remainder = sum % 3;

        java.util.List<Integer> kept = new java.util.ArrayList<>(Arrays.asList(sorted));
        if (remainder != 0) {
            if (!removeSmallest(kept, remainder)) {
                removeSmallest(kept, 3 - remainder);
                removeSmallest(kept, 3 - remainder);
            }
        }
        if (kept.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int d : kept) sb.append(d);
        if (sb.charAt(0) == '0') return "0";
        return sb.toString();
    }

    // Remove the smallest kept digit whose value mod 3 == targetRemainder; returns whether one was found.
    private boolean removeSmallest(java.util.List<Integer> kept, int targetRemainder) {
        for (int i = kept.size() - 1; i >= 0; i--) {
            if (kept.get(i) % 3 == targetRemainder) {
                kept.remove(i);
                return true;
            }
        }
        return false;
    }
}
