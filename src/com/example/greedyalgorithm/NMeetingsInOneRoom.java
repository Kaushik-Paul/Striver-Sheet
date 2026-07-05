package com.example.greedyalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Problem: N Meetings in One Room
 *
 * Given one meeting room and N meetings, each represented by a start
 * time and an end time, determine the maximum number of meetings that
 * can be accommodated in the room if only one meeting can be held at
 * a time. A meeting can be scheduled if its start time is strictly
 * greater than the end time of the previously selected meeting.
 *
 * Example 1:
 *   Input:  start = [1, 3, 0, 5, 8, 5],  end = [2, 4, 6, 7, 9, 9]
 *   Output: 4
 *   Explanation: The meetings (1,2), (3,4), (5,7), (8,9) can all be
 *   scheduled without overlap.
 *
 * Example 2:
 *   Input:  start = [10, 12, 20],  end = [20, 25, 30]
 *   Output: 1
 *   Explanation: Every pair of meetings overlaps, so at most one can
 *   be chosen.
 *
 * Constraints:
 *   1 <= N <= 10^5
 *   0 <= start[i] < end[i] <= 10^5
 */
public class NMeetingsInOneRoom {

    static class MeetingComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return Integer.compare(a[1], b[1]);
        }
    }

    /**
     * Returns the maximum number of non-overlapping meetings that can
     * be scheduled in a single room.
     *
     * Algorithm (greedy by earliest finish time):
     *   - Pair each start and end time, then sort all meetings by their
     *     end time in ascending order.
     *   - Greedily pick the meeting that finishes earliest, then repeatedly
     *     pick the next meeting whose start time is strictly after the
     *     end time of the last picked meeting.
     *   - This greedy choice is optimal because picking the earliest-
     *     finishing meeting always leaves the maximum remaining time
     *     window for the rest.
     *
     * Time complexity:  O(N log N)  (dominated by sorting)
     * Space complexity: O(N)       (list of meeting pairs)
     */
    public int maxMeetings(int[] start, int[] end) {
        int n = start.length;

        List<int[]> meetings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            meetings.add(new int[] {start[i], end[i]});
        }

        // Sort by end time so the meeting that frees the room earliest is considered first.
        Collections.sort(meetings, new MeetingComparator());

        int lastEndTime = meetings.get(0)[1];
        int totalMeet = 1;

        // Greedily pick every meeting whose start time is strictly after the last end time.
        for (int i = 1; i < n; i++) {
            int currentStartTime = meetings.get(i)[0];

            if (currentStartTime > lastEndTime) {
                lastEndTime = meetings.get(i)[1];
                totalMeet++;
            }
        }

        return totalMeet;
    }

    public static void main(String[] args) {
        NMeetingsInOneRoom solution = new NMeetingsInOneRoom();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - 6 meetings, 4 can fit",
                new int[]{1, 3, 0, 5, 8, 5},
                new int[]{2, 4, 6, 7, 9, 9},
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - all overlapping, only 1 fits",
                new int[]{10, 12, 20},
                new int[]{20, 25, 30},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Now your turn example - 4 meetings all fit back-to-back",
                new int[]{1, 4, 6, 9},
                new int[]{2, 5, 7, 12},
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All non-overlapping with gaps - every meeting fits",
                new int[]{1, 3, 5, 7},
                new int[]{2, 4, 6, 8},
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single meeting - trivially fits",
                new int[]{5},
                new int[]{10},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All identical intervals - only one can be chosen",
                new int[]{1, 1, 1, 1},
                new int[]{5, 5, 5, 5},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Chain where every other meeting fits due to strict > condition",
                new int[]{1, 2, 3, 4, 5},
                new int[]{2, 3, 4, 5, 6},
                3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Two separate clusters of meetings with a large gap in between",
                new int[]{1, 2, 3, 4, 10, 11, 12},
                new int[]{2, 3, 4, 5, 11, 12, 13},
                4
        );

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
            NMeetingsInOneRoom solution,
            String testName,
            int[] start,
            int[] end,
            int expected
    ) {
        try {
            int actual = solution.maxMeetings(start, end);

            if (actual == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " | start=" + arrayToString(start)
                                + ", end=" + arrayToString(end)
                                + " -> result=" + actual
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Start:    " + arrayToString(start));
            System.out.println("End:      " + arrayToString(end));
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Start:     " + arrayToString(start));
            System.out.println("End:       " + arrayToString(end));
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

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
