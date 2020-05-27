package adsd.app.ovapp.ovapp;

/**
 * Super class for transport times
 * This prevents us repeating the work when retrieving data, see OvApp Location Panel
 */
public abstract class TravelTime
{
    public abstract String getArrivalTime();
    public abstract String getDepartureTime();
    public abstract String getPlatform();
    public abstract String getStationName();
    public abstract String getDestination();
    public abstract String getRoute();
}
