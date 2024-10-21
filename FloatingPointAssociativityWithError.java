public class FloatingPointAssociativityWithError {
    public static void main(String[] args) {
        // Array of test values for x, y, and z
        int[] testValues = {100000000, 0, 1, -10000000, -1000000000, 0, 12345, -9876};
        // Variables to accumulate count errors
        int errorCount = 0;
        int totalTests = 0;
        double percentageError = 0.0;
        // Run the test for all combinations of x, y, and z from the testValues array
        for (int x : testValues) {
            for (int y : testValues) {
                for (int z : testValues) {
                    totalTests++;
                    
                    // Calculate (x + y) + z
                    int leftSide = (x + y) + z;

                    // Calculate x + (y + z)
                    int rightSide = x + (y + z);

                    // Check if associativity holds
                    boolean isEqual = leftSide == rightSide;

                    if (!isEqual) {
                        errorCount++;
                    }

                    // Print the result for each combination of x, y, z
                    System.out.printf("x = %d, y = %d, z = %d | (x + y) + z = %d, x + (y + z) = %d | Associative: %b%n", 
                                      x, y, z, leftSide, rightSide, isEqual);
                }
            }
        }

        // Calculate average percentage error if there were any errors
        if (errorCount > 0) {
            percentageError = (double) errorCount/totalTests*100; 
            System.out.printf("Percentage of associativity error: %.6f%%%n", percentageError);
        } else {
            System.out.println("No errors in associativity for the given values.");
        }

        // Print the total number of tests and failures
        System.out.printf("Total tests: %d, Tests where associativity failed: %d%n", totalTests, errorCount);
    }
}
