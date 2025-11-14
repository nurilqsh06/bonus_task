# Knuth-Morris-Pratt (KMP) Algorithm: Implementation and Analysis

## 1. Introduction

This report documents the implementation and analysis of the **Knuth-Morris-Pratt (KMP) string-searching algorithm** in Java. The KMP algorithm efficiently finds all occurrences of a **pattern** string $P$ (length $M$) within a larger **text** string $T$ (length $N$). Its primary advantage over naive searching is achieving an optimal linear time complexity of $O(N+M)$ by utilizing a pre-computed array to intelligently shift the pattern upon a mismatch.

---

## 2. Implementation Summary

The implementation is contained within the `KnuthMorrisPratt.java` class.

### 2.1. Building the LPS Array (`buildLPSArray`)

The **LPS (Longest Proper Prefix which is also a Suffix) array** is the cornerstone of KMP. `LPS[i]` stores the length of the longest proper prefix of the pattern substring $P[0 \dots i]$ that is also a suffix of that substring.

| Method | Description |
| :--- | :--- |
| `private static int[] buildLPSArray(String pattern)` | Pre-processes the pattern to compute the LPS array, enabling efficient pattern shifting during the search. |

**Example (Pattern: `ABABCABAB`):**

| Index (i) | Character | LPS Value | Rationale |
| :--- | :--- | :--- | :--- |
| 0 | A | 0 | (Always 0 for the first char) |
| 1 | B | 0 | |
| 2 | A | 1 | Longest prefix "A" is suffix "A" |
| 3 | B | 2 | Longest prefix "AB" is suffix "AB" |
| 4 | C | 0 | |
| 5 | A | 1 | Longest prefix "A" is suffix "A" |
| 6 | B | 2 | Longest prefix "AB" is suffix "AB" |
| 7 | A | 3 | Longest prefix "ABA" is suffix "ABA" |
| 8 | B | 4 | Longest prefix "ABAB" is suffix "ABAB" |

The resulting LPS array is `[0, 0, 1, 2, 0, 1, 2, 3, 4]`.

### 2.2. KMP Search (`KMPSearch`)

The main search function uses two pointers: `i` for the text and `j` for the pattern.

| Pointers | Purpose |
| :--- | :--- |
| `i` | Current index in the **Text** ($T$) |
| `j` | Current index in the **Pattern** ($P$) |

**Mismatch Handling:**
When $T[i] \neq P[j]$, the algorithm avoids backtracking $i$. Instead, it uses the LPS array to calculate the maximum safe shift for the pattern index $j$:
```java
// If a mismatch occurs after j characters have matched
else if (i < n && pattern.charAt(j) != text.charAt(i)) {
    if (j != 0) {
        j = lps[j - 1]; // Shift the pattern using LPS value
    } else {
        i++; // No shift possible, advance text pointer
    }
}
