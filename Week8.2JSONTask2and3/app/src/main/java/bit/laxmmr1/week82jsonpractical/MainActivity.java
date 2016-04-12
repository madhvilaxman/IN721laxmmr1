package bit.laxmmr1.week82jsonpractical;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String assetFileName = "dunedin_events.json";
    ListView viewEvents;
    Button fillList;
    ArrayList<String> titleList;
    ArrayList<String> descriptionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewEvents = (ListView) findViewById(R.id.listView);


        viewEvents.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //Gets the Description from the position of the item on the list
                String description = descriptionList.get(position);
                Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();

            }
        });//ENd SetonCLickListener

        fillList = (Button) findViewById(R.id.buttonFillList);

        fillList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String assetFileName = "dunedin_events.json";

                //Creating an Asset managet and  create an input stream from the JSON file
                AssetManager am = getAssets();
                InputStream inputStream = null;
                int fileSizeInBytes = 0;
                byte[] JSONBuffer = null;
                try {
                    inputStream = am.open(assetFileName);
                    fileSizeInBytes = inputStream.available();
                    JSONBuffer = new byte[fileSizeInBytes];
                    inputStream.read(JSONBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Creating a string from the byte[]
                String JSONInput = new String(JSONBuffer);
                titleList = new ArrayList<String>();
                descriptionList = new ArrayList<String>();

                try {
                    //JSON OBJECT alter object
                    JSONObject alterObject = new JSONObject(JSONInput);
                    //This gets the Key "Events" Object from the JSON File
                    JSONObject eventsObject = alterObject.getJSONObject("events");
                    //Gets the JSON ARRAY from the JSONOBJECT
                    JSONArray eventArray = eventsObject.getJSONArray("event");
                    int arraySize = eventArray.length();

                    for (int i=0; i < arraySize; i++)
                    {
                        JSONObject eventObject = eventArray.getJSONObject(i);
                        titleList.add(eventObject.getString("title"));
                        descriptionList.add(eventObject.getString("description"));

                    }
                }
                    catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, titleList);
                viewEvents.setAdapter(adapter);

            }//End Method
        });//End On click listener
    }//End Oncreate
    public void  addListenerOnButton()
    {
    }
}//End Class
