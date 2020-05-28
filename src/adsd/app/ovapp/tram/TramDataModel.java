package adsd.app.ovapp.tram;

import adsd.app.ovapp.ovapp.TravelTime;

import java.util.ArrayList;


public class TramDataModel
{
	private ArrayList<TravelTime> tramTimeList = new ArrayList<>();

	private void parseDataAndBuildList() 
	{
		// Clear the list first
		tramTimeList.clear();

	}
	
	public ArrayList<TravelTime> getArrivalTimes()
	{
		parseDataAndBuildList();
		
		return tramTimeList;
	}
	
	public ArrayList<TravelTime> getDepartureTimes()
	{
		parseDataAndBuildList();
		
		return tramTimeList;
	}
}

