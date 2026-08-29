# LeetCode Java Solutions

Staff-engineer-grade Java solutions to curated LeetCode problem sets, edge-case-safe and written to paste directly into the LeetCode editor (no package declarations). The DP sections (01–16) use top-down memoized recursion as the standard technique; the Graph Algorithms sections (17–35) use whichever canonical algorithm the category names (BFS/DFS, Union-Find, Dijkstra, Bellman-Ford, Floyd-Warshall, Tarjan's, etc.); the Two Pointers sections (36+) use the two-pointer pattern the category names (converging ends, slow/fast, dual-array scan, split & merge) — each documented in that file's Javadoc, with honest exceptions called out where a problem doesn't actually fit its category's core technique.

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

## 06 — Digit DP

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [600](https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/) | Non-negative Integers without Consecutive Ones | `solve(pos, prevBit, tight)` | O(bits) / O(bits) |
| [902](https://leetcode.com/problems/numbers-at-most-n-given-digit-set/) | Numbers At Most N Given Digit Set | `solve(pos, tight)` | O(len·digits) / O(len) |
| [1012](https://leetcode.com/problems/numbers-with-repeated-digits/) | Numbers With Repeated Digits | `countUnique(pos, usedMask, started, tight)` | O(len·2¹⁰) / O(len·2¹⁰) |

## 07 — DP on Trees

`Solution.java` intentionally omits `TreeNode`/`ListNode` (LeetCode's judge already supplies them) — see the note below the table for how they're supplied locally. Most tree-DP problems here need no explicit memo table: each node is visited exactly once in a single post-order pass, so there's no overlapping subproblem to cache (called out per-row); Unique BST II and Reorder Array genuinely do revisit the same subproblem from different parents and use real memoization.

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [95](https://leetcode.com/problems/unique-binary-search-trees-ii/) | Unique Binary Search Trees II | `build(lo, hi)` (memoized: ranges repeat) | O(Catalan(n)·n) / O(Catalan(n)·n) |
| [337](https://leetcode.com/problems/house-robber-iii/) | House Robber III | `solve(node)` → {skipped, robbed} *(no memo needed)* | O(n) / O(n) |
| [1339](https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/) | Maximum Product of Splitted Binary Tree | `subtreeSum(node)` *(no memo needed)* | O(n) / O(n) |
| [1367](https://leetcode.com/problems/linked-list-in-binary-tree/) | Linked List in Binary Tree | `isSubPath` + `matches` *(no memo needed)* | O(treeNodes·listLen) / O(height+listLen) |
| [1372](https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/) | Longest ZigZag Path in a Binary Tree | `solve(node)` → {goLeft, goRight} *(no memo needed)* | O(n) / O(n) |
| [968](https://leetcode.com/problems/binary-tree-cameras/) | Binary Tree Cameras | `solve(node)` → 3-state coverage *(no memo needed)* | O(n) / O(n) |
| [1373](https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/) | Maximum Sum BST in Binary Tree | `solve(node)` → {isBST, min, max, sum} *(no memo needed)* | O(n) / O(n) |
| [1569](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/) | Number of Ways to Reorder Array to Get Same BST | `ways(list)` + `choose(a,b)` (memoized Pascal's triangle) | O(n²) / O(n²) |

*Local testing only:* since `TreeNode`/`ListNode` aren't part of `Solution.java`, they're supplied as sibling files during compilation, exactly as LeetCode's judge does — not part of this repo.

## 08 — String DP

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [392](https://leetcode.com/problems/is-subsequence/) | Is Subsequence | `solve(i, j)` | O(s·t) / O(s·t) |
| [131](https://leetcode.com/problems/palindrome-partitioning/) | Palindrome Partitioning | `partitionsFrom(start)` + `isPalindrome(lo,hi)` | O(n·2ⁿ) / O(n·2ⁿ) |
| [132](https://leetcode.com/problems/palindrome-partitioning-ii/) | Palindrome Partitioning II | `minCuts(i)` + `isPalindrome(lo,hi)` | O(n²) / O(n²) |
| [139](https://leetcode.com/problems/word-break/) | Word Break | `canBreak(i)` | O(n²) / O(n) |
| [467](https://leetcode.com/problems/unique-substrings-in-wraparound-string/) | Unique Substrings in Wraparound String | `runLength(i)` | O(n) / O(n) |
| [712](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/) | Minimum ASCII Delete Sum for Two Strings | `solve(i, j)` | O(len1·len2) / O(len1·len2) |
| [1048](https://leetcode.com/problems/longest-string-chain/) | Longest String Chain | `chainLength(word)` | O(n·maxLen²) / O(n) |
| [1405](https://leetcode.com/problems/longest-happy-string/) | Longest Happy String | Greedy largest-count-first *(not a DP recursion)* | O(a+b+c) / O(a+b+c) |
| [32](https://leetcode.com/problems/longest-valid-parentheses/) | Longest Valid Parentheses | `validEndingAt(i)` | O(n) / O(n) |
| [115](https://leetcode.com/problems/distinct-subsequences/) | Distinct Subsequences | `ways(i, j)` | O(s·t) / O(s·t) |
| [140](https://leetcode.com/problems/word-break-ii/) | Word Break II | `sentencesFrom(i)` | O(n·2ⁿ) / O(n·2ⁿ) |
| [466](https://leetcode.com/problems/count-the-repetitions/) | Count The Repetitions | Cycle detection over s2-start-index *(not a value-memoized recursion)* | O(n1·s2len) worst case / O(n1+s2len) |
| [472](https://leetcode.com/problems/concatenated-words/) | Concatenated Words | `canBuild(word, start)` | O(totalChars·maxWordLen) / O(totalChars) |
| [730](https://leetcode.com/problems/count-different-palindromic-subsequences/) | Count Different Palindromic Subsequences | `count(lo, hi)` | O(n²) / O(n²) |
| [940](https://leetcode.com/problems/distinct-subsequences-ii/) | Distinct Subsequences II | `total(i)` | O(n) / O(n) |
| [1147](https://leetcode.com/problems/longest-chunked-palindrome-decomposition/) | Longest Chunked Palindrome Decomposition | Greedy shortest-matching-chunk two-pointer *(not a DP recursion)* | O(n²) / O(1) |
| [1278](https://leetcode.com/problems/palindrome-partitioning-iii/) | Palindrome Partitioning III | `best(i, k)` + `changeCost(lo,hi)` | O(n²·k) / O(n²+n·k) |
| [1616](https://leetcode.com/problems/find-all-good-strings/) | Find All Good Strings | `solve(pos, evilState, tightLow, tightHigh)` (KMP automaton) | O(n·m·26) / O(n·m) |
| [1531](https://leetcode.com/problems/string-compression-ii/) | String Compression II | `best(i, k)` | O(n²·k) / O(n·k) |
| [1639](https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/) | Number of Ways to Form a Target String Given a Dictionary | `solve(targetIndex, column)` | O(target·wordLen) / O(target·wordLen) |

## 09 — Probability DP

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [808](https://leetcode.com/problems/soup-servings/) | Soup Servings | `solve(a, b)` (capped n) | O(1) amortized / O(1) amortized |
| [837](https://leetcode.com/problems/new-21-game/) | New 21 Game | `prob(i)` via `windowSumUpTo(i)` | O(k+maxPts) / O(k) |
| [1227](https://leetcode.com/problems/airplane-seat-assignment-probability/) | Airplane Seat Assignment Probability | Closed-form symmetry argument *(not a DP recursion)* | O(1) / O(1) |

## 10 — Classic DPs

### A. Kadane's Algorithm

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [53](https://leetcode.com/problems/maximum-subarray/) | Maximum Subarray | `endingAt(i)` | O(n) / O(n) |
| [152](https://leetcode.com/problems/maximum-product-subarray/) | Maximum Product Subarray | `endingAt(i)` → {max, min} | O(n) / O(n) |
| [898](https://leetcode.com/problems/bitwise-ors-of-subarrays/) | Bitwise ORs of Subarrays | `orsEndingAt(i)` | O(n·30) / O(n·30) |
| [978](https://leetcode.com/problems/longest-turbulent-subarray/) | Longest Turbulent Subarray | `up(i)` / `down(i)` | O(n) / O(n) |
| [1186](https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/) | Maximum Subarray Sum With One Deletion | `noDelete(i)` / `withDelete(i)` | O(n) / O(n) |
| [1191](https://leetcode.com/problems/k-concatenation-maximum-sum/) | K-Concatenation Maximum Sum | `endingAt(i)` (Kadane on 1 and 2 copies) | O(n) / O(n) |
| [368](https://leetcode.com/problems/largest-divisible-subset/) | Largest Divisible Subset | `sizeEndingAt(i)` (LIS-style) | O(n²) / O(n²) |
| [873](https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/) | Length of Longest Fibonacci Subsequence | `lengthEndingAt(j, i)` | O(n²) / O(n²) |
| [134](https://leetcode.com/problems/gas-station/) | Gas Station | Single-pass minimum-balance argument *(not a DP recursion)* | O(n) / O(1) |

### B. LCS

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [5](https://leetcode.com/problems/longest-palindromic-substring/) | Longest Palindromic Substring | `isPalindrome(lo, hi)` | O(n²) / O(n²) |
| [516](https://leetcode.com/problems/longest-palindromic-subsequence/) | Longest Palindromic Subsequence | `best(lo, hi)` | O(n²) / O(n²) |
| [718](https://leetcode.com/problems/maximum-length-of-repeated-subarray/) | Maximum Length of Repeated Subarray | `commonSuffixLen(i, j)` | O(m·n) / O(m·n) |
| [1143](https://leetcode.com/problems/longest-common-subsequence/) | Longest Common Subsequence | `solve(i, j)` | O(m·n) / O(m·n) |
| [10](https://leetcode.com/problems/regular-expression-matching/) | Regular Expression Matching | `matches(i, j)` | O(s·p) / O(s·p) |
| [44](https://leetcode.com/problems/wildcard-matching/) | Wildcard Matching | `matches(i, j)` | O(s·p) / O(s·p) |
| [72](https://leetcode.com/problems/edit-distance/) | Edit Distance | `solve(i, j)` | O(m·n) / O(m·n) |
| [97](https://leetcode.com/problems/interleaving-string/) | Interleaving String | `canForm(i, j)` | O(m·n) / O(m·n) |
| [1092](https://leetcode.com/problems/shortest-common-supersequence/) | Shortest Common Supersequence | `lcsLen(i, j)` + table-guided reconstruction | O(m·n) / O(m·n) |
| [1312](https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/) | Minimum Insertion Steps to Make a String Palindrome | `best(lo, hi)` | O(n²) / O(n²) |
| [1458](https://leetcode.com/problems/max-dot-product-of-two-subsequences/) | Max Dot Product of Two Subsequences | `best(i, j)` | O(m·n) / O(m·n) |

### C. LIS

Best Team With No Conflicts (1626) and Wiggle Subsequence (376) also belong here per the original list but are already solved elsewhere in this repo (Knapsack and Linear DP respectively) — not duplicated.

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [300](https://leetcode.com/problems/longest-increasing-subsequence/) | Longest Increasing Subsequence | `lengthEndingAt(i)` | O(n²) / O(n) |
| [673](https://leetcode.com/problems/number-of-longest-increasing-subsequence/) | Number of Longest Increasing Subsequence | `lengthAt(i)` / `countAt(i)` | O(n²) / O(n) |
| [354](https://leetcode.com/problems/russian-doll-envelopes/) | Russian Doll Envelopes | Patience sorting (binary search) *(O(n²) recursion too slow at this scale)* | O(n log n) / O(n) |
| [960](https://leetcode.com/problems/delete-columns-to-make-sorted-iii/) | Delete Columns to Make Sorted III | `keptEndingAt(i)` | O(cols²·rows) / O(cols) |
| [1671](https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/) | Minimum Number of Removals to Make Mountain Array | `leftLen(i)` / `rightLen(i)` | O(n²) / O(n) |
| [1691](https://leetcode.com/problems/maximum-height-by-stacking-cuboids/) | Maximum Height by Stacking Cuboids | `heightEndingAt(i)` | O(n²) / O(n) |
| [1187](https://leetcode.com/problems/make-array-strictly-increasing/) | Make Array Strictly Increasing | `solve(i, prevValueIndex)` | O(n·(n+m)·log m) / O(n·(n+m)) |

### D. 2D Grid Traversal

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [62](https://leetcode.com/problems/unique-paths/) | Unique Paths | `ways(r, c)` | O(m·n) / O(m·n) |
| [63](https://leetcode.com/problems/unique-paths-ii/) | Unique Paths II | `ways(r, c)` | O(m·n) / O(m·n) |
| [64](https://leetcode.com/problems/minimum-path-sum/) | Minimum Path Sum | `best(r, c)` | O(m·n) / O(m·n) |
| [1594](https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/) | Maximum Non-negative Product in a Matrix | `best(r, c)` → {max, min} | O(m·n) / O(m·n) |
| [1706](https://leetcode.com/problems/where-will-the-ball-fall/) | Where Will the Ball Fall | `finalColumn(row, col)` | O(rows·cols) / O(rows·cols) |
| [174](https://leetcode.com/problems/dungeon-game/) | Dungeon Game | `minHpNeeded(r, c)` | O(m·n) / O(m·n) |
| [741](https://leetcode.com/problems/cherry-pickup/) | Cherry Pickup | `solve(r1, c1, r2)` | O(n³) / O(n³) |
| [1301](https://leetcode.com/problems/number-of-paths-with-max-score/) | Number of Paths with Max Score | `solve(r, c)` → {bestSum, ways} | O(n²) / O(n²) |
| [1463](https://leetcode.com/problems/cherry-pickup-ii/) | Cherry Pickup II | `best(row, col1, col2)` | O(rows·cols²·9) / O(rows·cols²) |
| [1643](https://leetcode.com/problems/kth-smallest-instructions/) | Kth Smallest Instructions | `choose(a, b)` (Pascal's triangle) | O((rows+cols)²) / O((rows+cols)²) |

### E. Cumulative Sum

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [303](https://leetcode.com/problems/range-sum-query-immutable/) | Range Sum Query - Immutable | `prefixSum(i)` | O(n) prep, O(1)/query / O(n) |
| [221](https://leetcode.com/problems/maximal-square/) | Maximal Square | `sideAt(r, c)` | O(rows·cols) / O(rows·cols) |
| [304](https://leetcode.com/problems/range-sum-query-2d-immutable/) | Range Sum Query 2D - Immutable | `prefixSum(r, c)` | O(rows·cols) prep, O(1)/query / O(rows·cols) |
| [764](https://leetcode.com/problems/largest-plus-sign/) | Largest Plus Sign | `left/right/up/down(r, c)` | O(n²) / O(n²) |
| [838](https://leetcode.com/problems/push-dominoes/) | Push Dominoes | Sentinel-padded force-gap scan *(not a DP recursion)* | O(n) / O(n) |
| [1139](https://leetcode.com/problems/largest-1-bordered-square/) | Largest 1-Bordered Square | `left(r,c)` / `up(r,c)` + border check | O(n³) worst case / O(n²) |
| [1277](https://leetcode.com/problems/count-square-submatrices-with-all-ones/) | Count Square Submatrices with All Ones | `sideAt(r, c)` | O(rows·cols) / O(rows·cols) |
| [1314](https://leetcode.com/problems/matrix-block-sum/) | Matrix Block Sum | `prefixSum(r, c)` | O(rows·cols) / O(rows·cols) |
| [1423](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/) | Maximum Points You Can Obtain From Cards | `prefixSum(i)` | O(n) / O(n) |
| [1504](https://leetcode.com/problems/count-submatrices-with-all-ones/) | Count Submatrices With All Ones | `rowRunEndingAt(r, c)` | O(rows·cols²) worst case / O(rows·cols) |
| [1664](https://leetcode.com/problems/ways-to-make-a-fair-array/) | Ways to Make a Fair Array | `evenPrefix(i)` / `oddPrefix(i)` | O(n) / O(n) |
| [85](https://leetcode.com/problems/maximal-rectangle/) | Maximal Rectangle | `heightAt(r, c)` + monotonic-stack histogram pass | O(rows·cols) / O(rows·cols) |
| [363](https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/) | Max Sum of Rectangle No Larger Than K | `prefixSum(r, c)` + TreeSet scan *(column-pair loop isn't memoized)* | O(min(rows,cols)²·max(rows,cols)·log) / O(rows·cols) |
| [517](https://leetcode.com/problems/super-washing-machines/) | Super Washing Machines | `balancePrefix(i)` | O(n) / O(n) |
| [689](https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/) | Maximum Sum of 3 Non-Overlapping Subarrays | `best(start, windowsLeft)` | O(n) / O(n) |
| [1074](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/) | Number of Submatrices That Sum to Target | `prefixSum(r, c)` + hashmap scan *(row-pair loop isn't memoized)* | O(rows²·cols) / O(rows·cols) |
| [1537](https://leetcode.com/problems/get-the-maximum-score/) | Get the Maximum Score | Two-pointer merge-walk *(not a DP recursion)* | O(m+n) / O(1) |

### F. Hashmap (SubArray)

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [523](https://leetcode.com/problems/continuous-subarray-sum/) | Continuous Subarray Sum | First-index-per-remainder scan *(not a DP recursion)* | O(n) / O(min(n,k)) |
| [1477](https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/) | Find Two Non-Overlapping Sub-arrays Each With Target Sum | Prefix-indexed single pass *(not a DP recursion)* | O(n) / O(n) |
| [1546](https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/) | Maximum Number of Non-Overlapping Subarrays With Sum Equals Target | Greedy prefix-set reset *(not a DP recursion)* | O(n) / O(n) |

## 11 — DP + Alpha (Tricks/DS)

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [446](https://leetcode.com/problems/arithmetic-slices-ii-subsequence/) | Arithmetic Slices II - Subsequence | `solve(i)` (per-index diff→count map) | O(n²) / O(n²) |
| [975](https://leetcode.com/problems/odd-even-jump/) | Odd Even Jump | `canReachEnd(i, odd)` (TreeMap-precomputed targets) | O(n log n) / O(n) |
| [1425](https://leetcode.com/problems/constrained-subsequence-sum/) | Constrained Subsequence Sum | `best(i)` (monotonic deque window max) | O(n) / O(n) |
| [1687](https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/) | Delivering Boxes from Storage to Ports | `dp(k)` (monotonic deque over trip-start candidates) | O(n) / O(n) |
| [42](https://leetcode.com/problems/trapping-rain-water/) | Trapping Rain Water | `leftMax(i)` / `rightMax(i)` | O(n) / O(n) |

## 12 — Insertion DP

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [629](https://leetcode.com/problems/k-inverse-pairs-array/) | K Inverse Pairs Array | `ways(n, k)` (telescoping window) | O(n·k) / O(n·k) |

## 13 — Graph DP

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [787](https://leetcode.com/problems/cheapest-flights-within-k-stops/) | Cheapest Flights Within K Stops | `minCost(node, stopsLeft)` | O(K·E) / O(V·K) |
| [943](https://leetcode.com/problems/find-the-shortest-superstring/) | Find the Shortest Superstring | `extraCost(usedMask, lastWord)` | O(2ⁿ·n²) / O(2ⁿ·n) |

## 14 — Memoization

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [1654](https://leetcode.com/problems/minimum-jumps-to-reach-home/) | Minimum Jumps to Reach Home | BFS over (position, justJumpedBack) *(cyclic state graph, not a DP recursion)* | O(bound) / O(bound) |
| [87](https://leetcode.com/problems/scramble-string/) | Scramble String | `solve(i1, i2, len)` | O(n⁴) / O(n³) |
| [1240](https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/) | Tiling a Rectangle with the Fewest Squares | Skyline branch-and-bound backtracking *(state space too large to memoize usefully)* | exponential worst case, pruned / O(m) |
| [1269](https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/) | Number of Ways to Stay in the Same Place After Some Steps | `solve(stepsLeft, pos)` | O(steps·min(steps,arrLen)) / same |
| [1340](https://leetcode.com/problems/jump-game-v/) | Jump Game V | `reach(i)` (processed shortest-to-tallest) | O(n·d) / O(n) |
| [1553](https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/) | Minimum Number of Days to Eat N Oranges | `days(n)` (hashmap-memoized) | O(log²n) / O(log²n) |

## 15 — Binary Lifting

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [1483](https://leetcode.com/problems/kth-ancestor-of-a-tree-node/) | Kth Ancestor of a Tree Node | `up(node, j)` (2^j-th ancestor table) | O(n log n) prep, O(log n)/query / O(n log n) |

## 16 — Math

| # | Problem | Recursive state | Time / Space |
|---|---------|------------------|---------------|
| [264](https://leetcode.com/problems/ugly-number-ii/) | Ugly Number II | `ugly(i)` (three-pointer merge) | O(n) / O(n) |
| [1641](https://leetcode.com/problems/count-sorted-vowel-strings/) | Count Sorted Vowel Strings | `ways(length, startVowel)` | O(n) / O(n) |
| [818](https://leetcode.com/problems/race-car/) | Race Car | `solve(t)` | O(target·log target) / O(target) |
| [887](https://leetcode.com/problems/super-egg-drop/) | Super Egg Drop | `maxFloors(eggs, moves)` | O(eggs·log n) / O(eggs·log n) |
| [964](https://leetcode.com/problems/least-operators-to-express-number/) | Least Operators to Express Number | `solve(target)` | O(log²ₓ(target)) / O(logₓ(target)) |
| [1363](https://leetcode.com/problems/largest-multiple-of-three/) | Largest Multiple of Three | Greedy remainder-fixing removal *(not a DP recursion)* | O(n log n) / O(n) |
| [1611](https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/) | Minimum One Bit Operations to Make Integers Zero | `minimumOneBitOperations(n)` *(strictly decreasing chain, no memo needed)* | O(log n) / O(log n) |

# Graph Algorithms

## Part I — Basics of Graph

### 17. Simple DFS/BFS

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [399](https://leetcode.com/problems/evaluate-division/) | Evaluate Division | Weighted graph + DFS per query | O(E+Q·V) / O(V+E) |
| [841](https://leetcode.com/problems/keys-and-rooms/) | Keys and Rooms | DFS | O(rooms+keys) / O(rooms) |
| [1311](https://leetcode.com/problems/get-watched-videos-by-your-friends/) | Get Watched Videos by Your Friends | Level-order BFS | O(V+E+videos log videos) / O(V+videos) |
| [1971](https://leetcode.com/problems/find-if-path-exists-in-graph/) | Find if Path Exists in Graph | BFS | O(V+E) / O(V+E) |
| [2101](https://leetcode.com/problems/detonate-the-maximum-bombs/) | Detonate the Maximum Bombs | Directed graph + DFS per start | O(n²) / O(n²) |

### 18. Count Degrees

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [997](https://leetcode.com/problems/find-the-town-judge/) | Find the Town Judge | Net trust score (indegree − outdegree) | O(n+trust) / O(n) |
| [1557](https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/) | Minimum Number of Vertices to Reach All Nodes | Indegree-0 nodes | O(n+edges) / O(n) |
| [1615](https://leetcode.com/problems/maximal-network-rank/) | Maximal Network Rank | Degree sum − shared-edge adjustment | O(n²+roads) / O(n²) |
| [1761](https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/) | Minimum Degree of a Connected Trio in a Graph | Adjacency matrix + triple loop | O(n³) / O(n²) |
| [1782](https://leetcode.com/problems/count-pairs-of-nodes/) | Count Pairs of Nodes | Sorted-degree two-pointer + direct-edge correction | O((n+m) log n + m + q log n) / O(n+m) |
| [1791](https://leetcode.com/problems/find-center-of-star-graph/) | Find Center of Star Graph | Shared endpoint of first 2 edges | O(1) / O(1) |
| [2285](https://leetcode.com/problems/maximum-total-importance-of-roads/) | Maximum Total Importance of Roads | Rearrangement inequality (sort by degree) | O(n log n+roads) / O(n) |
| [2374](https://leetcode.com/problems/node-with-highest-edge-score/) | Node With Highest Edge Score | Indegree-weighted sum, argmax | O(n) / O(n) |
| [2497](https://leetcode.com/problems/maximum-star-sum-of-a-graph/) | Maximum Star Sum of a Graph | Per-node top-k positive neighbors | O((n+edges) log maxDegree) / O(n+edges) |
| [2508](https://leetcode.com/problems/add-edges-to-make-degrees-of-all-nodes-even/) | Add Edges to Make Degrees of All Nodes Even | Odd-degree case analysis (0/2/4 nodes) | O(n²) / O(n²) |
| [2924](https://leetcode.com/problems/find-champion-ii/) | Find Champion II | Unique indegree-0 node | O(n+edges) / O(n) |

### 19. Topological Sorting

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [207](https://leetcode.com/problems/course-schedule/) | Course Schedule | Kahn's algorithm | O(V+E) / O(V+E) |
| [210](https://leetcode.com/problems/course-schedule-ii/) | Course Schedule II | Kahn's algorithm | O(V+E) / O(V+E) |
| [797](https://leetcode.com/problems/all-paths-from-source-to-target/) | All Paths From Source to Target | DFS backtracking (DAG, no visited set needed) | O(2ⱽ·V) / O(2ⱽ·V) |
| [802](https://leetcode.com/problems/find-eventual-safe-states/) | Find Eventual Safe States | 3-color DFS cycle detection | O(V+E) / O(V+E) |
| [1203](https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/) | Sort Items by Groups Respecting Dependencies | Two-level Kahn's algorithm (items + groups) | O(V+E) / O(V+E) |
| [2192](https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/) | All Ancestors of a Node in a Directed Acyclic Graph | Kahn's algorithm + BitSet ancestor propagation | O((V+E)·V/64) / O(V²/64) |
| [1462](https://leetcode.com/problems/course-schedule-iv/) | Course Schedule IV | Kahn's algorithm + BitSet reachability propagation | O((V+E)·V/64) / O(V²/64) |
| [2213](https://leetcode.com/problems/strange-printer-ii/) | Strange Printer II | Bounding-box dependency graph + Kahn's cycle check | O(rows·cols·colors) / O(colors²) |
| [2050](https://leetcode.com/problems/parallel-courses-iii/) | Parallel Courses III | Kahn's algorithm + longest-path finish times | O(V+E) / O(V+E) |
| [2115](https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/) | Find All Possible Recipes from Given Supplies | Kahn's algorithm | O(recipes·avgIngredients) / same |
| [2392](https://leetcode.com/problems/build-a-matrix-with-conditions/) | Build a Matrix With Conditions | Two independent Kahn's-algorithm sorts | O(k+conditions) / O(k²) |

### 20. Union-Find

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [547](https://leetcode.com/problems/number-of-provinces/) | Number of Provinces | Union-Find | O(n²α(n)) / O(n) |
| [684](https://leetcode.com/problems/redundant-connection/) | Redundant Connection | Union-Find | O(nα(n)) / O(n) |
| [685](https://leetcode.com/problems/redundant-connection-ii/) | Redundant Connection II | Two-candidate-edge Union-Find | O(nα(n)) / O(n) |
| [947](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/) | Most Stones Removed with Same Row or Column | Union-Find over shared row/col index space | O(nα(n)) / O(n) |
| [990](https://leetcode.com/problems/satisfiability-of-equality-equations/) | Satisfiability of Equality Equations | Union-Find | O(nα(26)) / O(26) |
| [1632](https://leetcode.com/problems/rank-transform-of-a-matrix/) | Rank Transform of a Matrix | Value-grouped Union-Find + rank propagation | O(rc·log(rc)) / O(rc) |
| [1319](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) | Number of Operations to Make Network Connected | Union-Find | O(edgesα(n)) / O(n) |
| [1579](https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/) | Remove Max Number of Edges to Keep Graph Fully Traversable | Dual Union-Find | O(edgesα(n)) / O(n) |
| [1697](https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/) | Checking Existence of Edge Length Limited Paths | Offline Union-Find | O((E+Q)log(E+Q)) / O(n+E+Q) |
| [1907](https://leetcode.com/problems/process-restricted-friend-requests/) | Process Restricted Friend Requests | Union-Find with pre-union restriction check | O(requests·restrictionsα(n)) / O(n) |
| [2092](https://leetcode.com/problems/find-all-people-with-secret/) | Find All People With Secret | Time-grouped Union-Find with reset | O(m log m·α(n)) / O(n) |
| [2316](https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/) | Count Unreachable Pairs of Nodes in an Undirected Graph | Union-Find + component sizes | O(n+edgesα(n)) / O(n) |
| [2492](https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/) | Minimum Score of a Path Between Two Cities | Union-Find | O((n+roads)α(n)) / O(n) |
| [2685](https://leetcode.com/problems/count-the-number-of-complete-components/) | Count the Number of Complete Components | Union-Find + node/edge counts | O(n+edgesα(n)) / O(n) |

### 21. Bipartite

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [785](https://leetcode.com/problems/is-graph-bipartite/) | Is Graph Bipartite? | BFS 2-coloring | O(V+E) / O(V) |
| [886](https://leetcode.com/problems/possible-bipartition/) | Possible Bipartition | BFS 2-coloring | O(n+dislikes) / O(n+dislikes) |

## Part II — Medium Level Topics of Graph

### 22. BFS Variants (0-1 BFS, multi-source BFS)

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [310](https://leetcode.com/problems/minimum-height-trees/) | Minimum Height Trees | Multi-source BFS leaf-peeling | O(n) / O(n) |
| [924](https://leetcode.com/problems/minimize-malware-spread/) | Minimize Malware Spread | Union-Find + sole-infector analysis | O(n²α(n)) / O(n) |
| [928](https://leetcode.com/problems/minimize-malware-spread-ii/) | Minimize Malware Spread II | Union-Find over clean subgraph + touch analysis | O(n²α(n)) / O(n) |
| [1298](https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/) | Maximum Candies You Can Get From Boxes | Worklist BFS (boxes/keys) | O(n) / O(n) |

### 23. Dijkstra's Algorithm

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [743](https://leetcode.com/problems/network-delay-time/) | Network Delay Time | Dijkstra | O(E log V) / O(V+E) |
| [882](https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/) | Reachable Nodes in Subdivided Graph | Dijkstra on collapsed graph + per-edge budget analysis | O(E log V) / O(V+E) |
| [1514](https://leetcode.com/problems/path-with-maximum-probability/) | Path with Maximum Probability | Dijkstra with a max-heap | O(E log V) / O(V+E) |
| [1368](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/) | Minimum Cost to Make at Least One Valid Path in a Grid | 0-1 BFS | O(rows·cols) / O(rows·cols) |
| [1786](https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/) | Number of Restricted Paths From First to Last Node | Dijkstra + memoized path counting | O(E log V) / O(V+E) |
| [1928](https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time/) | Minimum Cost to Reach Destination in Time | Cost-ordered Dijkstra with time pruning | O(E log E) / O(V+E) |
| [1976](https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/) | Number of Ways to Arrive at Destination | Dijkstra + path-count accumulation | O(E log V) / O(V+E) |
| [2039](https://leetcode.com/problems/the-time-when-the-network-becomes-idle/) | The Time When the Network Becomes Idle | BFS + resend-timing formula | O(V+E) / O(V+E) |
| [2045](https://leetcode.com/problems/second-minimum-time-to-reach-destination/) | Second Minimum Time to Reach Destination | BFS for 2nd-shortest distinct distance + signal simulation | O(V+E) / O(V+E) |
| [2203](https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths/) | Minimum Weighted Subgraph With the Required Paths | 3x Dijkstra (2 forward, 1 reversed) | O(E log V) / O(V+E) |
| [2290](https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/) | Minimum Obstacle Removal to Reach Corner | 0-1 BFS | O(rows·cols) / O(rows·cols) |
| [2662](https://leetcode.com/problems/minimum-cost-of-a-path-with-special-roads/) | Minimum Cost of a Path With Special Roads | Dijkstra over collapsed interesting-point graph | O(m² log m) / O(m²) |
| [2577](https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/) | Minimum Time to Visit a Cell in a Grid | Dijkstra + parity-aware wait adjustment | O(rc log(rc)) / O(rc) |
| [2699](https://leetcode.com/problems/modify-graph-edge-weights/) | Modify Graph Edge Weights | Dual Dijkstra with on-the-fly edge assignment | O(E log V) / O(V+E) |

### 24. Bellman-Ford Algorithm

Cheapest Flights Within K Stops (787) is also listed under this section but is already solved in 13-graph-dp — not duplicated.

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [1129](https://leetcode.com/problems/shortest-path-with-alternating-colors/) | Shortest Path with Alternating Colors | Multi-round BFS over (node, lastColor) | O(V+E) / O(V+E) |

### 25. Floyd-Warshall Algorithm

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [1334](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/) | Find the City With the Smallest Number of Neighbors at a Threshold Distance | Floyd-Warshall | O(n³) / O(n²) |
| [2642](https://leetcode.com/problems/design-graph-with-shortest-path-calculator/) | Design Graph With Shortest Path Calculator | Floyd-Warshall + incremental edge-insert relaxation | O(n²)/addEdge, O(1)/query / O(n²) |
| [2959](https://leetcode.com/problems/number-of-possible-sets-of-closing-branches/) | Number of Possible Sets of Closing Branches | Subset enumeration + Floyd-Warshall per subset | O(2ⁿ·n³) / O(n²) |
| [2976](https://leetcode.com/problems/minimum-cost-to-convert-string-i/) | Minimum Cost to Convert String I | Floyd-Warshall over the 26-letter alphabet | O(26³+n) / O(26²) |
| [2977](https://leetcode.com/problems/minimum-cost-to-convert-string-ii/) | Minimum Cost to Convert String II | Floyd-Warshall over substring nodes + position DP | O(m³+n·lengths) / O(m²+n) |
| [3015](https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-i/) | Count the Number of Houses at a Certain Distance I | BFS per node (cycle + chord graph) | O(n²) / O(n) |

### 26. Cycle Detection

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [1857](https://leetcode.com/problems/largest-color-value-in-a-directed-graph/) | Largest Color Value in a Directed Graph | Kahn's algorithm (cycle check) + color-count propagation | O(V+E) / O(V·26) |
| [2127](https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/) | Maximum Employees to Be Invited to a Meeting | Functional-graph peeling + cycle analysis | O(n) / O(n) |
| [2359](https://leetcode.com/problems/find-closest-node-to-given-two-nodes/) | Find Closest Node to Given Two Nodes | Functional-graph chain walk | O(n) / O(n) |
| [2360](https://leetcode.com/problems/longest-cycle-in-a-graph/) | Longest Cycle in a Graph | Functional-graph visit-time walk | O(n) / O(n) |
| [2608](https://leetcode.com/problems/shortest-cycle-in-a-graph/) | Shortest Cycle in a Graph | BFS per node + cross-edge closure | O(V·(V+E)) / O(V+E) |
| [2876](https://leetcode.com/problems/count-visited-nodes-in-a-directed-graph/) | Count Visited Nodes in a Directed Graph | Functional-graph walk with memoized resolution | O(n) / O(n) |

### 27. Minimum Spanning Tree — Kruskal's Algorithm

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [1489](https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/) | Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree | Kruskal's run 3 ways per edge (baseline, excluded, forced) | O(E² · α(V)) / O(V+E) |
| [1584](https://leetcode.com/problems/min-cost-to-connect-all-points/) | Min Cost to Connect All Points | Kruskal's over all pairwise Manhattan-distance edges | O(n² log n) / O(n²) |

## Part III — Rare/Advanced Topics of Graph

### 28. Euler Tour

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [332](https://leetcode.com/problems/reconstruct-itinerary/) | Reconstruct Itinerary | Hierholzer's algorithm, per-node min-heap for lexical order | O(E log E) / O(E) |
| [2097](https://leetcode.com/problems/valid-arrangement-of-pairs/) | Valid Arrangement of Pairs | Hierholzer's algorithm, start node = out-degree − in-degree = 1 | O(E) / O(E) |

### 29. De Bruijn Sequence

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [753](https://leetcode.com/problems/cracking-the-safe/) | Cracking the Safe | Implicit De Bruijn graph + Hierholzer's algorithm (post-order DFS) | O(k^n) / O(k^n) |

### 30. Game on Graph

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [913](https://leetcode.com/problems/cat-and-mouse/) | Cat and Mouse | Zermelo's algorithm — retrograde BFS from terminal states | O(n³) / O(n²) |
| [1728](https://leetcode.com/problems/cat-and-mouse-ii/) | Cat and Mouse II | Minimax with memoization, turn-count-capped state space | O(rows²·cols²·turnCap·jump) / O(rows²·cols²·turnCap) |

### 31. Graph Cloning

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [133](https://leetcode.com/problems/clone-graph/) | Clone Graph | DFS with an original→clone HashMap to handle cycles | O(V+E) / O(V) |

### 32. Construction

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [2242](https://leetcode.com/problems/maximum-score-of-a-node-sequence/) | Maximum Score of a Node Sequence | Per-node top-3 neighbor precomputation + per-edge wing combination | O(V+E) / O(V) |
| [765](https://leetcode.com/problems/couples-holding-hands/) | Couples Holding Hands | Union-Find over couples; swaps = Σ(component size − 1) | O(n·α(n)) / O(n) |

### 33. Tarjan's Algorithm

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [1192](https://leetcode.com/problems/critical-connections-in-a-network/) | Critical Connections in a Network | Tarjan's bridge-finding via disc/low-link values (iterative DFS) | O(V+E) / O(V+E) |

*Note: [Shortest Path Visiting All Nodes (847)](https://leetcode.com/problems/shortest-path-visiting-all-nodes/) also appears under "Hamilton Cycle/TSP" in this list but is already solved in `05-bitmask-dp` — not duplicated.*

### 34. DP Applications

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [2065](https://leetcode.com/problems/maximum-path-quality-of-a-graph/) | Maximum Path Quality of a Graph | DFS + backtracking *(path-dependent visited set, not a DP recursion)* | O(4^(maxTime/minEdgeTime)) / O(n) |
| [1494](https://leetcode.com/problems/parallel-courses-ii/) | Parallel Courses II | Bitmask DP, submask enumeration of the available-courses set | O(3ⁿ) / O(2ⁿ) |
| [1042](https://leetcode.com/problems/flower-planting-with-no-adjacent/) | Flower Planting With No Adjacent | Greedy graph coloring *(no optimization objective, not a DP fit)* | O(V+E) / O(V+E) |
| [851](https://leetcode.com/problems/loud-and-rich/) | Loud and Rich | Memoized DFS over the "richer than" DAG | O(V+E) / O(V+E) |
| [329](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) | Longest Increasing Path in a Matrix | Memoized DFS, dp[cell] = longest increasing path from cell | O(rows·cols) / O(rows·cols) |
| [2328](https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/) | Number of Increasing Paths in a Grid | Memoized DFS counting twin of 329, mod 1e9+7 | O(rows·cols) / O(rows·cols) |

### 35. Ad-Hoc

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [1719](https://leetcode.com/problems/number-of-ways-to-reconstruct-a-tree/) | Number of Ways to Reconstruct a Tree | Ancestor/descendant-set analysis, minimal-superset parent selection | O(n²) / O(n²) |
| [2493](https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/) | Divide Nodes Into the Maximum Number of Groups | Bipartite check + double-BFS diameter per component | O(V+E) / O(V+E) |
| [3017](https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-ii/) | Count the Number of Houses at a Certain Distance II | O(n) difference array over path + one shortcut edge *(listed as "3016" in the source list, but its real LeetCode number is 3017)* | O(n) / O(n) |

# Two Pointers

## Part I — Running From Both Ends of an Array

### 36. 2 Sum Problem

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [167](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) | Two Sum II - Input Array Is Sorted | Converging two pointers on the sorted array | O(n) / O(1) |
| [15](https://leetcode.com/problems/3sum/) | 3Sum | Sort + fix one index + converging two pointers | O(n²) / O(n) |
| [18](https://leetcode.com/problems/4sum/) | 4Sum | Sort + fix two indices + converging two pointers | O(n³) / O(n) |
| [1498](https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/) | Number of Subsequences That Satisfy the Given Sum Condition | Converging two pointers + 2^(window) counting, mod 1e9+7 | O(n log n) / O(n) |
| [653](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/) | Two Sum IV - Input is a BST | In-order traversal to sorted list + converging two pointers | O(n) / O(n) |
| [633](https://leetcode.com/problems/sum-of-square-numbers/) | Sum of Square Numbers | Converging two pointers over [0, √c] | O(√c) / O(1) |
| [881](https://leetcode.com/problems/boats-to-save-people/) | Boats to Save People | Sort + greedy converging two pointers | O(n log n) / O(1) |
| [1877](https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/) | Minimize Maximum Pair Sum in Array | Sort + pair extremes (converging two pointers) | O(n log n) / O(1) |
| [923](https://leetcode.com/problems/3sum-with-multiplicity/) | 3Sum With Multiplicity | Sort + fix one index + converging two pointers with run-length counting | O(n²) / O(1) |

### 37. Trapping Water

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [42](https://leetcode.com/problems/trapping-rain-water/) | Trapping Rain Water | Converging two pointers tracking left/right running max | O(n) / O(1) |
| [11](https://leetcode.com/problems/container-with-most-water/) | Container With Most Water | Converging two pointers, always advance the shorter wall | O(n) / O(1) |

### 38. Next Permutation

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [31](https://leetcode.com/problems/next-permutation/) | Next Permutation | Pivot scan + tail swap + two-pointer reverse | O(n) / O(1) |
| [556](https://leetcode.com/problems/next-greater-element-iii/) | Next Greater Element III | Next-permutation algorithm on digits | O(d) / O(d) |
| [1850](https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/) | Minimum Adjacent Swaps to Reach the Kth Smallest Number | Next-permutation ×k + greedy adjacent-swap counting *(listed as "1830" in the source list, but its real LeetCode number is 1850)* | O(k·n + n²) / O(n) |

### 39. Reversing / Swapping

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [125](https://leetcode.com/problems/valid-palindrome/) | Valid Palindrome | Converging two pointers, skipping non-alphanumerics | O(n) / O(1) |
| [344](https://leetcode.com/problems/reverse-string/) | Reverse String | Converging two-pointer in-place swap | O(n) / O(1) |
| [345](https://leetcode.com/problems/reverse-vowels-of-a-string/) | Reverse Vowels of a String | Converging two pointers, swap only vowel pairs | O(n) / O(n) |
| [680](https://leetcode.com/problems/valid-palindrome-ii/) | Valid Palindrome II | Converging two pointers; on mismatch, try skipping either side | O(n) / O(1) |
| [917](https://leetcode.com/problems/reverse-only-letters/) | Reverse Only Letters | Converging two pointers, swap only letter pairs | O(n) / O(n) |
| [27](https://leetcode.com/problems/remove-element/) | Remove Element | Converging two pointers, swap-to-tail on match | O(n) / O(1) |
| [75](https://leetcode.com/problems/sort-colors/) | Sort Colors | Dutch National Flag (converging low/high + scanning mid) | O(n) / O(1) |
| [832](https://leetcode.com/problems/flipping-an-image/) | Flipping an Image | Converging two pointers, swap + invert in one pass per row | O(rows·cols) / O(1) |
| [977](https://leetcode.com/problems/squares-of-a-sorted-array/) | Squares of a Sorted Array | Converging two pointers, fill result back-to-front | O(n) / O(n) |
| [905](https://leetcode.com/problems/sort-array-by-parity/) | Sort Array By Parity | Converging two-pointer partition swap | O(n) / O(1) |
| [922](https://leetcode.com/problems/sort-array-by-parity-ii/) | Sort Array By Parity II | Two pointers stepping by 2 over even/odd index sets | O(n) / O(1) |
| [969](https://leetcode.com/problems/pancake-sorting/) | Pancake Sorting | Selection sort via two prefix flips (two-pointer reverse) per step | O(n²) / O(n) |
| [2000](https://leetcode.com/problems/reverse-prefix-of-word/) | Reverse Prefix of Word | Two-pointer reverse of the prefix up to the first match | O(n) / O(n) |
| [541](https://leetcode.com/problems/reverse-string-ii/) | Reverse String II | Two-pointer reverse of the first k of every 2k block | O(n) / O(n) |
| [151](https://leetcode.com/problems/reverse-words-in-a-string/) | Reverse Words in a String | In-place clean pass + full reverse + per-word reverse | O(n) / O(n) |
| [557](https://leetcode.com/problems/reverse-words-in-a-string-iii/) | Reverse Words in a String III | Two-pointer reverse of each space-delimited word | O(n) / O(n) |

### 40. Others (Running From Both Ends)

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [948](https://leetcode.com/problems/bag-of-tokens/) | Bag of Tokens | Sort + greedy converging two pointers (play up / borrow down) | O(n log n) / O(1) |
| [942](https://leetcode.com/problems/di-string-match/) | DI String Match | Two pointers converging over the value range [0, n] | O(n) / O(n) |
| [1750](https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/) | Minimum Length of String After Deleting Similar Ends | Converging two pointers, strip whole matching runs | O(n) / O(1) |
| [1813](https://leetcode.com/problems/sentence-similarity-iii/) | Sentence Similarity III | Two converging scans: common prefix + common suffix | O(n) / O(n) |
| [658](https://leetcode.com/problems/find-k-closest-elements/) | Find K Closest Elements | Converging two pointers shrinking the full array to size k | O(n) / O(k) |
| [821](https://leetcode.com/problems/shortest-distance-to-a-character/) | Shortest Distance to a Character | Two sweeps from opposite ends *(sequential, not a single converging scan)* | O(n) / O(n) |

## Part II — Slow & Fast Pointers

### 41. Linked List Operations

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [141](https://leetcode.com/problems/linked-list-cycle/) | Linked List Cycle | Floyd's slow/fast pointer cycle detection | O(n) / O(1) |
| [142](https://leetcode.com/problems/linked-list-cycle-ii/) | Linked List Cycle II | Floyd's algorithm + reset-and-meet cycle-start walk | O(n) / O(1) |
| [19](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | Remove Nth Node From End of List | Fast/slow pointers with an n-node head start | O(n) / O(1) |
| [61](https://leetcode.com/problems/rotate-list/) | Rotate List | Length count + circular-link + new-tail walk | O(n) / O(1) |
| [143](https://leetcode.com/problems/reorder-list/) | Reorder List | Slow/fast midpoint + in-place reverse + alternating merge | O(n) / O(1) |
| [234](https://leetcode.com/problems/palindrome-linked-list/) | Palindrome Linked List | Slow/fast midpoint + in-place reverse + lockstep compare | O(n) / O(1) |

### 42. Cyclic Detection

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [287](https://leetcode.com/problems/find-the-duplicate-number/) | Find the Duplicate Number | Floyd's algorithm over the implicit index→value functional graph | O(n) / O(1) |
| [457](https://leetcode.com/problems/circular-array-loop/) | Circular Array Loop | Slow/fast pointers per direction-consistent start, self-loop guard | O(n) / O(1) |

### 43. Sliding Window / Caterpillar Method

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [795](https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/) | Number of Subarrays with Bounded Maximum | Caterpillar window-length counting, inclusion-exclusion on bound | O(n) / O(1) |
| [719](https://leetcode.com/problems/find-k-th-smallest-pair-distance/) | Find K-th Smallest Pair Distance | Binary search on distance + caterpillar pair-count check | O(n log n + n log(maxDist)) / O(1) |
| [1040](https://leetcode.com/problems/moving-stones-until-consecutive-ii/) | Moving Stones Until Consecutive II | Sort + caterpillar window of width n for min moves; closed-form max | O(n log n) / O(1) |
| [1782](https://leetcode.com/problems/count-pairs-of-nodes/) | Count Pairs of Nodes | Caterpillar pair-sum counting on degrees + multi-edge range correction | O((n+E) log(n+E) + Q log E) / O(n+E) |
| [696](https://leetcode.com/problems/count-binary-substrings/) | Count Binary Substrings | Caterpillar run-length scan | O(n) / O(1) |
| [532](https://leetcode.com/problems/k-diff-pairs-in-an-array/) | K-diff Pairs in an Array | Sort + caterpillar two-pointer crawl with duplicate skipping | O(n log n) / O(1) |

### 44. Rotation

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [1861](https://leetcode.com/problems/rotating-the-box/) | Rotating the Box | Per-row settle scan (write-position two-pointer) + index-mapped rotation | O(rows·cols) / O(rows·cols) |
| [189](https://leetcode.com/problems/rotate-array/) | Rotate Array | Three-reversal trick (whole array, then each half) | O(n) / O(1) |

### 45. String

| # | Problem | Technique | Time / Space |
|---|---------|-----------|---------------|
| [443](https://leetcode.com/problems/string-compression/) | String Compression | Slow/fast pointers, run-length write in place | O(n) / O(1) |
| [899](https://leetcode.com/problems/last-substring-in-lexicographical-order/) | Last Substring in Lexicographical Order | Two competing suffix pointers with a match-offset counter | O(n) / O(1) |
