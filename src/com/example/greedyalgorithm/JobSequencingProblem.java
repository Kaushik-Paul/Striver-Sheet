package com.example.greedyalgorithm;

import java.util.Arrays;

/**
 * Problem: Job Sequencing Problem
 *
 * Given N jobs where each job has a deadline and an associated profit,
 * schedule the jobs so that the total profit is maximised. Each job
 * takes exactly one unit of time to complete, and a job can only be
 * scheduled if it is finished on or before its deadline. Only one job
 * can be performed at any given time slot.
 *
 * Return an array where the first element is the count of jobs
 * scheduled and the second element is the maximum profit earned.
 *
 * Example 1:
 *   Input:  Jobs = [[1,4,20], [2,1,10], [3,1,40], [4,1,30]]
 *   Output: [2, 60]
 *   Explanation: Job 3 (profit 40) is scheduled at slot 0 and
 *   job 1 (profit 20) at slot 3, giving 2 jobs and profit 60.
 *
 * Example 2:
 *   Input:  Jobs = [[1,2,100], [2,1,50], [3,2,10], [4,1,20], [5,3,30]]
 *   Output: [3, 180]
 *   Explanation: Jobs 1, 2 and 5 are scheduled, earning profit 180.
 *
 * Constraints:
 *   1 <= N <= 10^5
 *   1 <= deadline, profit <= 10^5
 */
public class JobSequencingProblem {

    /**
     * Returns an array {count of jobs scheduled, maximum profit} for the
     * given list of jobs.
     *
     * Algorithm (greedy by profit with latest-slot scheduling):
     *   - Sort all jobs in descending order of profit so the most
     *     rewarding job gets the first chance at a slot.
     *   - For each job, try to place it in the latest available slot
     *     before its deadline. Scheduling as late as possible keeps the
     *     earlier slots free for jobs with tighter deadlines.
     *   - A slot is "available" if no job has been assigned to it yet.
     *
     * Time complexity:  O(N log N + N * D) where D is the max deadline
     * Space complexity: O(D)
     */
    public int[] JobScheduling(int[][] Jobs) {
        // Sort the array based on max profit so high-profit jobs are placed first.
        Arrays.sort(Jobs, (a, b) -> b[2] - a[2]);

        int maxProfit = 0;
        int numberOfJobs = 0;
        int maxDeadline = -1;

        // Find the maximum deadline to size the schedule array.
        for (int[] job: Jobs) {
            maxDeadline = Math.max(job[1], maxDeadline);
        }

        // jobsSchedule[i] holds the id of the job assigned to slot i (-1 = free).
        int[] jobsSchedule = new int[maxDeadline];
        Arrays.fill(jobsSchedule, -1);

        for (int i = 0; i < Jobs.length; i++) {

            // Try the latest free slot at or before this job's deadline.
            for (int j = Jobs[i][1] - 1; j >= 0; j--) {
                if (jobsSchedule[j] == -1) {
                    jobsSchedule[j] = Jobs[i][0];
                    maxProfit += Jobs[i][2];
                    numberOfJobs++;
                    break;
                }
            }
        }

        return new int[] {numberOfJobs, maxProfit};
    }

    public static void main(String[] args) {
        JobSequencingProblem solution = new JobSequencingProblem();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - 2 jobs, profit 60",
                new int[][]{{1, 4, 20}, {2, 1, 10}, {3, 1, 40}, {4, 1, 30}},
                new int[]{2, 60}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - 3 jobs, profit 180",
                new int[][]{{1, 2, 100}, {2, 1, 50}, {3, 2, 10}, {4, 1, 20}, {5, 3, 30}},
                new int[]{3, 180}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single job - always schedulable within its deadline",
                new int[][]{{1, 1, 50}},
                new int[]{1, 50}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All jobs share deadline 1 - only the highest-profit job fits",
                new int[][]{{1, 1, 10}, {2, 1, 30}, {3, 1, 20}},
                new int[]{1, 30}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Distinct deadlines - every job can be scheduled",
                new int[][]{{1, 1, 10}, {2, 2, 20}, {3, 3, 30}},
                new int[]{3, 60}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Same deadline for all - pick the top two profit jobs",
                new int[][]{{1, 2, 100}, {2, 2, 50}, {3, 2, 25}},
                new int[]{2, 150}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Deadline larger than job count - all jobs fit",
                new int[][]{{1, 5, 40}, {2, 5, 30}},
                new int[]{2, 70}
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All jobs have equal profit - schedule by deadline availability",
                new int[][]{{1, 3, 50}, {2, 2, 50}, {3, 1, 50}},
                new int[]{3, 150}
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
            JobSequencingProblem solution,
            String testName,
            int[][] jobs,
            int[] expected
    ) {
        try {
            int[] actual = solution.JobScheduling(jobs);

            if (Arrays.equals(actual, expected)) {
                System.out.println(
                        "[PASS] " + testName
                                + " | jobs=" + jobsToString(jobs)
                                + " -> result=" + arrayToString(actual)
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Jobs:    " + jobsToString(jobs));
            System.out.println("Expected: " + arrayToString(expected));
            System.out.println("Actual:   " + arrayToString(actual));
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Jobs:    " + jobsToString(jobs));
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

    private static String jobsToString(int[][] jobs) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < jobs.length; i++) {
            sb.append("(");
            for (int j = 0; j < jobs[i].length; j++) {
                sb.append(jobs[i][j]);
                if (j < jobs[i].length - 1) {
                    sb.append(",");
                }
            }
            sb.append(")");
            if (i < jobs.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
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