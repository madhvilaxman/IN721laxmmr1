package bit.laxmmr1.week82jsonpractical;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    String assetFileName = "dunedin_events.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Creating an Asset managet and  create an input stream from the JSON file
        AssetManager am = getAssets();
        InputStream inputStream = null;
        try
        {
            inputStream = am.open(assetFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }



        //Determine number of bytes in file and prepare buffer array for reading
        int fileSizeInBytes = 0;
        try
        {
            fileSizeInBytes = inputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] JSONBuffer = new byte[fileSizeInBytes];


        //Read the Stream into the buffer and close it
        try
        {
            inputStream.read(JSONBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try
        {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Create a String from the byte
        String JSONInput = new String(JSONBuffer);

        //Convert the fileString to a JSON Object
        JSONObject dunedinEvents = null;
        try
        {
            dunedinEvents = new JSONObject(JSONInput);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Grab the value part of the data :keyvalue pair.
        JSONArray eventsArray = null;
        try {
            eventsArray = dunedinEvents.getJSONArray("events");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Debug
        Toast.makeText(MainActivity.this, eventsArray.toString(),Toast.LENGTH_LONG).show();


    }
}
