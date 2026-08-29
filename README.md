# LeetCode Java Solutions

Staff-engineer-grade Java solutions to curated LeetCode problem sets — optimal time/space complexity, edge-case-safe, and written to paste directly into the LeetCode editor (no package declarations).

Each problem lives in its own folder as `NNNN-problem-slug/Solution.java`, where `NNNN` is the zero-padded LeetCode problem number.

## 01 — Linear DP

| # | Problem | Approach | Time / Space |
|---|---------|----------|---------------|
| [70](https://leetcode.com/problems/climbing-stairs/) | Climbing Stairs | Fibonacci rolling vars | O(n) / O(1) |
| [121](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | Best Time to Buy and Sell Stock | Track running min price | O(n) / O(1) |
| [746](https://leetcode.com/problems/min-cost-climbing-stairs/) | Min Cost Climbing Stairs | Rolling DP | O(n) / O(1) |
| [1025](https://leetcode.com/problems/divisor-game/) | Divisor Game | Parity insight | O(1) / O(1) |
| [91](https://leetcode.com/problems/decode-ways/) | Decode Ways | Rolling DP, 1- & 2-digit decodes | O(n) / O(1) |
| [96](https://leetcode.com/problems/unique-binary-search-trees/) | Unique Binary Search Trees | Catalan-number DP | O(n²) / O(n) |
| [198](https://leetcode.com/problems/house-robber/) | House Robber | Rolling DP | O(n) / O(1) |
| [279](https://leetcode.com/problems/perfect-squares/) | Perfect Squares | DP over squares ≤ i | O(n√n) / O(n) |
| [309](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | Buy/Sell Stock with Cooldown | 3-state machine | O(n) / O(1) |
| [322](https://leetcode.com/problems/coin-change/) | Coin Change | Unbounded knapsack DP | O(n·amount) / O(amount) |
| [338](https://leetcode.com/problems/counting-bits/) | Counting Bits | `dp[i]=dp[i&(i-1)]+1` | O(n) / O(n) |
| [343](https://leetcode.com/problems/integer-break/) | Integer Break | Greedy break into 3s | O(log n) / O(1) |
| [357](https://leetcode.com/problems/count-numbers-with-unique-digits/) | Count Numbers with Unique Digits | Combinatorics formula | O(n) / O(1) |
| [376](https://leetcode.com/problems/wiggle-subsequence/) | Wiggle Subsequence | Up/down counters | O(n) / O(1) |
| [416](https://leetcode.com/problems/partition-equal-subset-sum/) | Partition Equal Subset Sum | 0/1 knapsack boolean DP | O(n·sum) / O(sum) |
| [646](https://leetcode.com/problems/maximum-length-of-pair-chain/) | Maximum Length of Pair Chain | Greedy sort by end | O(n log n) / O(1) |
| [714](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) | Buy/Sell Stock with Transaction Fee | 2-state DP | O(n) / O(1) |
| [740](https://leetcode.com/problems/delete-and-earn/) | Delete and Earn | Bucket by value → House Robber | O(n+maxVal) / O(maxVal) |
| [790](https://leetcode.com/problems/domino-and-tromino-tiling/) | Domino and Tromino Tiling | Linear recurrence, mod 1e9+7 | O(n) / O(1) |
| [935](https://leetcode.com/problems/knight-dialer/) | Knight Dialer | DP over 10 digit-states | O(n) / O(1) |
| [983](https://leetcode.com/problems/minimum-cost-for-tickets/) | Minimum Cost For Tickets | Backward DP over calendar days | O(lastDay) / O(lastDay) |
| [1043](https://leetcode.com/problems/partition-array-for-maximum-sum/) | Partition Array for Maximum Sum | DP with window ≤ k | O(n·k) / O(n) |
| [1105](https://leetcode.com/problems/filling-bookcase-shelves/) | Filling Bookcase Shelves | DP with shelf-break loop | O(n²) / O(n) |
| [1218](https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/) | Longest Arithmetic Subsequence of Given Difference | HashMap DP | O(n) / O(n) |
| [1262](https://leetcode.com/problems/greatest-sum-divisible-by-three/) | Greatest Sum Divisible by Three | 3-state remainder DP | O(n) / O(1) |
| [123](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/) | Buy/Sell Stock III | 4-state DP | O(n) / O(1) |
| [552](https://leetcode.com/problems/student-attendance-record-ii/) | Student Attendance Record II | DP over (absences, trailing lates) | O(n) / O(1) |
| [639](https://leetcode.com/problems/decode-ways-ii/) | Decode Ways II | Rolling DP with `*` wildcard handling | O(n) / O(1) |
| [982](https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/) | Triples with Bitwise AND Equal to Zero | Pairwise-AND frequency + submask enumeration | O(n²+n·2¹⁶) / O(2¹⁶) |
| [1235](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | Maximum Profit in Job Scheduling | Sort by end + binary search + DP | O(n log n) / O(n) |
| [1326](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/) | Minimum Number of Taps to Open to Water a Garden | Max-reach array + greedy jump | O(n) / O(n) |
| [1359](https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/) | Count All Valid Pickup and Delivery Options | Closed-form product | O(n) / O(1) |
| [1406](https://leetcode.com/problems/stone-game-iii/) | Stone Game III | Suffix DP, best-of-3 lookahead | O(n) / O(n) |
| [1416](https://leetcode.com/problems/restore-the-array/) | Restore The Array | Suffix DP bounded by digits(k) | O(n·10) / O(n) |
| [1449](https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target/) | Form Largest Integer With Digits That Add up to Target | DP + greedy reconstruction | O(target·9) / O(target) |
| [1510](https://leetcode.com/problems/stone-game-iv/) | Stone Game IV | Boolean DP over perfect-square moves | O(n√n) / O(n) |
| [518](https://leetcode.com/problems/coin-change-2/) | Coin Change 2 | Unbounded knapsack counting DP | O(n·amount) / O(amount) |
| [256](https://leetcode.com/problems/paint-house/) | Paint House | Rolling 3-color DP | O(n) / O(1) |
