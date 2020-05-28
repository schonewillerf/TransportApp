package adsd.app.ovapp.ovapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Super class for transport times
 * This prevents us repeating the work when retrieving data, see OvApp Location Panel
 */
public abstract class TravelTime
{
    // Departure and arrival are stored in the parent class
    private final String departureTime;
    private final String arrivalTime;

    // This is the constructor for the parent class
    public TravelTime(String departureTime, String arrivalTime)
    {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    // These getters are implemented in the parent class
    // This prevents duplicate work
    public String getDepartureTime()
    {
        return departureTime;
    }

    public String getArrivalTime()
    {
        return arrivalTime;
    }

    /**
     * Calculates a duration based on departure and arrival time
     *
     * @return Time as string formatted in HH:mm
     */
    public String getDuration()
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date departure = null;
        Date arrival = null;
        try
        {
            departure = format.parse(departureTime);
            arrival = format.parse(arrivalTime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        long millis = arrival.getTime() - departure.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(millis); // Number of full hours
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60; // Remainder of minutes less than 60

        return String.format("%02d:%02d", hours, minutes); // Returns time as in "00:35"
    }

    // Abstract methods
    // These should be implemented in child classes
    public abstract String getPlatform();
    public abstract String getStationName();
    public abstract String getDestination();
    public abstract String getRoute();
    public abstract String getDistance();
}
