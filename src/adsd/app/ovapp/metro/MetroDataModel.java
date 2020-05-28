package adsd.app.ovapp.metro;

import adsd.app.ovapp.bus.BusTime;
import adsd.app.ovapp.ovapp.TravelTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<TravelTime> getArrivalTimes()
	{
		parseDataAndBuildList();
		
		return metroTimeList;
	}
	
	public ArrayList<TravelTime> getDepartureTimes()
	{
		parseDataAndBuildList();
		
		return metroTimeList;
	}
}

