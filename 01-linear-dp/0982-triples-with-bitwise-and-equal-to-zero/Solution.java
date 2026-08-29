/**
 * LeetCode 982. Triples with Bitwise AND Equal To Zero
 * Approach: First count pairwise ANDs into a frequency array over all
 * possible mask values (n^2 pairs; values are < 2^16 per constraints).
 * Then for each element z, enumerate only the submasks of ~z (via the
 * standard `sub = (sub - 1) & mask` trick) and sum pairAndCount over them --
 * exactly the pairs whose AND shares no bit with z.
 * Time: O(n^2 + n * 2^16) worst case | Space: O(2^16)
 */
class Solution {
    public int countTriplets(int[] nums) {
        int maxMask = 1 << 16;
        int[] pairAndCount = new int[maxMask];
        for (int x : nums) {
            for (int y : nums) {
                pairAndCount[x & y]++;
            }
        }

        long triples = 0;
        for (int z : nums) {
            int complement = ~z & (maxMask - 1);
            for (int sub = complement; ; sub = (sub - 1) & complement) {
                triples += pairAndCount[sub];
                if (sub == 0) break;
            }
        }
        return (int) triples;
    }
}
