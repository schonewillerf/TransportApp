package adsd.app.ovapp.ovapp;

import adsd.app.ovapp.bus.BusDataModel;
import adsd.app.ovapp.bus.BusTime;
import adsd.app.ovapp.metro.MetroDataModel;
import adsd.app.ovapp.train.TrainDataModel;
import adsd.app.ovapp.tram.TramDataModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

public class DBHandler
{
    private Connection connection;
    private BusTime busTime;

    public TravelTime getTravelTime(String transportType, int id)
    {
        String SQL = switch (String.valueOf(transportType))
                {
                    case "Bus" -> "SELECT * FROM busTime WHERE route=?";
                    case "Train" -> "SELECT * FROM trainTime WHERE route=?";
                    case "Tram" -> "SELECT * FROM tramTime WHERE route=?";
                    case "Metro" -> "SELECT * FROM metroTime WHERE route=?";
                    default -> throw new IllegalStateException("Unexpected value: " + transportType);
                };

        try
        {
            connection = Connection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                if (transportType.equals("Bus"))
                {
                    busTime = new BusTime(
                            resultSet.getString("arrivalTime"),
                            resultSet.getString("departureTime"),
                            resultSet.getString("platform"),
                            resultSet.getString("departure"),
                            resultSet.getString("destination"),
                            resultSet.getString("route"),
                            resultSet.getInt("distance")
                    );
                }
                else
                {
                    busTime = new BusTime(
                            resultSet.getString("arrivalTime"),
                            resultSet.getString("departureTime"),
                            resultSet.getString("platform"),
                            resultSet.getString("departure"),
                            resultSet.getString("destination"),
                            resultSet.getString("route"),
                            9000
                            //resultSet.getInt("distance")
                    );
                }


                return busTime;
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return null;
    }
}
