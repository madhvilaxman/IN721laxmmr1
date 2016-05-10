package bit.laxmmr1.lab102usinglocation;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnTeleport;
    TextView tvLatitude;
    TextView tvLongitude;
    TextView tvClosestCity;
    double globalLongitude;
    double globalLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLatitude = (TextView) findViewById(R.id.textViewLatitude);
        tvLongitude = (TextView) findViewById(R.id.textViewLongitude);

        btnTeleport = (Button) findViewById(R.id.buttonTeleport);
        btnTeleport.setOnClickListener(new telePortClicker());
    }//End OnCreate
//============================================================================================================================================================================
        class telePortClicker implements View.OnClickListener{
            @Override
            public void onClick(View v)
            {
                AsyncAPIShowRAWJSON APIThread = new AsyncAPIShowRAWJSON();
                APIThread.execute();
            }
        }//End TeleportCLicker
/*=========================================================================================================================================================================
                //HTTP URL CONNECTION 1
                ASYNC TASK AND HTTP CONNECTION
==============================================================================================================================================================================*/
        class AsyncAPIShowRAWJSON extends AsyncTask<Void, Void,String>{
            @Override
            protected String doInBackground(Void... arg0)
            {
                String JSONString = null;
                String FlickrJSONString = null;
                String geoPluginString = "No Place found";

                double latitude = 0.00;
                double longitude = 0.00;

                while (geoPluginString == "No Place found")
                {
                latitude = RandomLocationGenerator.generateLocation(2);
                longitude = RandomLocationGenerator.generateLocation(1);

                String urlString = "http://www.geoplugin.net/extras/location.gp?" +
                        "lat=" + latitude+
                        "&long="+ longitude + "&format=json";

                try {
                    //HttpURL Connection
                    URL URLObject = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                    connection.connect();

                    //Get inputStream
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    //Reading the input
                    String responseString;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((responseString = bufferedReader.readLine()) != null)
                    {
                        stringBuilder = stringBuilder.append(responseString);
                    }
                    //Get the String from the String builder JSON STring ready for parsing
                    JSONString = stringBuilder.toString();

                    //Get JSON Object
                    JSONObject JSONGeoPlugin = new JSONObject(JSONString);
                    geoPluginString = JSONGeoPlugin.optString("geoplugin_place", "No Place found");

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    }//end Try

                }//End while
                globalLatitude = latitude;
                globalLongitude = longitude;
/*=========================================================================================================================================================================
                //HTTP URL CONNECTION 2
                GET IMAGE FROM FLICKR
==============================================================================================================================================================================*/
                String urlFlickrString = "https://api.flickr.com/services/rest/?format=json&nojsoncallback=1&method=flickr.photos.search&api_key=eda41a123d459be0f85276d37290651e&tags=";
                urlFlickrString += geoPluginString;
                try {
                    //HttpURL Connection
                    URL URLPhotoObject = new URL(urlFlickrString);
                    HttpURLConnection connection = (HttpURLConnection) URLPhotoObject.openConnection();
                    connection.connect();

                    //Get inputStream
                    InputStream inputStream1 = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream1);
                    BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader);

                    //Reading the input
                    String responseString;
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((responseString = bufferedReader1.readLine()) != null)
                    {
                        stringBuilder = stringBuilder.append(responseString);
                    }//end While

                    //Get the String from the String builder JSON String ready for parsing
                    FlickrJSONString = stringBuilder.toString();

                    //Get JSON OBJECT FROM THE FLICK JSON STRING
                    //Convert to JSON
                    JSONObject FlickrObject = new JSONObject(FlickrJSONString);
                    JSONObject photosObject = FlickrObject.getJSONObject("photos");
                    String totalResultsString = photosObject.getString("total");
                    int totalResults = Integer.parseInt(totalResultsString);

                    if (totalResults > 0)
                    {
                        JSONArray photoArray = photosObject.getJSONArray("photo");
                        JSONObject firstPhotoObject = photoArray.getJSONObject(0);
                        String farm = firstPhotoObject.getString("farm");
                        String server = firstPhotoObject.getString("server");
                        String id = firstPhotoObject.getString("id");
                        String secret = firstPhotoObject.getString("secret");

                        String imageURL = "http://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + "_m.jpg";
                    }//end If
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }//end Catch
                    catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//====================================================================================================================================================================
                return geoPluginString;
            }//End DoInBackground
// ============================================================================================================================
        @Override
        public  void  onPostExecute( String fetchedString)
        {
            tvClosestCity = (TextView) findViewById(R.id.textViewClosestCity);
            tvClosestCity.setText("Closest City : " + fetchedString);
            tvLongitude.setText("Longitude : " + String.format("%.3f", globalLongitude));
            tvLatitude.setText("Latitude : " + String.format("%.3f", globalLatitude));

        }//End PostExecute
    }//End AsyncClass
}//End Main Activity
