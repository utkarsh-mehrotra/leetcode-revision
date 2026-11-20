# Retro Study Desk: the Revision Hub redesign

This replaces the Nintendo.com (2001) "console chrome" look (`DESIGN-nintendo-2001.md`) with a design built around the hub's actual job, which is **revising** solutions rather than only browsing them.

## 1. What the previous design got wrong for revision

| Area | Problem |
|---|---|
| Layout | Three fixed columns (categories 240px, problem list 340px, code 1fr) left the code under 60% of the width on a 1440px screen. The category sidebar and the list duplicated each other. |
| Revision | It was a reader with no recall loop: the approach and code were always visible, so every visit was passive re-reading. Nothing recorded what you had revised or what was due. |
| Hierarchy | The Approach and Time badges were cut off after the first line of the Javadoc. For 176 solutions, those with imports above the Javadoc, the approach was missing entirely and the Javadoc stayed inside the code. |
| Theme | Low contrast: dark ink on periwinkle `#7a8aba`, 10–11px uppercase labels everywhere, bevel borders on every element, and outlined Arial Black titles that were hard to read. |
| Chrome | The persistent promo banner and stat chips took space and gave you nothing to do. Dark mode came from loading a second highlight.js theme (Monokai), so the code colours never matched the UI. |
| Data | Titles were capitalised mechanically ("Stock Iii", "Buy And Sell"), and 474 rows of static HTML repeated the embedded `PROBLEMS` data. |

## 2. Direction: "Retro Study Desk"

The look stays retro but borrows from a study desk instead of a game console: graph paper, index cards and early Mac OS windows.

- **Desk:** a warm dotted graph-paper background.
- **Sheets:** cream index-card panels with hard 2px ink borders and offset "printed" shadows (`4px 4px 0`) instead of soft blur.
- **Window chrome:** pinstriped classic-Mac title bars with a close box on every panel (`.win`, `.win-title`).
- **Type:**
  - **VT323** (pixel) for titles, numbers and counters
  - **IBM Plex Mono** for UI labels
  - **IBM Plex Sans** for prose
  - **JetBrains Mono** for code, with ligatures off so `<=` reads as `<=`

  Body prose is 15px, and the uppercase micro-type is used only for labels.
- **Buttons:** they physically press: the shadow collapses and the button moves 2px.

### Themes
All colours are tokens on `:root`, and the syntax colours are tokens too, so the code always matches the theme. `T` cycles the themes:

| Theme | Feel |
|---|---|
| **Paper** (default light) | cream paper, ink black, tomato accent |
| **Night** (default dark) | warm charcoal, cream ink, orange accent |
| **Phosphor** | green CRT terminal, amber accent |

The first visit follows the OS light/dark setting. A theme you pick by hand sticks.

### Status colours (used everywhere)
`New` hollow grey · `Learning` mustard · `Reviewing` blue · `Mastered` teal. Grade buttons use Again = tomato, Hard = mustard, Good = teal, Easy = blue.

## 3. Layout

```
┌ topbar: ☰ · LR logo ··········· [▶ Review N due] [Hide answers] [Theme] [?] [GitHub] ┐
├──────────── rail (320px) ────────────┬──────────────── reader ─────────────────────────┤
│ [search /]                           │  breadcrumb › category                          │
│ [All|DP|Graphs|2-Ptr|Trees]          │  #70  Climbing Stairs                           │
│ chips: All·Due·New·Learning·…        │  [status · due]          [LeetCode ↗] [←] [→]   │
│ ▸ 01 Linear DP      ▭▭▭  3/38        │  ┌ APPROACH ─────────────────────────────┐      │
│ ▾ 04 Interval DP    ▭▭▭  1/17        │  │ technique chip · insight · TIME SPACE │      │
│    ● 312 Burst Balloons     due      │  └───────────────────────────────────────┘      │
│    ○ 375 Guess Number…               │  ┌ SOLUTION.JAVA ─── Copy Wrap A− A+ ────┐      │
│                                      │  └───────────────────────────────────────┘      │
│                                      │  ┌ MY NOTES (lined paper) ───────────────┐      │
├──────────────────────────────────────┴─────────────────────────────────────────────────┤
│ How well did you recall it?               [Again 1] [Hard 2] [Good 3] [Easy 4]         │
└────────────────────────────────────────────────────────────────────────────────────────┘
```

- **One rail instead of two columns.** A collapsible category tree with a per-category mastery meter replaces both the sidebar and the list pane, so the reader gets about 75% of the width. The reading measure is capped at 980px.
- **Rail visibility.** `[` hides the rail entirely for focused reading. On phones the rail becomes a slide-over drawer, and the grade bar becomes four thumb-sized buttons.
- **Compare.** The ⧉ button on any row opens that problem side by side with the current one (the old compare feature is kept).

## 4. Revision features (implemented)

1. **Active recall ("Hide answers").** The approach and code start covered, with prompts: *which pattern? what's the state? recurrence/invariant? time & space?* `Space` reveals the approach, then the code.
2. **Spaced repetition.** After revealing, you grade your recall with `1`–`4`. A small SM-2 variant schedules the next review, and each grade button shows its resulting interval. Status (New / Learning / Reviewing / Mastered) is derived from the interval.
3. **Review sessions.** `R` builds a queue of everything due in the current scope (All / DP / Graphs / …) plus *N* new problems (configurable). A progress strip shows your position, and "Again" cards come back once more at the end. A summary screen follows.
4. **Dashboard.** It shows:
   - today's due count, reviews done today and your streak
   - continue where you left off, and a random cold-recall drill
   - overall and per-topic progress bars
   - **Weak spots**: problems you graded Again or Hard
   - **Coming up**: your next scheduled reviews
   - a 30-day activity heatmap
5. **Per-problem notes.** They autosave, and rows with notes get a ✎ mark.
6. **Filters and search.** Status chips (Due / New / Learning / Reviewing / Mastered) work together with the topic scope. Search matches number, title, category and technique, with highlighted matches. `Enter` opens the first hit.
7. **Clean approach card.** The Javadoc is parsed fully: the technique becomes a chip, the insight a paragraph, and Time/Space become labelled chips. It works whether the Javadoc was in `javadoc` or still inside `code`.
8. **Code tools.** Copy (`C`), line wrap, and font size A−/A+ (remembered).
9. **Keyboard-first.** `/` search · `J`/`K` next/prev · `Space` reveal · `1–4` grade · `R` review · `H` hide answers · `N` notes · `O` LeetCode · `G` home · `T` theme · `?` help.
10. **Your data stays yours.** Progress, notes and activity live in `localStorage`, and every access is guarded. You can export progress to a JSON file, import it, or reset it.
11. **Backwards compatible.** Old `#slug` links still open the right problem. The old "last viewed" key is migrated.

## 5. Ideas for later (not built yet)

- **Pattern drills.** Show only the approach and ask "which problem is this?", or show a problem and pick its technique from four options.
- **"Write it" mode.** An empty editor beside the hidden solution, then a diff against the reference.
- **Cross-device sync.** Sync progress through a GitHub Gist or a small Vercel KV store instead of export/import.
- **Similar problems.** Link problems by shared technique (e.g. all "interval DP on `best(lo, hi)`"), so you can revise a pattern as a set.
- **Generator script.** Generate the `PROBLEMS` blob from the `Solution.java` files with a build script, so adding a problem never means hand-editing `index.html`.
- **Print / PDF cheat sheet.** One line per problem (technique + complexity) per category, for last-minute revision.

## 6. Modern variant (branch `redesign/revision-desk-modern`)

The same layout and features with the retro styling replaced:

- **Type:** Inter for the UI, JetBrains Mono for code.
- **Surfaces:** neutral backgrounds, 1px borders, rounded corners (8–12px) and soft shadows. The pinstriped title bars become plain card headers.
- **Top bar:** frosted glass.
- **Themes:** Light (indigo accent), Dark, and Midnight (slate with a sky-blue accent). The syntax colours follow GitHub's light and dark palettes.

The theme tokens are at the top of `<style>`. A "MODERN SKIN" block at the end overrides the retro component styles, so you can compare the two branches directly.

On this branch, "Hide answers" and the step-by-step reveal are **removed**: the approach, complexity and code always appear together, review sessions included. Grading stays. During a review session it's a fixed bar at the bottom; while browsing it's a card under the code.
