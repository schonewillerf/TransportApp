package adsd.app.ovapp.train;

import adsd.app.ovapp.train.NScontent.NStrain;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


//import org.codehaus.jackson.map.JsonParseException;

public class NSapi {

    public static void main(String[] args) {


        CloseableHttpClient httpclient = HttpClients.createDefault();

        ObjectMapper mapper = new ObjectMapper();


        try {


            URIBuilder builder = new URIBuilder("https://gateway.apiportal.ns.nl/reisinformatie-api/api/v3/trips");

            builder.setParameter("originTransit","false");
            builder.setParameter("originWalk","false");
            builder.setParameter("originBike","false");
            builder.setParameter("originCar","false");
            builder.setParameter("travelAssistanceTransferTime","0");
            builder.setParameter("searchForAccessibleTrip","false");
            builder.setParameter("destinationTransit","false");
            builder.setParameter("destinationWalk", "false");
            builder.setParameter("destinationBike", "false");
            builder.setParameter("destinationCar", "false");
            builder.setParameter("excludeHighSpeedTrains", "false");
            builder.setParameter("excludeHighSpeedTrains", "false");
            builder.setParameter("excludeReservationRequired", "false");
            builder.setParameter("passing", "false");
            builder.setParameter("fromStation", "Maarssen");
            builder.setParameter("toStation", "UT");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Accept", "");
            request.setHeader("X-Request-ID", "");
            request.setHeader("X-Caller-ID", "");
            request.setHeader("x-api-key", "");
            request.setHeader("Authorization", "");

            request.setHeader("Ocp-Apim-Subscription-Key", "ecea2783032c441fb813a07205d4cd80");


            // Request body
            StringEntity reqEntity = new StringEntity("{body}");
            //request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            request.toString();


            if (entity != null) {


                NStrain nstrain = mapper.readValue(EntityUtils.toString(entity), NStrain.class);
                System.out.println(nstrain.trips[0].legs[0].name);
                System.out.println(nstrain.trips[0].legs[0].getDirection());
                System.out.println(nstrain.trips[0].legs[0].destination.getName());

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}