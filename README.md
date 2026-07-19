# Striver's SDE Sheet

My solutions to [Striver's SDE Sheet](https://takeuforward.org/dsa/strivers-sde-sheet-top-coding-interview-problems) — a curated set of **191** top coding interview problems spanning Data Structures & Algorithms, asked at companies like Google, Amazon, Microsoft, Facebook, Swiggy and Flipkart.

Solutions are written in Java, each self-contained with its own test harness.

## Solved Problems

### Arrays-1

| # | Problem                       | Solution                                                                         | Complexity                          |
|---|-------------------------------|----------------------------------------------------------------------------------|-------------------------------------|
| 1 | Set Matrix Zeroes             | [SetMatrixZero.java](src/com/example/arrays1/SetMatrixZero.java)                 | O(m·n) time, O(1) space             |
| 2 | Pascal's Triangle I           | [PascalTriangle1.java](src/com/example/arrays1/PascalTriangle1.java)             | O(min(c, r−c)) time, O(1) space     |
| 3 | Next Permutation              | [NextPermutation.java](src/com/example/arrays1/NextPermutation.java)             | O(n) time, O(1) space               |
| 4 | Kadane's Algorithm            | [KadensAlgorithm.java](src/com/example/arrays1/KadensAlgorithm.java)             | O(n) time, O(1) space               |
| 5 | Sort an array of 0's, 1's, 2's | [SortArrays0s1s2s.java](src/com/example/arrays1/SortArrays0s1s2s.java)           | O(n) time, O(1) space               |
| 6 | Best Time to Buy and Sell Stock | [BuyAndSellStock.java](src/com/example/arrays1/BuyAndSellStock.java)             | O(n) time, O(1) space               |

### LinkedList

| # | Problem                              | Solution                                                                                 | Complexity                  |
|---|--------------------------------------|------------------------------------------------------------------------------------------|-----------------------------|
| 1 | Reverse a Linked List                | [ReverseLinkedList.java](src/com/example/linkedlist1/ReverseLinkedList.java)               | O(n) time, O(1) space       |
| 2 | Middle of a Linked List              | [MiddleOfLinkedList.java](src/com/example/linkedlist1/MiddleOfLinkedList.java)             | O(n) time, O(1) space       |
| 3 | Merge Two Sorted Linked Lists        | [MergeLinkedList.java](src/com/example/linkedlist1/MergeLinkedList.java)                   | O(n + m) time, O(1) space   |
| 4 | Remove Nth Node From End of List     | [RemoveNthListFromBack.java](src/com/example/linkedlist1/RemoveNthListFromBack.java)       | O(n) time, O(1) space       |
| 5 | Delete Node in a Linked List         | [DeleteNodeInLinkedList.java](src/com/example/linkedlist1/DeleteNodeInLinkedList.java)     | O(1) time, O(1) space       |

### Greedy Algorithm

| # | Problem                       | Solution                                                                         | Complexity                          |
|---|-------------------------------|----------------------------------------------------------------------------------|-------------------------------------|
| 1 | N Meetings in One Room        | [NMeetingsInOneRoom.java](src/com/example/greedyalgorithm/NMeetingsInOneRoom.java) | O(N log N) time, O(N) space        |
| 2 | Minimum Platforms in Railway  | [MinimumPlatformInRailway.java](src/com/example/greedyalgorithm/MinimumPlatformInRailway.java) | O(N^2) time, O(1) space            |
| 3 | Job Sequencing Problem        | [JobSequencingProblem.java](src/com/example/greedyalgorithm/JobSequencingProblem.java) | O(N log N + N·D) time, O(D) space  |
| 4 | Fractional Knapsack            | [FractionalKnapsack.java](src/com/example/greedyalgorithm/FractionalKnapsack.java) | O(N log N) time, O(N) space        |

## Project Structure

```
src/
└── com/example/
    ├── arrays1/
    │   ├── SetMatrixZero.java
    │   ├── PascalTriangle1.java
    │   ├── NextPermutation.java
    │   ├── KadensAlgorithm.java
    │   ├── SortArrays0s1s2s.java
    │   └── BuyAndSellStock.java
    ├── linkedlist1/
    │   ├── ListNode.java
    │   ├── ReverseLinkedList.java
    │   ├── MiddleOfLinkedList.java
    │   ├── MergeLinkedList.java
    │   ├── RemoveNthListFromBack.java
    │   └── DeleteNodeInLinkedList.java
    └── greedyalgorithm/
        ├── NMeetingsInOneRoom.java
        ├── MinimumPlatformInRailway.java
        ├── JobSequencingProblem.java
        └── FractionalKnapsack.java
```

Each problem lives in `src/com/example/<topic>/<ProblemName>.java`, where `<topic>` matches the sheet's section (e.g. `arrays1`, `linkedlist1`, `recursion`, `graph`, ...). New topics are added as new packages under `com.example`.

## Conventions

Every solution file follows the same layout:

1. **Package declaration** under `com.example.<topic>`.
2. **Javadoc** describing the problem statement, an example, and the constraints.
3. **One or more solutions**, each with its time and space complexity documented in a Javadoc. Where applicable, a brute-force solution is included alongside the optimal one.
4. **A `main` method** that runs a suite of test cases, printing `[PASS]` / `[FAIL]` / `[ERROR]` for each case and a final `x/y tests passed` summary. The method throws an `AssertionError` if any test fails, so a non-zero exit code signals failure.

## How to Run

Each file is self-contained and can be run independently.

```bash
# Compile
javac -d out src/com/example/arrays1/SetMatrixZero.java

# Run
java -cp out com.example.arrays1.SetMatrixZero
```

Alternatively, open the project in IntelliJ IDEA and run the `main` method of any problem file directly.
