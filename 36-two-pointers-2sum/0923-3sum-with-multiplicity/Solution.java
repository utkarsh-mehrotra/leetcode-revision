import java.util.Arrays;

/**
 * LeetCode 923. 3Sum With Multiplicity
 * Approach: Sort, fix the smallest element of each triple, then converge
 * two pointers on the remainder as in 3Sum -- but since duplicate VALUES
 * must each be counted as distinct triples (by index), a match doesn't
 * just record one triple: it counts how many equal values run at the
 * left pointer and how many run at the right pointer and multiplies
 * them (every left-run/right-run combination is a valid triple), or, if
 * the whole remaining window is one repeated value, counts all ways to
 * choose 2 indices from it.
 * Time: O(n²) | Space: O(1) extra
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int threeSumMulti(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        long result = 0;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            int need = target - arr[i];
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum < need) {
                    left++;
                } else if (sum > need) {
                    right--;
                } else if (arr[left] != arr[right]) {
                    int leftCount = 1;
                    while (left + 1 < right && arr[left + 1] == arr[left]) {
                        leftCount++;
                        left++;
                    }
                    int rightCount = 1;
                    while (right - 1 > left && arr[right - 1] == arr[right]) {
                        rightCount++;
                        right--;
                    }
                    result = (result + (long) leftCount * rightCount) % MOD;
                    left++;
                    right--;
                } else {
                    long windowSize = right - left + 1;
                    result = (result + windowSize * (windowSize - 1) / 2) % MOD;
                    break;
                }
            }
        }
        return (int) result;
    }
}
