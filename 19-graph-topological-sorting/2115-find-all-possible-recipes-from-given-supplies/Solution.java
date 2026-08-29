import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 2115. Find All Possible Recipes from Given Supplies
 * Approach: Kahn's algorithm -- a recipe's indegree is the number of its
 * ingredients that are NOT already-available supplies (those are the
 * ones that must first be made as other recipes). Whenever a recipe
 * becomes makeable (indegree 0), it unlocks any recipe waiting on it as
 * an ingredient.
 * Time: O(recipes * avgIngredients) | Space: O(recipes * avgIngredients)
 */
class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> supplySet = new HashSet<>(Arrays.asList(supplies));
        Map<String, List<String>> waitingOn = new HashMap<>(); // ingredient -> recipes needing it
        Map<String, Integer> indegree = new HashMap<>();

        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            int missing = 0;
            for (String ingredient : ingredients.get(i)) {
                if (!supplySet.contains(ingredient)) {
                    missing++;
                    waitingOn.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipe);
                }
            }
            indegree.put(recipe, missing);
        }

        Deque<String> queue = new ArrayDeque<>();
        for (String recipe : recipes) {
            if (indegree.get(recipe) == 0) queue.add(recipe);
        }

        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String recipe = queue.poll();
            result.add(recipe);
            for (String next : waitingOn.getOrDefault(recipe, new ArrayList<>())) {
                int remaining = indegree.get(next) - 1;
                indegree.put(next, remaining);
                if (remaining == 0) queue.add(next);
            }
        }
        return result;
    }
}
