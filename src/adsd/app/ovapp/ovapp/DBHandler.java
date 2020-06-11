package adsd.app.ovapp.ovapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

public class DBHandler
{
    private Connection connection;

    public List<SavedTime> getSavedTimes(int ID)
    {
        List<SavedTime> savedTimes = new ArrayList<>();

        try
        {
            connection = Connection();

            String SQL = "SELECT * FROM savedRoute WHERE loggedInUser=?";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, ID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                savedTimes.add(new SavedTime(
                        resultSet.getString("departureTime"),
                        resultSet.getString("departure"),
                        resultSet.getString("arrivalTime"),
                        resultSet.getString("destination"),
                        resultSet.getString("transportType")
                ));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return savedTimes;
    }

    public void saveTime(TravelTime travelTime,
                         String transportType,
                         int loggedInUser)
    {
        try
        {
            connection = Connection();

            String SQL = "INSERT INTO savedRoute VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, travelTime.getDepartureTime());
            preparedStatement.setString(2, travelTime.getStationName());
            preparedStatement.setString(3, travelTime.getArrivalTime());
            preparedStatement.setString(4, travelTime.getDestination());
            preparedStatement.setString(5, transportType);
            preparedStatement.setInt(6, loggedInUser);

            int row = preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void editTime(
            TravelTime travelTimeBack,
            TravelTime travelTime,
            String transportType,
            int id
    )
    {
        try
        {
            connection = Connection();

            String SQL = "UPDATE savedRoute " +
                    "SET departureTime = ?, departure = ?, arrivalTime = ?, destination = ?, transportType = ?, loggedInUser = ? " +
                    "WHERE departureTime = ? AND departure = ? AND arrivalTime = ? AND destination = ? AND loggedInUser = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, travelTime.getDepartureTime());
            preparedStatement.setString(2, travelTime.getStationName());
            preparedStatement.setString(3, travelTime.getArrivalTime());
            preparedStatement.setString(4, travelTime.getDestination());
            preparedStatement.setString(5, transportType);
            preparedStatement.setInt(6, id);

            preparedStatement.setString(7, travelTimeBack.getDepartureTime());
            preparedStatement.setString(8, travelTimeBack.getStationName());
            preparedStatement.setString(9, travelTimeBack.getArrivalTime());
            preparedStatement.setString(10, travelTimeBack.getDestination());
            preparedStatement.setInt(11, id);

            int row = preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
