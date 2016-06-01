package bit.laxmmr1.googleplayservices;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by laxmmr1 on 24/05/2016.
 */
public class CityFromInternet extends AsyncTask<Void, Void, String>
{
    private double longitude;
    private double latitude;
    private final int LONGITUDE = 0;
    private final int LATITUDE = 1;
    private MainActivity activity;


    @Override
    protected String doInBackground(Void... params)
    {
        boolean presentCity = false;
        String JSONData = null;
        String cityName ="";

        while (!presentCity){
            longitude = RandomLocation.generateRandomLocation(LONGITUDE);
            latitude = RandomLocation.generateRandomLocation(LATITUDE);

            String URLString = "http://www.geoplugin.net/extras/location.gp?" +
                    "lat=" + latitude +
                    "&long=" + longitude +
                    "&format=json";

            try {
                //HTTP CONNECTION OBJECT and stream reader
                URL URLObject = new URL(URLString);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String bufferString;
                StringBuilder stringBuilder = new StringBuilder();

                while ((bufferString = bufferedReader.readLine()) != null) {
                    stringBuilder = stringBuilder.append(bufferString);
                }
                //Pulling JSON DATA
                JSONData = stringBuilder.toString();
                JSONObject firstObject = new JSONObject(JSONData);
                cityName = firstObject.optString("geoplugin_place", "None");
                presentCity =! cityName.equals("None");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }//End While
        return cityName;
    }//end do inBackground

    @Override
    protected void onPostExecute(String fetchedData)
    {
        LatLng chosenCity = new LatLng(latitude, longitude);

        ((MainActivity) activity).getMap().addMarker(new MarkerOptions().position(chosenCity).title("Marker currently in: " + fetchedData));
        ((MainActivity) activity).getMap().moveCamera(CameraUpdateFactory.newLatLng(chosenCity));

    }//End onPostExecute
}//end Clas cityfromInternet
