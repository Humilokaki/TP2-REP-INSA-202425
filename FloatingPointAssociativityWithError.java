public class FloatingPointAssociativityWithError {
    public static void main(String[] args) {
        // Array of test values for x, y, and z
        int[] testValues = {100000000, 0, 1, -10000000, -1000000000, 0, 12345, -9876};
        // Variables to accumulate count errors
        int errorCount = 0;
        int totalTests = 0;
        double percentageSuccess = 0.0;
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

                }
            }
        }

        // Calculate average percentage error if there were any errors
        if (errorCount > 0) {
            percentageSuccess = (double) (1 - errorCount/totalTests)*100;
	    
            System.out.printf("%.2f%%%n", percentageSuccess);
	} else {
            System.out.println("100.00");
        }
    }
}
