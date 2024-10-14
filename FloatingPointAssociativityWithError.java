public class FloatingPointAssociativityWithError {
    public static void main(String[] args) {
        // Array of test values for x, y, and z
        double[] testValues = {1e16, 1e-16, 1.0, -1e16, -1e-16, 0.0, 12345.6789, -9876.54321};
        // Variables to accumulate count errors
        int errorCount = 0;
        int totalTests = 0;
        double percentageError = 0.0;
        // Run the test for all combinations of x, y, and z from the testValues array
        for (double x : testValues) {
            for (double y : testValues) {
                for (double z : testValues) {
                    totalTests++;
                    
                    // Calculate (x + y) + z
                    double leftSide = (x + y) + z;

                    // Calculate x + (y + z)
                    double rightSide = x + (y + z);

                    // Check if associativity holds
                    boolean isEqual = leftSide == rightSide;

                    if (!isEqual) {
                        errorCount++;
                    }

                    // Print the result for each combination of x, y, z
                    System.out.printf("x = %e, y = %e, z = %e | (x + y) + z = %e, x + (y + z) = %e | Associative: %b%n", 
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
