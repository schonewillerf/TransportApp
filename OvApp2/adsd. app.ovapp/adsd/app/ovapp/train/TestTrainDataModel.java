package adsd.app.ovapp.train;

public class TestTrainDataModel {
	
	private static Boolean overallTestResult = true;
	
	/**     * Default constructor for test class TestTraintDataModel     
	 * @param traintime */
	
	 public static Boolean runTester() {
		 
		System.out.println("\nRun tester..");
		
		
		// Execute and display results of the defined unittests

        // Testcase 1, step-by-step written
		
        int testCase = 1;
        Boolean testResult = testCase1();
        printTestResult(testCase, testResult);
		
        //testcase 2 short
        printTestResult(2, testCase2());
    
        
		return overallTestResult;
		 
	 }
	 
	private static Boolean testCase1() {
		
		TrainTime departureTime = new TrainTime();
		departureTime.setDepartureTime("14:00");
		
		return setOverallTestResult(String.valueOf(departureTime.getDepartureTime() ).equals("14:00"));
	}
	 
	private static Boolean testCase2() {
		TrainTime stationName = new TrainTime();
		stationName.setStationName("Amersfoort");
		
		return setOverallTestResult(String.valueOf(stationName.getStationName() ).equals("Amersfoort"));
	}
	
	// Set overallTestResult to false when a testCase fails
	
    private static Boolean setOverallTestResult(Boolean testResult) {
    	
        if(!testResult) {
            overallTestResult = false;
        }

        return testResult;
    }
    
   
    private static void printTestResult(int testCase, Boolean testResult) {
    	
      // System.out.println("\t#Test result @testCase " + String.valueOf(testCase) + ": " + testResult);
    } 
    
	public static void main(String[] args  ) {
		
		
		System.out.println("\n[!] Overall test result: " + runTester() + "\n"); 
		 
	}
}




