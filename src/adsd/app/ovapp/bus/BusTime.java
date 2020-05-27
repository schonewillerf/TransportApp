package adsd.app.ovapp.bus;

import adsd.app.ovapp.ovapp.TravelTime;

/**
 * BusTime class
 */
public class BusTime extends TravelTime
{
	private String arrivalTime;
	private String departureTime;
	private String platform;
	private String stationName;
	private String destination;
	private String route;

	public BusTime() 
	{
		this(null,null,null,null,null,null);
	}
	
	public BusTime(String arrivalTime,
            String departureTime,
            String platform,
            String stationName,
            String destination,
            String route)
						 
	{
		 this.arrivalTime = arrivalTime;
	     this.departureTime = departureTime;
	     this.platform = platform;
	     this.stationName = stationName;
	     this.destination = destination;
	     this.route = route;
	
	}

	// Set and Get arrivalTime
	public String getArrivalTime() 
	{
		return arrivalTime;
	}
	//
	public void setArrivalTime(String arrivalTime) 
	{
		this.arrivalTime = arrivalTime;
	}

	// Set and Get departureTime
	public String getDepartureTime() 
	{
		return departureTime;
	}
	//
	public void setDepartureTime(String departureTime) 
	{
		this.departureTime = departureTime;
	}

	// Get platform
	public String getPlatform()
	{
		return platform;
	}

	// Get stationName
	public String getStationName() 
	{
		return stationName;
	}

	// Get destination
	public String getDestination() 
	{
		return destination;
	}

	// Get route
	public String getRoute()
	{
		return route;
	}
}

