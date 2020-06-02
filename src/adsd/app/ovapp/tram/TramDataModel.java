package adsd.app.ovapp.tram;

import adsd.app.ovapp.metro.MetroTime;
import adsd.app.ovapp.ovapp.TravelTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

public class TramDataModel
{
	private List<TravelTime> tramTimeList = new ArrayList<>();
	private Connection connection;

	private void parseDataAndBuildList() 
	{
		// Clear the list first
		tramTimeList.clear();

		try
		{
			connection = Connection();

			String SQL = "SELECT * FROM tramTime";

			PreparedStatement preparedStatement = connection.prepareStatement(SQL);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				tramTimeList.add(new TramTime(
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
		return tramTimeList;
	}

	public TravelTime getTravelTime(String departureTime, String platform, String destination)
	{
		String SQL = "SELECT * FROM tramTime WHERE departureTime=? AND platform=? AND destination=?;";

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
				TramTime tramTime = new TramTime(
						resultSet.getString("arrivalTime"),
						resultSet.getString("departureTime"),
						resultSet.getString("platform"),
						resultSet.getString("departure"),
						resultSet.getString("destination"),
						resultSet.getString("route"),
						resultSet.getInt("distance")
				);

				return tramTime;
			}
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}

		return null; // Should check if return is not null when using this method
	}
	
}

