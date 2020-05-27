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

		// Parse some data and build list
		tramTimeList.add(new TramTime("15:00", "15:15", "perron 1a", "Amsterdam Ctrl","Amsterdam OS","6c"));
		tramTimeList.add(new TramTime("16:00", "16:30", "perron 2b", "Amsterdam ZD","Amsterdam CRL","5d"));
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

