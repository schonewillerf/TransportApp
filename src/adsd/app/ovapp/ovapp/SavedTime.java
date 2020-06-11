package adsd.app.ovapp.ovapp;

/**
 * SavedTime class used for storing TravelTimes in DB
 */

public class SavedTime
{
    // Instance Variables
    private String departureTime;
    private String departure;
    private String arrivalTime;
    private String destination;
    private String transportType;

    // Constructor
    public SavedTime(String departureTime, String departure, String arrivalTime, String destination, String transportType)
    {
        this.departureTime = departureTime;
        this.departure = departure;
        this.arrivalTime = arrivalTime;
        this.destination = destination;
        this.transportType = transportType;
    }

    // Getter methods
    public String getDepartureTime()
    {
        return departureTime;
    }

    public String getDeparture()
    {
        return departure;
    }

    public String getArrivalTime()
    {
        return arrivalTime;
    }

    public String getDestination()
    {
        return destination;
    }

    public String getTransportType()
    {
        return transportType;
    }
    // Seanan was here
}
