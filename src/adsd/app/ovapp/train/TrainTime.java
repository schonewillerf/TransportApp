package adsd.app.ovapp.train;

import adsd.app.ovapp.ovapp.TravelTime;

public class TrainTime extends TravelTime
{

    //instance variables
    private String arrivalTime;
    private String departureTime;
    private String platform;
    private String stationName;
    private String destination;
    private String route;

    public TrainTime()
    {

        this(null, null, null, null, null, null);
    }

    public TrainTime(String arrivaltime, String departuretime, String platform, String stationname, String destination, String route)
    {

        this.arrivalTime = arrivaltime;
        this.departureTime = departuretime;
        this.platform = platform;
        this.stationName = stationname;
        this.destination = destination;
        this.route = route;

    }

    public String getArrivalTime()
    {

        return arrivalTime;
    }

    public void setArrivalTime(String arrivaltime)
    {

        this.arrivalTime = arrivaltime;
    }

    public String getDepartureTime()
    {

        return departureTime;
    }

    public void setDepartureTime(String departuretime)
    {

        this.departureTime = departuretime;
    }

    public String getPlatform()
    {

        return platform;
    }

    public void setPlatform(String platform)
    {

        this.platform = platform;
    }

    public String getStationName()
    {

        return stationName;
    }

    public void setStationName(String stationname)
    {

        this.stationName = stationname;
    }

    public String getDestination()
    {

        return destination;
    }

    public void setDestination(String destination)
    {

        this.destination = destination;
    }

    public String getRoute()
    {

        return route;
    }

    public void setRoute(String route)
    {

        this.route = route;
    }
}
