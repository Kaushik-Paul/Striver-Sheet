# Agent Guide for Striver's SDE Sheet

This document contains instructions for AI agents working on this repository.
Read this file before making any changes.

---

## 1. Project Overview

- This repository contains Java solutions to **Striver's SDE Sheet** problems.
- Each problem has its own self-contained Java file under `src/com/example/<topic>/`.
- Every file must include:
  - A package declaration.
  - A class-level Javadoc describing the problem.
  - The solution method(s).
  - A `main` method with a test harness that prints `[PASS]`, `[FAIL]`, or `[ERROR]` and a final summary.
- The `README.md` is the human-facing summary and must be kept in sync.

---

## 2. Directory / Package Layout

```text
src/com/example/<topic>/<ProblemName>.java
```

Existing topics:

- `arrays1`
- `arrays2`
- `linkedlist1`
- `greedyalgorithm`
- `binarysearch`

If a new topic is needed, create a new package under `com.example`.

---

## 3. Code Conventions

### 3.1 File Template

```java
package com.example.<topic>;

/**
 * Problem: <Problem title>
 *
 * <Short description of the problem.>
 *
 * Example 1:
 *   Input:  <input>
 *   Output: <output>
 *   Explanation: <explanation>
 *
 * Constraints:
 *   <constraints>
 */
public class <ProblemName> {

    /**
     * <Brief description of the approach.>
     *
     * Time complexity:  <...>
     * Space complexity: <...>
     */
    public <ReturnType> <methodName>(<params>) {
        // solution
    }

    public static void main(String[] args) {
        <ProblemName> solution = new <ProblemName>();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "<descriptive test name>",
                <input args>,
                <expected>
        );

        // ... more tests

        System.out.println();
        System.out.println("========================================");
        System.out.println(
                "Test summary: " + passedTests + "/" + totalTests + " passed"
        );
        System.out.println("========================================");

        if (passedTests != totalTests) {
            throw new AssertionError(
                    (totalTests - passedTests) + " test(s) failed."
            );
        }
    }

    private static int runTest(
            <ProblemName> solution,
            String testName,
            <params>,
            <expected param>
    ) {
        try {
            <actual> = solution.<methodName>(<args>);

            if (/* actual equals expected */) {
                System.out.println("[PASS] " + testName);
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println(
                    "Exception: "
                            + exception.getClass().getSimpleName()
                            + ": "
                            + exception.getMessage()
            );
            System.out.println();
            return 0;
        }
    }
}
```

### 3.2 Formatting Rules

- Use 4 spaces for indentation.
- Opening brace on the same line.
- Keep lines within 100 characters when reasonable.

### 3.3 User Solution Integrity

The solution the user wrote must **not** be changed: do not rewrite, refactor, restyle, or alter the algorithm, control flow, data structures, or variable names unless the user explicitly asks.

You **may** add comments inside the user's solution, but only at important places, and only when a comment is required and genuinely helpful (for example, a non-obvious invariant, a tricky merge/overlap condition, or why a greedy choice is valid). Do not add comments that restate the code. Do not over-comment.

---

## 4. When the User Adds a New Problem / Solution

This workflow applies whenever the user indicates they have written, pasted, or added a solution file, even if they do not say "I have added this problem" word-for-word. Phrases like "I have added another solution", "I added this solution", "added the problem", or mentioning a newly created file under `src/com/example/<topic>/` should all trigger this workflow.

### Required Changes

1. **Add / complete the test harness in the Java file**
   - If the file already has a `main` method, make sure it has 5-6 good edge cases.
   - If the file does not have a `main` method, add one following the template above.
   - Include:
     - The example from the problem statement.
     - Edge cases (e.g., single element, empty input, all same values, negative numbers, large sizes).
   - Ensure tests print `[PASS]`, `[FAIL]`, or `[ERROR]` and a final summary.
   - Throw `AssertionError` if any test fails.

2. **Update `README.md`**
   - Add the problem to the correct topic section.
   - Use this table row format:
     ```
     | # | <Problem title> | [<ProblemName>.java](src/com/example/<topic>/<ProblemName>.java) | <complexity> |
     ```
   - If the topic section does not exist, create a new `### <Topic>` heading.
   - Update the project structure tree to include the new package/file.

3. **Do NOT modify the user's solution unless asked**
   - Leave the user's algorithm, logic, and style unchanged (see §3.3).
   - You may add comments only at important places, and only if they are required and helpful.
   - Do not refactor code style without permission.

### Example Workflow

User: "I have added the problem in `src/com/example/arrays2/RotateArraysBy90.java`."

Agent should:

1. Read the file.
2. Check if it has a `main` method. If missing, add a test harness.
3. Add 5-6 edge cases to the test harness.
4. Update `README.md` with the new problem entry and project structure.
5. Compile and run the file to verify all tests pass.

---

## 5. How to Run

```bash
# Compile
javac -d out src/com/example/<topic>/<ProblemName>.java

# Run
java -cp out com.example.<topic>.<ProblemName>
```

Always compile and run the file after making changes.

---

## 6. Things to Avoid

- Do not change the user's solution without explicit permission (see §3.3).
- Do not add comments unless they are at an important place and are required and helpful. Do not add unnecessary documentation.
- Do not commit or push unless explicitly asked.
- Do not delete or move existing files without permission.
- Do not add emojis to code or documentation.

---

## 7. Contact / Help

If the user's request is ambiguous, ask for clarification before making changes.
