package bit.laxmmr1.datapassing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGoToSetting = (Button) findViewById(R.id.buttongoToSettings);
        textView2= (TextView) findViewById(R.id.textView2);
        buttonGoToSetting.setOnClickListener(new goToSettings());
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if ((requestCode == 0) && (resultCode == Activity.RESULT_OK))
        {
            String result = data.getStringExtra("UserName"); // Gets the UserName from the SettingsActivity
            textView2.setText(result);
        }//End if
    }//End method result

    class goToSettings implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //New Intent Instance (Current ActivityInstance to nect/targetted ActivityClass
            Intent changeActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(changeActivityIntent, 0);
        }//Endmethod
    }//EndClass Button clickHandler
}//endMain Class
