package adsd.app.ovapp.train;

import adsd.app.ovapp.metro.MetroTime;
import adsd.app.ovapp.ovapp.TravelTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

public class TrainDataModel
{
    private List<TravelTime> trainTimesList = new ArrayList<TravelTime>();
    private Connection connection;

    public void parseDataAndBuildList()
    {
        trainTimesList.clear();

        try
        {
            connection = Connection();
            String SQL = "SELECT * FROM trainTime";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                trainTimesList.add(new MetroTime(
                        resultSet.getString("arrivalTime"),
                        resultSet.getString("departureTime"),
                        resultSet.getString("platform"),
                        resultSet.getString("departure"),
                        resultSet.getString("destination"),
                        resultSet.getString("route"),
                        9000
                        //resultSet.getInt("distance")
                ));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    public List<TravelTime> getArrivalTimes()
    {
        parseDataAndBuildList();
        return trainTimesList;
    }
}
