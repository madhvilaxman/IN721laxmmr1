package bit.laxmmr1.week81task1externaldatastorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    //Declaring the Variables
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSetLanguage = (Button) findViewById(R.id.btnSetLanguage);
        //languageGroup = (RadioGroup) findViewById(R.id.rdGroup_Laguages);

        prefs = getSharedPreferences("prefsLab", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        //Fetches the Stored Language Preference
        //Return null is the provided Key-Value pair hasntbeen set

        String languagePreference = prefs.getString("language",null);
        if (languagePreference != null)
        {
            String greetingInChosenLanguage = getGreeting(languagePreference);

            TextView tvGreeting = (TextView) findViewById(R.id.tvChosenLanguage);
            tvGreeting.setText(greetingInChosenLanguage);

        }//End If

        buttonSetLanguage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Find out which language radio button is selected
                RadioGroup rg =(RadioGroup) findViewById(R.id.rdGroup_Laguages);
                int selectedID = rg.getCheckedRadioButtonId();
                RadioButton radioLanguageButoon = (RadioButton) findViewById(selectedID);
                String checkedLanguage = radioLanguageButoon.getText().toString();

                prefsEditor.putString("language", checkedLanguage);
                prefsEditor.commit();

            }//EndOnclick Method
        });//End ButtonCLickHandler
    }//EnOncreate

    private String getGreeting(String languagePreference) {
        String greeting = "";

        switch (languagePreference)
        {
            case ("French"):
                greeting = "Bonjour Le Monde";
                break;

            case ("German"):
                greeting = "Hallo Welt";
                break;

            case ("Spanish"):
                greeting = "Hola Mundo";
                break;
            default:
                break;
        }
        return greeting;
    }//End String

    public void addListenerOnButton()
    {

    }//End Listener
}//End Class
