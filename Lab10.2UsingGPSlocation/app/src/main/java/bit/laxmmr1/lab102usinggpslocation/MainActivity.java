package bit.laxmmr1.lab102usinggpslocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnTeleport;
    TextView tvLatitude;
    TextView tvLongitude;
    TextView tvClosestCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLongitude = (TextView) findViewById(R.id.textViewLongitude);
        tvLatitude = (TextView) findViewById(R.id.textViewLatitude);

        btnTeleport = (Button) findViewById(R.id.buttonTeleport);

        btnTeleport.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Instance of the inner class
                LocationListener locationListener = new CustomLocationListener();

                //Create Utility Objects - Insance of the Location Manager
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Criteria defaultCriteria = new Criteria();

                //Set Criteria Specifications i.e. either use GPS or Network Services
                String providerName = locationManager.getBestProvider(defaultCriteria, false);
                locationManager.requestLocationUpdates(providerName, 1000, 1, locationListener);


                //Read Location
                Location currentlocation = locationManager.getLastKnownLocation(providerName);

                double lat = currentlocation.getLatitude();
                double lng = currentlocation.getLongitude();

                tvLongitude.setText("Longitude: " + String.format("%.3f" ,lng));
                tvLatitude.setText("Latitude: " + String.format("%.3f", lat));

            }//End onClick
        });//End OnClickListener
    }//End Oncreate

    private class CustomLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {

            double lat = location.getLatitude();
            double lng = location.getLongitude();

            tvLongitude.setText("Longitude: " + String.format("%.3f" ,lng));
            tvLatitude.setText("Latitude: " + String.format("%.3f", lat));
        }//End onLocationChanged

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }//EndonStatusChanged

        @Override
        public void onProviderEnabled(String provider)
        {
        }//End onProviderEnabled

        @Override
        public void onProviderDisabled(String provider)
        {
        }//End onProviderDisabled
    }//End CustomListener
}//End Main Activity

