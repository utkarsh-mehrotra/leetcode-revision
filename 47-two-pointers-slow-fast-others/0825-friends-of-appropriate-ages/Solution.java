import java.util.Arrays;

/**
 * LeetCode 825. Friends of Appropriate Ages
 * Approach: Sort ages, then note that both the friend-request lower bound
 * (0.5*age + 7) and upper bound (age itself) are non-decreasing as age
 * increases -- so two pointers can sweep forward monotonically across
 * the whole sorted array instead of re-searching per person. For each
 * person (in sorted order), advance `left` past everyone too young and
 * `right` past everyone with a larger age, then the window [left, right)
 * holds every valid candidate INCLUDING the person themself when their
 * own age qualifies (age > 14) -- hence the -1, which harmlessly has no
 * effect when self doesn't qualify, since `left` has already advanced
 * past that person's own index in that case.
 * Time: O(n log n) | Space: O(1) extra
 */
class Solution {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int n = ages.length;
        int left = 0, right = 0, requests = 0;

        for (int k = 0; k < n; k++) {
            while (left < n && 2 * ages[left] <= ages[k] + 14) left++;
            while (right < n && ages[right] <= ages[k]) right++;
            requests += Math.max(0, right - left - 1);
        }
        return requests;
    }
}
