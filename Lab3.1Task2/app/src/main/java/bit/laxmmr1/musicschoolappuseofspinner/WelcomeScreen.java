package bit.laxmmr1.musicschoolappuseofspinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        final Button changeActivity = (Button) findViewById(R.id.buttonA);

        ChangeActivityButtonClickHandler Bob = new ChangeActivityButtonClickHandler();
        changeActivity.setOnClickListener(Bob);
    }//end Method onCreate

    class ChangeActivityButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //New Intent Instance (CurrentActivityInstance to next/targetted Activity class)
            Intent changeActivityIntent = new Intent(WelcomeScreen.this, MainActivity.class);
            startActivity(changeActivityIntent);
        }//End Method onClick
    }//End ChangeActivity Class
}//End Main Class
