package adsd.app.ovapp.ovapp;

import adsd.app.ovapp.bus.BusTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

public class DBHandler
{
    private Connection connection;
    private TravelTime busTime;
    private String SQL;

    public TravelTime getTravelTime(String transportType, String departureTime, String platform, String destination)
    {
        // Pre-prepare the SQL statement with the correct travel type
        if (transportType.equals("Bus"))
        {
            SQL = "SELECT * FROM busTime WHERE departureTime=? AND platform=? AND destination=?;";
        }
        
        else if (transportType.equals("Train"))
        {
            SQL = "SELECT * FROM trainTime WHERE departureTime=? AND platform=? AND destination=?;";
        }
        
        else if (transportType.equals("Tram"))
        {
            SQL = "SELECT * FROM tramTime WHERE departureTime=? AND platform=? AND destination=?;";
        }
        
        else if (transportType.equals("Metro"))
        {
            SQL = "SELECT * FROM metroTime WHERE departureTime=? AND platform=? AND destination=?;";
        }

        try
        {
            connection = Connection();

            // Actually prepare the SQL statement with parameters from selected travelTime
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, departureTime);
            preparedStatement.setString(2, platform);
            preparedStatement.setString(3, destination);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Execute if there is a result in the DB
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
                            resultSet.getInt("distance") // Only the busTime table has a distance column
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
                            //resultSet.getInt("distance")
                            9000 // Use hard coded distance for other travel types until available in DB
                    );
                }

                return busTime;
            }
        }
        
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return null; // Should check if return is not null when using this method
    }
}
