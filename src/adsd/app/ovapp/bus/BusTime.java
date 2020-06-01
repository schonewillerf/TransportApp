package adsd.app.ovapp.bus;

import adsd.app.ovapp.ovapp.TravelTime;

/**
 * BusTime class
 */
public class BusTime extends TravelTime
{
    private String platform;
    private String stationName;
    private String destination;
    private String route;
    private int distance;

    public BusTime(String arrivalTime,
                   String departureTime,
                   String platform,
                   String stationName,
                   String destination,
                   String route,
                   int distance
    )
    {
        super(departureTime, arrivalTime);
        this.platform = platform;
        this.stationName = stationName;
        this.destination = destination;
        this.route = route;
        this.distance = distance;
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

    // Get distance
    public String getDistance()
    {
        return String.format("%s km", distance / 1000);
    }
}

