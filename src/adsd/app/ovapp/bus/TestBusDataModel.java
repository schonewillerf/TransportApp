package adsd.app.ovapp.bus;

/**
 * This class has a main method that will run a couple of unit tests
 */

import adsd.app.ovapp.ovapp.TravelTime;

import java.util.List;

public class TestBusDataModel
{
	private boolean overallTestResult = true;

	// Runs the unit tests
	public Boolean runUnitTest()
	{
		System.out.println("\nRun tester..");

		printTestResult(1, testCase1());
		printTestResult(2, testCase2());
        printTestResult(3, testCase3());

        return overallTestResult;
	}

	// Test getArrivalTimes().get(0).getDestination()
	// Result should be "Hogwarts"
	private Boolean testCase1()
	{
		BusDataModel model = new BusDataModel();
		List<TravelTime> arrivalTimes = model.getArrivalTimes();
		TravelTime bus1 = arrivalTimes.get(0);

		return setOverallTestResult(String.valueOf(bus1.getDestination()).equals("Amersfoort ZD"));
	}

	// Test getArrivalTimes().get(0).getPlatform()
	// Result should be "11 3/4"
	private Boolean testCase2()
	{
		BusDataModel model = new BusDataModel();
		List<TravelTime> arrivalTimes = model.getArrivalTimes();
		TravelTime bus2 = arrivalTimes.get(0);
		
		return setOverallTestResult(String.valueOf(bus2.getPlatform()).contentEquals("Perron D"));
	}

    // Test getDuration()
    // Result should be "00:10"
    private Boolean testCase3()
    {
        BusTime bus3 = new BusTime("12:20", "12:10", "", "", "", "", 0);

        return setOverallTestResult(String.valueOf(bus3.getDuration()).contentEquals("00:10"));
    }

	private Boolean setOverallTestResult(Boolean testResult)
	{
		if(!testResult)
		{
			overallTestResult = false;
		}

		return testResult;
	}

	private void printTestResult(int testCase, Boolean testResult)
	{
		System.out.println("\t#Test result @testCase " + String.valueOf(testCase) + ": " + testResult);
	}

	/**
	 * This static main method is now calling the runUnitTest in a non static way
	 *
	 * @param args
	 */
	public static void main(String[] args) 
	{
		TestBusDataModel model = new TestBusDataModel();
		System.out.println("\n[!] Overall test result: " + model.runUnitTest() + "\n");
	}
}

