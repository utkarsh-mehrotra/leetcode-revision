import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 638. Shopping Offers
 * Approach: Top-down memoized recursion over the current needs vector --
 * try buying items individually at price[], or applying each special offer
 * that doesn't overbuy any item, recursing on the reduced needs. The needs
 * vector is serialized to a string key for the memo since it isn't a
 * simple integer/index state.
 * Time: O(offers * distinct-needs-states) | Space: O(distinct-needs-states)
 */
class Solution {
    private List<Integer> price;
    private List<List<Integer>> special;
    private Map<String, Integer> dp;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        this.price = price;
        this.special = special;
        this.dp = new HashMap<>();
        return solve(needs);
    }

    private int solve(List<Integer> needs) {
        String key = needs.toString();
        if (dp.containsKey(key)) return dp.get(key);

        int noOffer = 0; // buy every remaining item individually
        for (int i = 0; i < needs.size(); i++) {
            noOffer += needs.get(i) * price.get(i);
        }

        int best = noOffer;
        for (List<Integer> offer : special) {
            List<Integer> next = new ArrayList<>();
            boolean valid = true;
            for (int i = 0; i < needs.size(); i++) {
                int remaining = needs.get(i) - offer.get(i);
                if (remaining < 0) {
                    valid = false;
                    break;
                }
                next.add(remaining);
            }
            if (valid) {
                int offerPrice = offer.get(needs.size());
                best = Math.min(best, offerPrice + solve(next));
            }
        }
        dp.put(key, best);
        return best;
    }
}
