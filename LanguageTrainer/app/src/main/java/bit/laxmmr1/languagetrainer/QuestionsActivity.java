package bit.laxmmr1.languagetrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class QuestionsActivity extends AppCompatActivity {
    //Variables declaration
    RadioGroup answerGroup;
    Button ButtonClick;
    Button ButtonNextQuestion;
    ImageView selectedImageDisplay;
    TextView progressBar;
    String progressBarString;
    int currentQuestion = 0;
    int corrextAnswer = 0;
    Random rand;
    Boolean check;
    int resourceIDs[];
    String images[] = {"der_apfel", "das_auto", "der_baum", "die_ente", "das_haus", "die_hexe", "die_kuh", "die_milch", "das_schaf", "die_strasse", "der_stuhl"};
    String articles[] = {"der", "das", "der", "die", "das", "die", "die", "die", "das", "die", "der"};

    //=================================================================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        shuffle();

        ButtonClick = (Button) findViewById(R.id.buttonSubmit);
        ButtonClick.setOnClickListener(new ButtonClickHandler());

        ButtonNextQuestion = (Button) findViewById(R.id.buttonNextQuestion);
        ButtonClick.setOnClickListener(new NextQuestionClickHandler());

        answerGroup = (RadioGroup) findViewById(R.id.rdGroup_Gender);


        //Not Sure why My Progress Questions Bar does not appear or change
        progressBar = (TextView) findViewById(R.id.textViewQuestionBar);
        progressBarString = "Question 1 of 11";
        progressBar.setText(progressBarString);

        Resources resources = getResources();
        resourceIDs = new int [images.length];

        check = false;
        //Doesnt display my image either
        for (int i = 0; i < images.length; i++)
        {
            String nameOfImage = images[i];
            int resourceID = resources.getIdentifier(nameOfImage, "drawable", getPackageName());

            resourceIDs [i] = resourceID;
        }//End for loop
        int currentResourceID = resourceIDs[currentQuestion];
        selectedImageDisplay = (ImageView) findViewById(R.id.imageView);
        selectedImageDisplay.setImageResource(currentResourceID);
    }// End On Create

    //===================================================================================

    public class NextQuestionClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
           if (check == true)
            {
               currentQuestion++;
                if (currentQuestion < images.length)
                {
                    int currentResourceID = resourceIDs[currentQuestion];
                    selectedImageDisplay.setImageResource(currentResourceID);
                    progressBarString = (currentQuestion + 1) + " of 11";
                    progressBar.setText(progressBarString);
                    check=false;
                }else
                {
                    Intent changeActivityIntent = new Intent(QuestionsActivity.this, FeedBackActivity.class);
                    String passTheData = Integer.toString(corrextAnswer);

                    changeActivityIntent.putExtra("Display Score", passTheData);
                    startActivity(changeActivityIntent);
                    check = false;
                }
            }//End If
           }
        }//End Next Questionclickhandler
    //======================================================================================================================================================

    public class ButtonClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v)
        {
            RadioButton chosenAnswer = (RadioButton) findViewById(answerGroup.getCheckedRadioButtonId());
            String chosenButton = chosenAnswer.getText().toString();

            String correctAnswer = articles[currentQuestion];

            if (check==false)
            {
                if (chosenButton.equals(correctAnswer))
                {
                    Toast.makeText(QuestionsActivity.this, chosenButton + "Is the Correct Answer", Toast.LENGTH_SHORT).show();
                    check = true;
                    corrextAnswer++;
                }else{
                    Toast.makeText(QuestionsActivity.this, chosenButton + "Is the INCORRECT Answer", Toast.LENGTH_LONG).show();
                }
            }//Endif
        }//End Onclick
    }//EndButtonClickHandler


    //============================================================================================================================================
    private void shuffle()
    {
    rand = new Random();
        for (int i=0; i <100; i++)
        {
            int num1 = rand.nextInt(11);
            int num2 = rand.nextInt(11);

            String imageDisplayed = images[num1];
            String answersDisplay = articles[num1];

            images[num1] = images[num2];
            articles[num1] = articles[num2];

            images[num2] = imageDisplayed;
            articles[num2] = answersDisplay;
        }//EndForloop
    }//End shuffle
} //End Main Class
