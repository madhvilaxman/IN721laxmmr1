package bit.laxmmr1.multipleactivities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_c);

        final Button changeActivity = (Button) findViewById(R.id.buttonc);

        ChangeActivityButtonClickHandler Bob2 = new ChangeActivityButtonClickHandler();
        changeActivity.setOnClickListener(Bob2);
    }

    class ChangeActivityButtonClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            //New Intent Instance (Implicit intent - to launch website)
            Uri goToWebsite = Uri.parse("http://www.beautybay.com");
            Intent beautyBayIntent = new Intent(Intent.ACTION_VIEW, goToWebsite);
            startActivity(beautyBayIntent);
        }//End Method OnClick
    }//End ChangeActivity Class
}//End Main Class
