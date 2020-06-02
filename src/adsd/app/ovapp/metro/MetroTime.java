package adsd.app.ovapp.metro;

import adsd.app.ovapp.ovapp.TravelTime;

public class MetroTime extends TravelTime
{
    private String platform;
    private String stationName;
    private String destination;
    private String route;
    private int distance;

    public MetroTime(String arrivalTime,
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

    public String getPlatform()
    {
        return platform;
    }

    public String getStationName()
    {
        return this.stationName;
    }

    public String getDestination()
    {
        return this.destination;
    }

    public String getRoute()
    {
        return this.route;
    }

    public String getDistance()
    {
        return String.format("%s m", distance);
    }
}
