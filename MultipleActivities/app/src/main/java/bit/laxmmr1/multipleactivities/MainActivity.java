package bit.laxmmr1.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//For Launching the App and Intent for Activity A to Activity B
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button changeActivity = (Button) findViewById(R.id.buttonA);

        ChangeActivityButtonClickHandler Bob = new ChangeActivityButtonClickHandler();
        changeActivity.setOnClickListener(Bob);
        }

        class ChangeActivityButtonClickHandler implements View.OnClickListener{
            @Override
            public void onClick(View v){
                //New Intent Instance (CurrentActivityInstance to next/targetted Activity class)
                Intent changeActivityIntent = new Intent(MainActivity.this, ActivityB.class);
                startActivity(changeActivityIntent);
            }
        }
    }

