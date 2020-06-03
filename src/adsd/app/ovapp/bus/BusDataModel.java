package adsd.app.ovapp.bus;

import adsd.app.ovapp.metro.MetroTime;
import adsd.app.ovapp.ovapp.TravelTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

/**
 * BusDataModel class
 *
 * Future place of magic, for now just two hard coded BusTime objects
 */
public class BusDataModel
{
	private List<TravelTime> busTimeList = new ArrayList<>();
	private Connection connection;

	private void parseDataAndBuildList() 
	{
		busTimeList.clear();

		try
		{
			connection = Connection();

			String SQL = "SELECT * FROM busTime";

			PreparedStatement preparedStatement = connection.prepareStatement(SQL);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				busTimeList.add(new BusTime(
						resultSet.getString("arrivalTime"),
						resultSet.getString("departureTime"),
						resultSet.getString("platform"),
						resultSet.getString("departure"),
						resultSet.getString("destination"),
						resultSet.getString("route"),
						resultSet.getInt("distance")
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
		return busTimeList;
	}

	public TravelTime getTravelTime(String departureTime, String platform, String destination)
	{
		String SQL = "SELECT * FROM busTime WHERE departureTime=? AND platform=? AND destination=?;";

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
				BusTime busTime = new BusTime(
						resultSet.getString("arrivalTime"),
						resultSet.getString("departureTime"),
						resultSet.getString("platform"),
						resultSet.getString("departure"),
						resultSet.getString("destination"),
						resultSet.getString("route"),
						resultSet.getInt("distance")
				);

				return busTime;
			}
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}

		return null; // Should check if return is not null when using this method
	}
	public TravelTime getSavedTravelTime(String departureTime, String departure, String arrivalTime, String destination)
	{
		String SQL = "SELECT * FROM busTime WHERE departureTime=? AND departure=? AND arrivalTime=? AND destination=?;";

		try
		{
			connection = Connection();

			// Actually prepare the SQL statement with parameters from selected travelTime
			PreparedStatement preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1, departureTime);
			preparedStatement.setString(2, departure);
			preparedStatement.setString(3, arrivalTime);
			preparedStatement.setString(4, destination);

			ResultSet resultSet = preparedStatement.executeQuery();

			// Execute if there is a result in the DB
			if (resultSet.next())
			{
				BusTime busTime = new BusTime(
						resultSet.getString("arrivalTime"),
						resultSet.getString("departureTime"),
						resultSet.getString("platform"),
						resultSet.getString("departure"),
						resultSet.getString("destination"),
						resultSet.getString("route"),
						resultSet.getInt("distance")
				);

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

