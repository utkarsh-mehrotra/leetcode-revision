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

## 02 — Knapsack

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [213](https://leetcode.com/problems/house-robber-ii/) | House Robber II | `best(i, hi)` (run twice over the two linear splits) | O(n) / O(n) |
| [474](https://leetcode.com/problems/ones-and-zeroes/) | Ones and Zeroes | `solve(i, zerosLeft, onesLeft)` | O(strs·m·n) / O(strs·m·n) |
| [494](https://leetcode.com/problems/target-sum/) | Target Sum | `solve(i, sum)` (offset-indexed) | O(n·totalSum) / O(n·totalSum) |
| [638](https://leetcode.com/problems/shopping-offers/) | Shopping Offers | `solve(needs)` (serialized needs vector as key) | O(offers·states) / O(states) |
| [650](https://leetcode.com/problems/2-keys-keyboard/) | 2 Keys Keyboard | `ops(n)` over divisors | O(n²) / O(n) |
| [801](https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/) | Minimum Swaps to Make Sequences Increasing | `solve(i, prevSwapped)` | O(n) / O(n) |
| [1626](https://leetcode.com/problems/best-team-with-no-conflicts/) | Best Team With No Conflicts | `teamEndingAt(i)` (LIS-style, sorted by age) | O(n²) / O(n) |
| [879](https://leetcode.com/problems/profitable-schemes/) | Profitable Schemes | `solve(i, membersLeft, profitSoFar)` (capped) | O(crimes·n·minProfit) / O(crimes·n·minProfit) |
| [956](https://leetcode.com/problems/tallest-billboard/) | Tallest Billboard | `best(i, diff)` (HashMap-memoized) | O(n·totalHeight) / O(n·totalHeight) |
| [1388](https://leetcode.com/problems/pizza-with-3n-slices/) | Pizza With 3n Slices | `pick(i, hi, k)` (run twice over the two linear splits) | O(n²) / O(n²) |
| [1402](https://leetcode.com/problems/reducing-dishes/) | Reducing Dishes | `total(i)` built from `suffixSum(i)` | O(n log n) / O(n) |
| [2742](https://leetcode.com/problems/painting-the-walls/) | Painting the Walls | `solve(i, coverageNeeded)` (capped) | O(n²) / O(n²) |

## 03 — Multi-Dimensional DP

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [120](https://leetcode.com/problems/triangle/) | Triangle | `best(row, col)` | O(n²) / O(n²) |
| [377](https://leetcode.com/problems/combination-sum-iv/) | Combination Sum IV | `ways(remaining)` | O(target·nums) / O(target) |
| [576](https://leetcode.com/problems/out-of-boundary-paths/) | Out of Boundary Paths | `solve(row, col, movesLeft)` | O(m·n·maxMove) / O(m·n·maxMove) |
| [688](https://leetcode.com/problems/knight-probability-in-chessboard/) | Knight Probability in Chessboard | `prob(row, col, movesLeft)` | O(n²·k) / O(n²·k) |
| [799](https://leetcode.com/problems/champagne-tower/) | Champagne Tower | `poured(row, col)` via `overflow(...)` | O(row²) / O(row²) |
| [813](https://leetcode.com/problems/largest-sum-of-averages/) | Largest Sum of Averages | `best(i, groupsLeft)` | O(n²·k) / O(n·k) |
| [931](https://leetcode.com/problems/minimum-falling-path-sum/) | Minimum Falling Path Sum | `solve(row, col)` | O(n²) / O(n²) |
| [1024](https://leetcode.com/problems/video-stitching/) | Video Stitching | `best(covered)` | O(n) / O(n) |
| [1027](https://leetcode.com/problems/longest-arithmetic-subsequence/) | Longest Arithmetic Subsequence | `solve(i)` (per-index diff map) | O(n²) / O(n²) |
| [1140](https://leetcode.com/problems/stone-game-ii/) | Stone Game II | `best(i, M)` | O(n²) / O(n²) |
| [1155](https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/) | Number of Dice Rolls With Target Sum | `ways(diceLeft, remaining)` | O(n·target·k) / O(n·target) |
| [1223](https://leetcode.com/problems/dice-roll-simulation/) | Dice Roll Simulation | `solve(rollsLeft, lastValue, streak)` | O(n·6·maxRollMax) / O(n·6·maxRollMax) |
| [1621](https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/) | Number of Sets of K Non-Overlapping Line Segments | `choose(a, b)` (Pascal's triangle) | O(n·k) / O(n·k) |
| [188](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/) | Best Time to Buy and Sell Stock IV | `solve(day, txnLeft, holding)` | O(n·k) / O(n·k) |
| [321](https://leetcode.com/problems/create-maximum-number/) | Create Maximum Number | Greedy monotonic-stack + merge *(not a DP recursion)* | O(k·(n1+n2+k²)) / O(n1+n2+k) |
| [403](https://leetcode.com/problems/frog-jump/) | Frog Jump | `canReachEnd(stoneIndex, lastJump)` | O(n²) / O(n²) |
| [410](https://leetcode.com/problems/split-array-largest-sum/) | Split Array Largest Sum | `best(i, groupsLeft)` | O(n²·m) / O(n·m) |
| [514](https://leetcode.com/problems/freedom-trail/) | Freedom Trail | `solve(ringIndex, keyIndex)` | O(ring·key²) / O(ring·key) |
| [871](https://leetcode.com/problems/minimum-number-of-refueling-stops/) | Minimum Number of Refueling Stops | `reach(i, stops)` | O(n²) / O(n²) |
| [920](https://leetcode.com/problems/number-of-music-playlists/) | Number of Music Playlists | `ways(songsLeft, uniqueLeft)` | O(goal·n) / O(goal·n) |
| [1220](https://leetcode.com/problems/count-vowels-permutation/) | Count Vowels Permutation | `count(length, vowel)` | O(n) / O(n) |
| [1289](https://leetcode.com/problems/minimum-falling-path-sum-ii/) | Minimum Falling Path Sum II | `solve(row, col)` | O(n³) / O(n²) |
| [1320](https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/) | Minimum Distance to Type a Word Using Two Fingers | `solve(i, idlePos)` | O(n·27) / O(n·27) |
| [1335](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/) | Minimum Difficulty of a Job Schedule | `best(i, daysLeft)` | O(n²·d) / O(n·d) |
| [1411](https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/) | Number of Ways to Paint N x 3 Grid | `solve(rowsLeft, patternIdx)` | O(n·12²) / O(n·12) |
| [1420](https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/) | Build Array Where You Can Find The Maximum Exactly K Comparisons | `solve(i, maxSoFar, cost)` | O(n·m²·k) / O(n·m·k) |
| [1444](https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/) | Number of Ways of Cutting a Pizza | `solve(row, col, cutsLeft)` | O(rows·cols·k·(rows+cols)) / O(rows·cols·k) |
| [1473](https://leetcode.com/problems/paint-house-iii/) | Paint House III | `solve(house, prevColor, neighborhoods)` | O(m·n²·target) / O(m·n·target) |
| [1575](https://leetcode.com/problems/count-all-possible-routes/) | Count All Possible Routes | `solve(curr, fuelLeft)` | O(n²·fuel) / O(n·fuel) |

## 04 — Interval DP

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [375](https://leetcode.com/problems/guess-number-higher-or-lower-ii/) | Guess Number Higher or Lower II | `cost(lo, hi)` | O(n³) / O(n²) |
| [413](https://leetcode.com/problems/arithmetic-slices/) | Arithmetic Slices | `endingAt(i)` | O(n) / O(n) |
| [486](https://leetcode.com/problems/predict-the-winner/) | Predict the Winner | `diff(lo, hi)` | O(n²) / O(n²) |
| [647](https://leetcode.com/problems/palindromic-substrings/) | Palindromic Substrings | `isPalindrome(lo, hi)` | O(n²) / O(n²) |
| [877](https://leetcode.com/problems/stone-game/) | Stone Game | `diff(lo, hi)` | O(n²) / O(n²) |
| [1039](https://leetcode.com/problems/minimum-score-triangulation-of-polygon/) | Minimum Score Triangulation of Polygon | `best(lo, hi)` | O(n³) / O(n²) |
| [1049](https://leetcode.com/problems/last-stone-weight-ii/) | Last Stone Weight II | `best(i, capacity)` (subset-sum knapsack) | O(n·sum) / O(n·sum) |
| [1130](https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/) | Minimum Cost Tree From Leaf Values | `best(lo, hi)` + `maxInRange(lo, hi)` | O(n³) / O(n²) |
| [1690](https://leetcode.com/problems/stone-game-vii/) | Stone Game VII | `diff(lo, hi)` | O(n²) / O(n²) |
| [312](https://leetcode.com/problems/burst-balloons/) | Burst Balloons | `best(lo, hi)` (last-burst framing) | O(n³) / O(n²) |
| [546](https://leetcode.com/problems/remove-boxes/) | Remove Boxes | `best(lo, hi, attachedCount)` | O(n⁴) / O(n³) |
| [664](https://leetcode.com/problems/strange-printer/) | Strange Printer | `best(lo, hi)` | O(n³) / O(n²) |
| [903](https://leetcode.com/problems/valid-permutations-for-di-sequence/) | Valid Permutations for DI Sequence | `ways(position, rank)` | O(n³) / O(n²) |
| [1000](https://leetcode.com/problems/minimum-cost-to-merge-stones/) | Minimum Cost to Merge Stones | `best(lo, hi, piles)` | O(n³/K) / O(n²·K) |
| [1478](https://leetcode.com/problems/allocate-mailboxes/) | Allocate Mailboxes | `best(i, boxesLeft)` | O(n²·k) / O(n²+n·k) |
| [1547](https://leetcode.com/problems/minimum-cost-to-cut-a-stick/) | Minimum Cost to Cut a Stick | `best(loIdx, hiIdx)` | O(m³) / O(m²) |
| [1563](https://leetcode.com/problems/stone-game-v/) | Stone Game V | `best(lo, hi)` | O(n³) / O(n²) |

## 05 — Bitmask DP

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [464](https://leetcode.com/problems/can-i-win/) | Can I Win | `win(usedMask)` | O(2ⁿ·n) / O(2ⁿ) |
| [698](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/) | Partition to K Equal Sum Subsets | `solve(mask)` | O(n·2ⁿ) / O(2ⁿ) |
| [691](https://leetcode.com/problems/stickers-to-spell-word/) | Stickers to Spell Word | `solve(coveredMask)` | O(2ⁿ·stickers·n) / O(2ⁿ) |
| [847](https://leetcode.com/problems/shortest-path-visiting-all-nodes/) | Shortest Path Visiting All Nodes | Multi-source BFS over (mask,node) *(cyclic state graph, not a DP recursion)* | O(2ⁿ·n²) / O(2ⁿ·n) |
| [1125](https://leetcode.com/problems/smallest-sufficient-team/) | Smallest Sufficient Team | `solve(missingSkillMask)` | O(2ᵐ·people) / O(2ᵐ·people) |
| [1349](https://leetcode.com/problems/maximum-students-taking-exam/) | Maximum Students Taking Exam | `solve(row, prevRowMask)` | O(rows·4^cols) / O(rows·2^cols) |
| [1434](https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/) | Number of Ways to Wear Different Hats to Each Other | `solve(hat, coveredMask)` | O(hats·2^people) / O(hats·2^people) |
| [1595](https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/) | Minimum Cost to Connect Two Groups of Points | `solve(i, coveredMask)` | O(size1·2^size2·size2) / O(size1·2^size2) |
| [1601](https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/) | Maximum Number of Achievable Transfer Requests | Backtracking over degree array *(no reusable state to memoize)* | O(2ᵐ) / O(n) |
| [1655](https://leetcode.com/problems/distribute-repeating-integers/) | Distribute Repeating Integers | `solve(valueIndex, unsatisfiedMask)` | O(values·3^queries) / O(values·2^queries) |
| [1659](https://leetcode.com/problems/maximize-grid-happiness/) | Maximize Grid Happiness | `solve(pos, introvertsLeft, extrovertsLeft, profile)` | O(mn·intro·extro·3ⁿ) / same |
| [1723](https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/) | Find Minimum Time to Finish All Jobs | `best(jobMask, workersLeft)` | O(3ⁿ) / O(2ⁿ·k) |
