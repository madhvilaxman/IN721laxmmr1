package bit.laxmmr1.week82jsonpractical;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewEvents = (ListView) findViewById(R.id.listView);
        fillList = (Button) findViewById(R.id.buttonFillList);

        fillList.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                ArrayList<String> dunedinEventsArray = new ArrayList<String>();

                String assetFileName = "dunedin_events.json";

                //Creating an Asset managet and  create an input stream from the JSON file
                AssetManager am = getAssets();
                InputStream inputStream = null;
                try {
                    inputStream = am.open(assetFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Determind number of bytes in file and prepare buffer array for read
                int fileSizeInBytes = 0;
                try {
                    fileSizeInBytes = inputStream.available();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] JSONBuffer = new byte[fileSizeInBytes];

                //Read the streat into the buffer, and close it
                try {
                    inputStream.read(JSONBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Creating a string from the byte[]
                String JSONInput = new String(JSONBuffer);

                //Adapter for the List View
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, dunedinEventsArray);
                viewEvents.setAdapter(adapter);

            }//End Method
        });//End On click listener
    }//End Oncreate
    public void  addListenerOnButton()
    {
    }
}//End Class
