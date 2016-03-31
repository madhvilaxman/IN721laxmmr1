package bit.laxmmr1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);


        //Fetches the Intent
        Intent launchIntent = getIntent();

        //Retrieve Data
        String displayScore = launchIntent.getStringExtra("Display Score");

        //Use the data
        TextView showScore = (TextView) findViewById(R.id.textViewScore);
        showScore.setText(displayScore);

        final Button startQuizAgain = (Button) findViewById(R.id.buttonStartQuizAgain);

        StartQuizAgainButtonClickHandler newclickagain = new StartQuizAgainButtonClickHandler();
        startQuizAgain.setOnClickListener(newclickagain);
    }//End onCreate

    class StartQuizAgainButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Shows the Quiz Page
            Intent changeActivityIntent = new Intent(FeedBackActivity.this, QuestionsActivity.class);
            startActivity(changeActivityIntent);
        }//end onClick
    }//Endclicklistener
}//EndClass
