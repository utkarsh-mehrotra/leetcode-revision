/**
 * LeetCode 165. Compare Version Numbers
 * Approach: Two pointers walking each version string's characters
 * directly (no upfront split into arrays), each extracting one
 * dot-delimited numeric revision at a time and comparing it against the
 * other version's revision at that position. A version that runs out of
 * revisions is treated as having trailing zeros, so "1.0" == "1".
 * Time: O(n+m) | Space: O(1)
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
        int n = version1.length(), m = version2.length();

        while (i < n || j < m) {
            int rev1 = 0;
            while (i < n && version1.charAt(i) != '.') {
                rev1 = rev1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            i++; // skip the dot

            int rev2 = 0;
            while (j < m && version2.charAt(j) != '.') {
                rev2 = rev2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            j++; // skip the dot

            if (rev1 != rev2) return rev1 < rev2 ? -1 : 1;
        }
        return 0;
    }
}
