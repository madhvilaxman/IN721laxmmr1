package bit.laxmmr1.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_b);

    final Button changeActivity = (Button) findViewById(R.id.buttonb);

    ChangeActivityButtonClickHandler Bob1 = new ChangeActivityButtonClickHandler();
    changeActivity.setOnClickListener(Bob1);
}
    class ChangeActivityButtonClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //New Intent Instance (CurrentActivityInstance to next/targetted Activity Class)
            Intent changeActivityIntent = new Intent(ActivityB.this, ActivityC.class);
            startActivity(changeActivityIntent);
        }
    }
}
