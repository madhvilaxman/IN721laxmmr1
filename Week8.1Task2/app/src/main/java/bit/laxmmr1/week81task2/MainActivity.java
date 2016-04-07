package bit.laxmmr1.week81task2;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonFillList = (Button) findViewById(R.id.btnFillList);
        listCities = (ListView)findViewById(R.id.listView);

        buttonFillList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> cityNameArray = new ArrayList<String>();
                String assetFileName = "city_names.txt";
                AssetManager am = getAssets();

                InputStream is = null;
                try {
                    is = am.open(assetFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ir);

                String newCity;
                try {
                    while ((newCity = br.readLine()) != null) {
                        cityNameArray.add(newCity);
                    }//End while
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, cityNameArray);
                listCities.setAdapter(adapter);
            }//End Method onCLick
        });//End Onclick Hanlder
    }//End oncrete
    public void addListenerOnButton()
    {

    }//End Listener
}//End Main Activity
