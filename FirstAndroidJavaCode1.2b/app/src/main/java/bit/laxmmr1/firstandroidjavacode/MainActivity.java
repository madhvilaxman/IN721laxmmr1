package bit.laxmmr1.firstandroidjavacode;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String myString;
    private static final Random rgenerator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtRandomString = (TextView)findViewById(R.id.txtDisplay);

        Resources resourceResolver = getResources();
            int dateArray[] = resourceResolver.getIntArray(R.array.FebFridays);

            myString = "February Fridays are on: ";
            for (int i=0; i<dateArray.length; i++){
                myString = myString.concat(" " + dateArray[i]);
            }
            TextView tv = (TextView) findViewById(R.id.txtDisplay);
            tv.setText(myString);
    }
}
