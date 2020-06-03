package adsd.app.ovapp.metro;

import adsd.app.ovapp.bus.BusTime;
import adsd.app.ovapp.ovapp.TravelTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

public class MetroDataModel
{
	private ArrayList<TravelTime> metroTimeList = new ArrayList<>();
	private Connection connection;

	private void parseDataAndBuildList() 
	{
		// Clear the list first
		metroTimeList.clear();

		try
		{
			connection = Connection();

			String SQL = "SELECT * FROM metroTime";

			PreparedStatement preparedStatement = connection.prepareStatement(SQL);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				metroTimeList.add(new MetroTime(
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
		return metroTimeList;
	}

	public TravelTime getTravelTime(String departureTime, String platform, String destination)
	{
		String SQL = "SELECT * FROM metroTime WHERE departureTime=? AND platform=? AND destination=?;";

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
				MetroTime metroTime = new MetroTime(
						resultSet.getString("arrivalTime"),
						resultSet.getString("departureTime"),
						resultSet.getString("platform"),
						resultSet.getString("departure"),
						resultSet.getString("destination"),
						resultSet.getString("route"),
						resultSet.getInt("distance")
				);

				return metroTime;
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
		String SQL = "SELECT * FROM metroTime WHERE departureTime=? AND departure=? AND arrivalTime=? AND destination=?;";

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
				MetroTime metroTime = new MetroTime(
						resultSet.getString("arrivalTime"),
						resultSet.getString("departureTime"),
						resultSet.getString("platform"),
						resultSet.getString("departure"),
						resultSet.getString("destination"),
						resultSet.getString("route"),
						resultSet.getInt("distance")
				);

				return metroTime;
			}
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}

		return null; // Should check if return is not null when using this method
	}
}

