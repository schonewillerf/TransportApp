package adsd.app.ovapp.ovapp;

import adsd.app.ovapp.bus.BusTime;

import java.util.ArrayList;
import java.util.List;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

public class DBHandler
{
    public List<TravelTime> getSavedTimes()
    {
        List<TravelTime> savedTimes = new ArrayList<>();



        savedTimes.add(new BusTime("12", "34", "ede", "sdede", "amersd", "56", 7000));

        return savedTimes;
    }

}
