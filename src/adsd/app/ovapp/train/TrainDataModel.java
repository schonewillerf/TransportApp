package adsd.app.ovapp.train;

import adsd.app.ovapp.ovapp.OvApp;
import adsd.app.ovapp.ovapp.TravelTime;
import adsd.app.ovapp.train.NScontent.NStrain;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TrainDataModel
{
    private List<TravelTime> trainTimesList = new ArrayList<TravelTime>();

    public void parseDataAndBuildList()
    {
        trainTimesList.clear();

        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            String url = "https://gateway.apiportal.ns.nl/reisinformatie-api/api/v3/trips";
            URIBuilder builder = new URIBuilder(url);

            builder.setParameter("dateTime", "2020-06-10T10:59:00+0200");
            builder.setParameter("fromStation", OvApp.selectedDeparture());
            builder.setParameter("toStation", OvApp.selectedDestination());

            HttpGet request = new HttpGet(builder.build());
            request.setHeader("Ocp-Apim-Subscription-Key", "ecea2783032c441fb813a07205d4cd80");

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            NStrain nstrain = mapper.readValue(EntityUtils.toString(entity), NStrain.class);

            for (int i = 0; i < 6; i++)
            {
                String DepartureName = nstrain.trips[i].legs[0].origin.getName();
                String DepartureTrack = nstrain.trips[i].legs[0].origin.getPlannedTrack();
                String inptDepartureDateTime = nstrain.trips[i].legs[0].origin.getPlannedDateTime();
                String Direction = nstrain.trips[i].legs[0].getDirection();
                String DestinationName = nstrain.trips[i].legs[0].destination.getName();
                String DestinationTrack = nstrain.trips[i].legs[0].destination.getPlannedTrack();
                String inptDestinationDateTime = nstrain.trips[i].legs[0].destination.getPlannedDateTime();

                SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+'SSSS");
                SimpleDateFormat output = new SimpleDateFormat("HH:mm");

                Date dest = null;
                Date dep = null;
                try
                {
                    dest = input.parse(inptDestinationDateTime);
                    dep = input.parse(inptDepartureDateTime);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
                String DestinationDateTime = output.format(dest);
                String DepartureDateTime = output.format(dep);

                trainTimesList.add(new TrainTime(
                        DestinationDateTime,
                        DepartureDateTime,
                        DepartureTrack,
                        DepartureName,
                        DestinationName,
                        Direction,
                        9000
                ));
            }
        } catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }
    }

    public List<TravelTime> getArrivalTimes()
    {
        parseDataAndBuildList();
        return trainTimesList;
    }

    public TravelTime getTravelTime(String departureTime, String platform, String destination) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            URIBuilder builder = new URIBuilder("https://gateway.apiportal.ns.nl/reisinformatie-api/api/v3/trips");

            builder.setParameter("dateTime", "2020-06-10T"+departureTime+":00+0200");
            builder.setParameter("fromStation", OvApp.selectedDeparture());
            builder.setParameter("toStation",OvApp.selectedDestination());

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "ecea2783032c441fb813a07205d4cd80");

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            NStrain nstrain = mapper.readValue(EntityUtils.toString(entity), NStrain.class);

            String DepartureName = nstrain.trips[0].legs[0].origin.getName();
            String DepartureTrack = nstrain.trips[0].legs[0].origin.getPlannedTrack();
            String inptDepartureDateTime = nstrain.trips[0].legs[0].origin.getPlannedDateTime();
            String Direction = nstrain.trips[0].legs[0].getDirection();
            String DestinationName = nstrain.trips[0].legs[0].destination.getName();
            String DestinationTrack = nstrain.trips[0].legs[0].destination.getPlannedTrack();
            String inptDestinationDateTime = nstrain.trips[0].legs[0].destination.getPlannedDateTime();
            String priceTicket =  nstrain.trips[0].productFare.getPriceInCents();

            OvApp.lblPriceTxt.setText(priceTicket);

            SimpleDateFormat inputTwo = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+'SSSS");
            SimpleDateFormat outputTwo = new SimpleDateFormat("HH:mm");

            Date dest = null;
            Date depTwo = null;
            try
            {
                dest = inputTwo.parse(inptDestinationDateTime);
                depTwo = inputTwo.parse(inptDepartureDateTime);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            String DestinationDateTime = outputTwo.format(dest);
            String DepartureDateTime = outputTwo.format(depTwo);

            TrainTime trainTime = new TrainTime(
                    DestinationDateTime,
                    DepartureDateTime,
                    DepartureTrack,
                    DepartureName,
                    DestinationName,
                    Direction,
                    9000
            );
            return trainTime;
        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public TrainTime getSavedTravelTime(String departureTime, String departure, String arrivalTime, String destination)
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            URIBuilder builder = new URIBuilder("https://gateway.apiportal.ns.nl/reisinformatie-api/api/v3/trips");

            builder.setParameter("dateTime", "2020-06-10T" + arrivalTime + ":00+0200");
            builder.setParameter("fromStation", departure);
            builder.setParameter("toStation", destination);

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "ecea2783032c441fb813a07205d4cd80");

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            NStrain nstrain = mapper.readValue(EntityUtils.toString(entity), NStrain.class);

            String DepartureName = nstrain.trips[0].legs[0].origin.getName();
            String DepartureTrack = nstrain.trips[0].legs[0].origin.getPlannedTrack();
            String DepartureDateTime = nstrain.trips[0].legs[0].origin.getPlannedDateTime();
            String Direction = nstrain.trips[0].legs[0].getDirection();
            String DestinationName = nstrain.trips[0].legs[0].destination.getName();
            String DestinationTrack = nstrain.trips[0].legs[0].destination.getPlannedTrack();
            String DestinationDateTime = nstrain.trips[0].legs[0].destination.getPlannedDateTime();

            TrainTime trainTime = new TrainTime(
                    DestinationDateTime,
                    DepartureDateTime,
                    DepartureTrack,
                    DepartureName,
                    DestinationName,
                    Direction,
                    9000
            );
            return trainTime;

        } catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }
        return null; // Should check if return is not null when using this method
    }
}