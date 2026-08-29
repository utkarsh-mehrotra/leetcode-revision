/**
 * LeetCode 91. Decode Ways
 * Approach: Rolling DP where dp[i] = number of ways to decode s[0..i).
 * Each state extends by a valid single digit (1-9) or valid two-digit
 * group (10-26).
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;
        int n = s.length();
        int prev2 = 1; // dp[i-2]
        int prev1 = 1; // dp[i-1]
        for (int i = 2; i <= n; i++) {
            int curr = 0;
            int oneDigit = s.charAt(i - 1) - '0';
            if (oneDigit >= 1) curr += prev1;
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + oneDigit;
            if (twoDigit >= 10 && twoDigit <= 26) curr += prev2;
            if (curr == 0) return 0;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
