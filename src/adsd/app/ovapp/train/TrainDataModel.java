package adsd.app.ovapp.train;

import adsd.app.ovapp.ovapp.TravelTime;

import java.util.ArrayList;


public class TrainDataModel
{
    private ArrayList<TravelTime> trainTimesList = new ArrayList<TravelTime>();

    public void parseDataAndBuildList()
    {

    }

    public ArrayList<TravelTime> getArrivalTimes()
    {

        parseDataAndBuildList();
        return trainTimesList;
    }

    public ArrayList<TravelTime> getDepatureTimes()
    {

        parseDataAndBuildList();
        return trainTimesList;
    }
}
