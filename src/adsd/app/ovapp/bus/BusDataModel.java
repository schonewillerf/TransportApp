package adsd.app.ovapp.bus;

import java.util.ArrayList;

/**
 * BusDataModel class
 *
 * Future place of magic, for now just two hard coded BusTime objects
 */

public class BusDataModel
{
	private ArrayList<BusTime> busTimeList = new ArrayList<>();

	private void parseDataAndBuildList() 
	{
		// Clear the list first
		busTimeList.clear();

		// Parse some data and build list
		busTimeList.add(new BusTime("13:15", "13.45", "perron D", "Amersfoort","Amersfoort ZD","perron A"));
		busTimeList.add(new BusTime("13:30", "14.15", "peroon F", "Amersfoort", "Amersfoort CRL","Perron J"));
	}
	
	public ArrayList<BusTime> getArrivalTimes() 
	{
		parseDataAndBuildList();
		
		return busTimeList;
	}
	
	public ArrayList<BusTime> getDepartureTimes() 
	{
		parseDataAndBuildList();
		
		return busTimeList;
	}
}

