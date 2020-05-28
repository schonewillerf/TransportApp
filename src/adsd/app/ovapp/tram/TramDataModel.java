package adsd.app.ovapp.tram;

import adsd.app.ovapp.ovapp.TravelTime;

import java.util.ArrayList;
import java.util.List;


public class TramDataModel
{
	private List<TravelTime> tramTimeList = new ArrayList<>();

	private void parseDataAndBuildList() 
	{
		// Clear the list first
		tramTimeList.clear();

	}
	
	public List<TravelTime> getArrivalTimes()
	{
		parseDataAndBuildList();
		
		return tramTimeList;
	}
}

