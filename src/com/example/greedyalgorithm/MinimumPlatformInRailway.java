package com.example.greedyalgorithm;

/**
 * Problem: Minimum number of platforms required for a railway
 *
 * Given the arrival and departure times of all trains reaching a
 * particular railway station, determine the minimum number of
 * platforms required so that no train is kept waiting. All trains
 * arrive and depart on the same day.
 *
 * In any particular instance, the same platform cannot be used for
 * both the departure of one train and the arrival of another train.
 *
 * Example 1:
 *   Input:  arrival = [900, 940, 950, 1100, 1500, 1800],
 *           departure = [910, 1200, 1120, 1130, 1900, 2000]
 *   Output: 3
 *
 * Example 2:
 *   Input:  arrival = [900, 1100, 1235],  departure = [1000, 1200, 1240]
 *   Output: 1
 *   Explanation: All three trains can use the same platform.
 */
public class MinimumPlatformInRailway {

    /**
     * Returns the minimum number of platforms required so that every
     * train can be accommodated without waiting.
     *
     * Algorithm (brute-force):
     *   - For each train i, count how many other trains j are present
     *     at the station when train i arrives.
     *   - A train j is present at train i's arrival if train i arrives
     *     after or exactly when train j arrives, and train j departs
     *     after or exactly when train i arrives.
     *   - The maximum such count across all trains is the answer.
     *
     * Time complexity:  O(N^2)
     * Space complexity: O(1)
     */
    public int findPlatform(int[] Arrival, int[] Departure) {
        int numberOfTrains = Arrival.length;
        int numberOfPlatforms = 1;

        for (int i = 0; i < numberOfTrains; i++) {
            int currentCount = 1;

            for (int j = 0; j < numberOfTrains; j++) {
                if (i != j) {
                    // Train j is already at the station when train i arrives.
                    if (Arrival[i] >= Arrival[j] && Departure[j] >= Arrival[i]) {
                        currentCount++;
                    }
                }

                numberOfPlatforms = Math.max(numberOfPlatforms, currentCount);
            }
        }

        return numberOfPlatforms;
    }

    public static void main(String[] args) {
        MinimumPlatformInRailway solution = new MinimumPlatformInRailway();

        int passedTests = 0;
        int totalTests = 0;

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 1 from problem statement - 3 platforms needed",
                new int[]{900, 940, 950, 1100, 1500, 1800},
                new int[]{910, 1200, 1120, 1130, 1900, 2000},
                3
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Example 2 from problem statement - single platform is enough",
                new int[]{900, 1100, 1235},
                new int[]{1000, 1200, 1240},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Single train - only one platform needed",
                new int[]{900},
                new int[]{1000},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "All identical intervals - each train needs its own platform",
                new int[]{1000, 1000, 1000, 1000},
                new int[]{1100, 1100, 1100, 1100},
                4
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "No overlapping trains - one platform suffices",
                new int[]{900, 1000, 1100, 1200},
                new int[]{910, 1010, 1110, 1210},
                1
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Departure equals next arrival - different platforms required",
                new int[]{900, 1000, 1100},
                new int[]{1000, 1100, 1200},
                2
        );

        totalTests++;
        passedTests += runTest(
                solution,
                "Multiple trains arriving at the same time with staggered departures",
                new int[]{900, 900, 930, 1000},
                new int[]{930, 1000, 1100, 1200},
                3
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
            MinimumPlatformInRailway solution,
            String testName,
            int[] arrival,
            int[] departure,
            int expected
    ) {
        try {
            int actual = solution.findPlatform(arrival, departure);

            if (actual == expected) {
                System.out.println(
                        "[PASS] " + testName
                                + " | arrival=" + arrayToString(arrival)
                                + ", departure=" + arrayToString(departure)
                                + " -> result=" + actual
                );
                return 1;
            }

            System.out.println("[FAIL] " + testName);
            System.out.println("Arrival:   " + arrayToString(arrival));
            System.out.println("Departure: " + arrayToString(departure));
            System.out.println("Expected:  " + expected);
            System.out.println("Actual:    " + actual);
            System.out.println();
            return 0;
        } catch (Exception exception) {
            System.out.println("[ERROR] " + testName);
            System.out.println("Arrival:   " + arrayToString(arrival));
            System.out.println("Departure: " + arrayToString(departure));
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
