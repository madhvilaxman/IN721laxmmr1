package bit.laxmmr1.musicschoolappuseofspinner;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Variables- Control Variables
    DialogFragment confirmFragment;
    private RadioGroup instrumentGroup;
    private RadioButton radioInstrumentButton;
    private Spinner spnMonths;
    private  int layoutID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConfirm = (Button) findViewById(R.id.btnEnrol);
      //  btnConfirm.setOnClickListener(new CreateFragmentButtonHandler());
       layoutID=android.R.layout.simple_spinner_item;
        instrumentGroup = (RadioGroup)findViewById(R.id.rdGroup_Instruments);
        spnMonths = (Spinner) findViewById(R.id.spnMonths);
        String[] months ={"January","February","March","April","May","June","July","August","September","October","November",
                "December" };
        TextView textViewFeedback =(TextView) findViewById(R.id.txtInstrumentMonths);
        textViewFeedback.setVisibility(View.INVISIBLE);


        ArrayAdapter<String> monthsAdapter = new ArrayAdapter<String>(this, layoutID, months);

        spnMonths.setAdapter(monthsAdapter);
        //Select a Radio Button Id and pass its value to the selectedID
        int selectedId = instrumentGroup.getCheckedRadioButtonId();

        //Then pass on the Selected ID on ot the Radionbutton Instruments
        radioInstrumentButton = (RadioButton) findViewById(selectedId);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //The Text from the RadioInstrument ->passed on to new string chosenInstrument
                String chosenInstrument = radioInstrumentButton.getText().toString();

                //Get the Selected item from the Spinner and pass to a new string selectedSpinnermonth
                String selectedSpinnerMonth = spnMonths.getSelectedItem().toString();

                //Pass the string value of the SelectedSpinnerMonth on to the String ChosenRadioMonth
                String chosenRadioMonth = selectedSpinnerMonth;

                //Pass the value of the spinner and the radio to first a string then to a Text View
                String feedbackString="You are enrolled for " +chosenInstrument +" lessons in "  + chosenRadioMonth;

                TextView textViewFeedback =(TextView) findViewById(R.id.txtInstrumentMonths);
                textViewFeedback.setText(feedbackString);

                textViewFeedback.setVisibility(View.VISIBLE);
            }
        });
    }
    public void addListenerOnButton(){

    }

}
