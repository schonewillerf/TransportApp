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

}
