# LeetCode Java Solutions

Staff-engineer-grade Java solutions to curated LeetCode problem sets — top-down memoized recursion (recursion + cache) as the standard technique, edge-case-safe, and written to paste directly into the LeetCode editor (no package declarations).

Each problem lives in its own folder as `NNNN-problem-slug/Solution.java`, where `NNNN` is the zero-padded LeetCode problem number.

## 01 — Linear DP

All solutions below use top-down memoized recursion (a recursive helper plus a memo array/map caching each subproblem's result), except **982**, which is a bitmask/combinatorics precomputation rather than a sequence DP and is called out explicitly.

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [70](https://leetcode.com/problems/climbing-stairs/) | Climbing Stairs | `climb(n)` | O(n) / O(n) |
| [121](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | Best Time to Buy and Sell Stock | `solve(day, state)` | O(n) / O(n) |
| [746](https://leetcode.com/problems/min-cost-climbing-stairs/) | Min Cost Climbing Stairs | `solve(i)` | O(n) / O(n) |
| [1025](https://leetcode.com/problems/divisor-game/) | Divisor Game | `win(n)` (game-theory) | O(n·d(n)) / O(n) |
| [91](https://leetcode.com/problems/decode-ways/) | Decode Ways | `ways(i)` | O(n) / O(n) |
| [96](https://leetcode.com/problems/unique-binary-search-trees/) | Unique Binary Search Trees | `solve(nodes)` | O(n²) / O(n) |
| [198](https://leetcode.com/problems/house-robber/) | House Robber | `best(i)` | O(n) / O(n) |
| [279](https://leetcode.com/problems/perfect-squares/) | Perfect Squares | `solve(n)` | O(n√n) / O(n) |
| [309](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | Buy/Sell Stock with Cooldown | `solve(day, state)` (3 states) | O(n) / O(n) |
| [322](https://leetcode.com/problems/coin-change/) | Coin Change | `solve(remaining)` | O(n·amount) / O(amount) |
| [338](https://leetcode.com/problems/counting-bits/) | Counting Bits | `popcount(i)` | O(n) / O(n) |
| [343](https://leetcode.com/problems/integer-break/) | Integer Break | `solve(n)` | O(n²) / O(n) |
| [357](https://leetcode.com/problems/count-numbers-with-unique-digits/) | Count Numbers with Unique Digits | `exactlyK(k)` | O(n) / O(n) |
| [376](https://leetcode.com/problems/wiggle-subsequence/) | Wiggle Subsequence | `up(i)` / `down(i)` | O(n²) / O(n) |
| [416](https://leetcode.com/problems/partition-equal-subset-sum/) | Partition Equal Subset Sum | `canReach(i, remaining)` | O(n·sum) / O(n·sum) |
| [646](https://leetcode.com/problems/maximum-length-of-pair-chain/) | Maximum Length of Pair Chain | `chain(i)` (LIS-style) | O(n²) / O(n) |
| [714](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) | Buy/Sell Stock with Transaction Fee | `solve(day, holding)` | O(n) / O(n) |
| [740](https://leetcode.com/problems/delete-and-earn/) | Delete and Earn | Bucket by value → `best(v)` | O(n+maxVal) / O(maxVal) |
| [790](https://leetcode.com/problems/domino-and-tromino-tiling/) | Domino and Tromino Tiling | `f(n)` | O(n) / O(n) |
| [935](https://leetcode.com/problems/knight-dialer/) | Knight Dialer | `ways(hopsRemaining, digit)` | O(n) / O(n) |
| [983](https://leetcode.com/problems/minimum-cost-for-tickets/) | Minimum Cost For Tickets | `solve(i)` + binary search | O(n log n) / O(n) |
| [1043](https://leetcode.com/problems/partition-array-for-maximum-sum/) | Partition Array for Maximum Sum | `best(i)` | O(n·k) / O(n) |
| [1105](https://leetcode.com/problems/filling-bookcase-shelves/) | Filling Bookcase Shelves | `best(i)` | O(n²) / O(n) |
| [1218](https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/) | Longest Arithmetic Subsequence of Given Difference | `length(i)` | O(n) / O(n) |
| [1262](https://leetcode.com/problems/greatest-sum-divisible-by-three/) | Greatest Sum Divisible by Three | `dp(i, r)` | O(n) / O(n) |
| [123](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/) | Buy/Sell Stock III | `solve(day, txnLeft, holding)` | O(n) / O(n) |
| [552](https://leetcode.com/problems/student-attendance-record-ii/) | Student Attendance Record II | `count(daysLeft, absences, lateStreak)` | O(n) / O(n) |
| [639](https://leetcode.com/problems/decode-ways-ii/) | Decode Ways II | `ways(i)` (wildcard-aware) | O(n) / O(n) |
| [982](https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/) | Triples with Bitwise AND Equal to Zero | Pairwise-AND frequency + submask enumeration *(bitmask precompute, not sequence DP)* | O(n²+n·2¹⁶) / O(2¹⁶) |
| [1235](https://leetcode.com/problems/maximum-profit-in-job-scheduling/) | Maximum Profit in Job Scheduling | `best(i)` + binary search | O(n log n) / O(n) |
| [1326](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/) | Minimum Number of Taps to Open to Water a Garden | `best(covered)` | O(n) / O(n) |
| [1359](https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/) | Count All Valid Pickup and Delivery Options | `ways(i)` | O(n) / O(n) |
| [1406](https://leetcode.com/problems/stone-game-iii/) | Stone Game III | `bestDiff(i)` | O(n) / O(n) |
| [1416](https://leetcode.com/problems/restore-the-array/) | Restore The Array | `ways(i)` | O(n·10) / O(n) |
| [1449](https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target/) | Form Largest Integer With Digits That Add up to Target | `maxDigits(t)` + greedy reconstruction | O(target·9) / O(target) |
| [1510](https://leetcode.com/problems/stone-game-iv/) | Stone Game IV | `win(i)` | O(n√n) / O(n) |
| [518](https://leetcode.com/problems/coin-change-2/) | Coin Change 2 | `ways(i, remaining)` | O(n·amount) / O(n·amount) |
| [256](https://leetcode.com/problems/paint-house/) | Paint House | `solve(i, prevColor)` | O(n) / O(n) |
