import java.util.Arrays;
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;      // total number of spells
        int m = potions.length;     // total number of potions

        int[] result = new int[n];  // result[i] = successful potions for spell[i]

        // Step 1: Sort potions so binary search becomes possible
        Arrays.sort(potions);

        // Step 2: Process each spell independently
        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            /*
             We need:
                 spell * potion >= success

             Rearranging:
                 potion >= success / spell

             But division may not be exact, so we use CEIL.

             Example:
                 success = 16
                 spell = 3
                 16/3 = 5.33 → need potion >= 6

             Ceil formula:
                 ceil(a/b) = (a + b - 1) / b
            */
            long minPotionRequired = (success + spell - 1) / spell;
						//OR
						//long minPotionRequired = (long) Math.ceil((double) success / spell);          

            // Binary search to find first potion >= minPotionRequired
            int left = 0;
            int right = m - 1;

            // default index means no valid potion found
            int firstValidIndex = m;

            while (left <= right) {
                // safe mid calculation to avoid overflow
                int mid = left + (right - left) / 2;
                /*
                 If potion[mid] satisfies the condition,
                 it might be the first valid potion,
                 but we must check left side to confirm.
                */
                if (potions[mid] >= minPotionRequired) {
                    firstValidIndex = mid;     // store candidate answer
                    right = mid - 1;           // search left side
                } else {
                    // potion too small, move right
                    left = mid + 1;
                }
            }
            /*
             All potions from firstValidIndex → end will work.
             Example:
             potions = [5,8,8]
             firstValidIndex = 1
             valid potions = index 1,2
             count = m - firstValidIndex
            */
            result[i] = m - firstValidIndex;
        }
        return result;
    }
}
/*
Time Complexity
Sorting potions= O(m log m)
Binary search per spell = O(log m)
Total O(m log m + n log m)

Space Complexity O(1) extra space (excluding output array)

Important Interview Cross Questions

1. Why sort only potions and not spells?

Because each spell is independent.
We only need to know the minimum potion required for that spell.
Sorting potions lets us binary search the boundary.

Sorting spells gives no additional benefit for this approach.

2. Why do we compute `(success + spell - 1) / spell` ?

To perform ceiling division.

Example

success = 16
spell = 3

16 / 3 = 5.33

Potion must be ≥ 6.

Normal division gives 5, which is wrong.

Ceil formula:

(a + b − 1) / b

3. Why use `long` for success and multiplication?

Constraints can be:

spell ≤ 10^5
potion ≤ 10^5

Multiplication:

10^5 × 10^5 = 10^10

This exceeds int range (2.1e9).
So we must use `long`.

4. Why do we search for the FIRST valid potion?

Because potions are sorted.

Example:

potions = [2,4,6,8,10]

If index 2 works (6), then everything after it also works.

So

valid pairs = m − index

5. What happens if no potion works?

Binary search never finds a valid potion.

So

firstValidIndex = m

Then

result = m − m = 0

6. Why not compute multiplication inside binary search?

Instead of:

spell * potion ≥ success

We convert to:

potion ≥ success / spell

This avoids multiplication overflow and simplifies comparison.

7. Can we optimize further?

Yes.

Two-pointer approach:

* sort spells
* sort potions
* move pointer once

Time complexity

O(n log n + m log m)

Binary search approach is usually preferred in interviews because:

* simpler
* easier to implement
* less bug-prone

8. What if potions array is extremely large (10^7)?

Binary search still works efficiently because:

log2(10^7) ≈ 24

So only ~24 comparisons per spell.

9. Edge cases interviewer may test

spell = 1
success very large

all pairs successful

no pairs successful

duplicate potions

large values causing overflow

10. Follow-up interviewer question

"What if spells and potions both change frequently?"

Answer:

Sorting every time becomes expensive.

Better data structures:

* Balanced BST
* Fenwick tree
* Segment tree
* Maintain sorted potions with binary searchable structure.
*/