package bit.laxmmr1.googleplayservices;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private Button btnteleport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnteleport = (Button) findViewById(R.id.buttonTeleport);
        btnteleport.setOnClickListener(new GetCity());

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    class GetCity implements  View.OnClickListener{

        @Override
        public void onClick(View v)
        {
            CityFromInternet cityFromInternet = new CityFromInternet();
            cityFromInternet.execute();
        }// End method onclick
    }//end onClick Listener

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;
    }

    public GoogleMap getMap(){
        return map;
    }
}//End Main Activity

