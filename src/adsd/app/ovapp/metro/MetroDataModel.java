package adsd.app.ovapp.metro;

import adsd.app.ovapp.ovapp.TravelTime;

import java.util.ArrayList;





public class MetroDataModel
{
	private ArrayList<TravelTime> metroTimeList = new ArrayList<>();

	private void parseDataAndBuildList() 
	{
		// Clear the list first
		metroTimeList.clear();

		// Parse some data and build list
		metroTimeList.add(new MetroTime("12:00", "12:15", "perron 1c", "Amsterdam ZD","Amsterdam OST","6c"));
		metroTimeList.add(new MetroTime("13:00", "13:15", "perron 1a", "Amsterdam Ctrl","Amsterdam WST","2d"));
	}
	
	public ArrayList<TravelTime> getArrivalTimes()
	{
		parseDataAndBuildList();
		
		return metroTimeList;
	}
	
	public ArrayList<TravelTime> getDepartureTimes()
	{
		parseDataAndBuildList();
		
		return metroTimeList;
	}
}

