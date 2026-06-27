# Striver's SDE Sheet

My solutions to [Striver's SDE Sheet](https://takeuforward.org/dsa/strivers-sde-sheet-top-coding-interview-problems) — a curated set of **191** top coding interview problems spanning Data Structures & Algorithms, asked at companies like Google, Amazon, Microsoft, Facebook, Swiggy and Flipkart.

Solutions are written in Java, each self-contained with its own test harness.

## Solved Problems

### Arrays

| # | Problem             | Solution                                                                 | Complexity                          |
|---|---------------------|--------------------------------------------------------------------------|-------------------------------------|
| 1 | Set Matrix Zeroes   | [SetMatrixZero.java](src/com/example/arrays/SetMatrixZero.java)         | O(m·n) time, O(1) space             |
| 2 | Pascal's Triangle I | [PascalTriangle1.java](src/com/example/arrays/PascalTriangle1.java)     | O(min(c, r−c)) time, O(1) space     |

## Project Structure

```
src/
└── com/example/
    └── arrays/
        ├── SetMatrixZero.java
        └── PascalTriangle1.java
```

Each problem lives in `src/com/example/<topic>/<ProblemName>.java`, where `<topic>` matches the sheet's section (e.g. `arrays`, `linkedlist`, `recursion`, `graph`, ...). New topics are added as new packages under `com.example`.

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
javac -d out src/com/example/arrays/SetMatrixZero.java

# Run
java -cp out com.example.arrays.SetMatrixZero
```

Alternatively, open the project in IntelliJ IDEA and run the `main` method of any problem file directly.
