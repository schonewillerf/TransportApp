package adsd.app.ovapp.ovapp;

import adsd.app.ovapp.bus.BusTime;

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
                        resultSet.getInt("transportType")
                ));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return savedTimes;
    }

    public void saveTime(String departureTime,
                         String departure,
                         String arrivalTime,
                         String destination,
                         String transportType,
                         int loggedInUser)
    {
        int transportNumber = 0;

        if (transportType.equals("Bus"))
        {
            transportNumber = 1;
        }
        else if (transportType.equals("Metro"))
        {
            transportNumber = 2;
        }
        else if (transportType.equals("Train"))
        {
            transportNumber = 3;
        }
        else if (transportType.equals("Tram"))
        {
            transportNumber = 4;
        }

        try
        {
            connection = Connection();

            String SQL = "INSERT INTO savedRoute VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, departureTime);
            preparedStatement.setString(2, departure);
            preparedStatement.setString(3, arrivalTime);
            preparedStatement.setString(4, destination);
            preparedStatement.setInt(5, transportNumber);
            preparedStatement.setInt(6, loggedInUser);

            int row = preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
