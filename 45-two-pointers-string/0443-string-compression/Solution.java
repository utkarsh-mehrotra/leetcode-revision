/**
 * LeetCode 443. String Compression
 * Approach: Slow/fast pointers over the character array. `fast` scans
 * forward to find the end of each run of identical characters; `slow` is
 * the write cursor that emits the character and, if the run length
 * exceeds 1, each digit of that length -- all done in place since the
 * write cursor never outruns the read cursor.
 * Time: O(n) | Space: O(1) extra
 */
class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        int slow = 0, fast = 0;
        while (fast < n) {
            char c = chars[fast];
            int runStart = fast;
            while (fast < n && chars[fast] == c) fast++;
            int runLength = fast - runStart;

            chars[slow++] = c;
            if (runLength > 1) {
                for (char digit : String.valueOf(runLength).toCharArray()) {
                    chars[slow++] = digit;
                }
            }
        }
        return slow;
    }
}
