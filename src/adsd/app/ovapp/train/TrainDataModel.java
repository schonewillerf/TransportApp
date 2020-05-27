package adsd.app.ovapp.train;

import adsd.app.ovapp.ovapp.TravelTime;

import java.util.ArrayList;


public class TrainDataModel
{
    private ArrayList<TravelTime> trainTimesList = new ArrayList<TravelTime>();

    public void parseDataAndBuildList()
    {

        TrainTime train1 = new TrainTime("13:00", "13:15", "Spoor 2", "Utrecht CRL", "Amsterdam Crl", "4c");
        TrainTime train2 = new TrainTime("14:00", "14:15", "Spoor 4", "Amsterdam CRL", "Amersfoort Crl", "5b");
        trainTimesList.add(train1);
        trainTimesList.add(train2);
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
