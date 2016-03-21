package bit.laxmmr1.datapassing;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button returnButton = (Button) findViewById(R.id.buttongoReturntoMain);
        editTextName = (EditText)findViewById(R.id.editText);
        returnButton.setOnClickListener(new goBack());
    }//End OnCReate

    class goBack implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            String name = editTextName.getText().toString();
            if(name.length() < 5)
            {
                Toast.makeText(SettingsActivity.this, "Username must be 5 characters long , " + name.toString(), Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent back = new Intent();
                back.putExtra("UserName",name);
                setResult(Activity.RESULT_OK, back);
                finish();
            }  //End Else
        }//End Method
    }//End Go back class
}
