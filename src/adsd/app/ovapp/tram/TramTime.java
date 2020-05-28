package adsd.app.ovapp.tram;

import adsd.app.ovapp.ovapp.TravelTime;

public class TramTime extends TravelTime
{
    private String platform;
    private String stationName;
    private String destination;
    private String route;
    private int distance;

    public TramTime(String arrivalTime,
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
        return String.format("%s km", distance / 1000);
    }
}
