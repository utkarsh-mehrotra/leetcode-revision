import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 1125. Smallest Sufficient Team
 * Approach: Top-down memoized recursion over `skillMask` (which required
 * skills are already covered). Fix the lowest uncovered skill and try
 * every person who has it (a valid team must include someone who covers
 * it), recursing on the mask with that person's skills merged in, and
 * keep the smallest resulting team.
 * Time: O(2^m * people) | Space: O(2^m * people)
 */
class Solution {
    private int[] personSkills;
    private List<Integer>[] dp;

    @SuppressWarnings("unchecked")
    public int[] smallestSufficientTeam(String[] reqSkills, List<List<String>> people) {
        int m = reqSkills.length;
        Map<String, Integer> skillIndex = new HashMap<>();
        for (int i = 0; i < m; i++) skillIndex.put(reqSkills[i], i);

        int n = people.size();
        personSkills = new int[n];
        for (int p = 0; p < n; p++) {
            int mask = 0;
            for (String skill : people.get(p)) {
                Integer idx = skillIndex.get(skill);
                if (idx != null) mask |= (1 << idx);
            }
            personSkills[p] = mask;
        }

        dp = new List[1 << m];
        List<Integer> result = solve((1 << m) - 1);
        int[] team = new int[result.size()];
        for (int i = 0; i < team.length; i++) team[i] = result.get(i);
        return team;
    }

    private List<Integer> solve(int missingMask) {
        if (missingMask == 0) return new ArrayList<>();
        if (dp[missingMask] != null) return dp[missingMask];

        int requiredSkill = Integer.numberOfTrailingZeros(missingMask);
        List<Integer> best = null;
        for (int p = 0; p < personSkills.length; p++) {
            if ((personSkills[p] & (1 << requiredSkill)) == 0) continue;
            List<Integer> rest = solve(missingMask & ~personSkills[p]);
            if (best == null || rest.size() + 1 < best.size()) {
                List<Integer> candidate = new ArrayList<>(rest);
                candidate.add(p);
                best = candidate;
            }
        }
        dp[missingMask] = best;
        return best;
    }
}
