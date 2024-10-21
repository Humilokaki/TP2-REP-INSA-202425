public class FloatingPointAssociativityWithError {
    public static void main(String[] args) {
        // Array of test values for x, y, and z
        double[] testValues = {1e16, 1e-16, 1.0, -1e16, -1e-16, 0.0, 12345.6789};
        // Variables to accumulate count errors
        int errorCount = 0;
        int totalTests = 0;
        double percentageSuccess= 0.0;
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
                }
            }
        }

        // Calculate average percentage error if there were any errors
       
        percentageSuccess = 100 - (double) errorCount/totalTests*100; 
        System.out.printf("%.2f",percentageSuccess);
    }
}
