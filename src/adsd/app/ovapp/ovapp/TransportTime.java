package adsd.app.ovapp.ovapp;

public class TransportTime
{
    String departureTime;
    String departureLocation;
    String destination;

    public TransportTime(String departureTime, String departureLocation, String destination)
    {
        this.departureTime = departureTime;
        this.departureLocation = departureLocation;
        this.destination = destination;
    }

    public String getDepartureTime()
    {
        return departureTime;
    }

    public String getDepartureLocation()
    {
        return departureLocation;
    }

    public String getDestination()
    {
        return destination;
    }
}
