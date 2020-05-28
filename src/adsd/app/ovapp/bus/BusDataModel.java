package adsd.app.ovapp.bus;

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
}

