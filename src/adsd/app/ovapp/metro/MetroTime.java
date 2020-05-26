package adsd.app.ovapp.metro;

import adsd.app.ovapp.ovapp.TravelTime;

public class MetroTime extends TravelTime
{

    private String arrivalTime;
    private String departureTime;
    private String platform;
    private String stationName;
    private String destination;
    private String route;


    public MetroTime()
    {
        this(null, null, null, null, null,null);
    }


    public MetroTime(String arrivalTime,
                    String departureTime,
                    String platform,
                    String stationName,
                    String destination,
                    String route
    )

    {
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.platform = platform;
        this.stationName = stationName;
        this.destination = destination;
        this.route = route;
    }


    public  void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTime()
    {
        return this.arrivalTime;
    }


    public void setDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }

    public String getDepartureTime()
    {
        return this.departureTime;
    }


    public  void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public String getPlatform()
    {
        return platform;
    }


    public  void setStationName(String stationName)
    {
        this.stationName = stationName;
    }

    public String getStationName()
    {
        return this.stationName;
    }


    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    public String getDestination()
    {
        return this.destination;
    }


    public void setRoute(String route)
    {
        this.route = route;
    }

    public String getRoute()
    {
        return this.route;
    }

}
