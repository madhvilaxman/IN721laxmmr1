package bit.laxmmr1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startQuiz = (Button) findViewById(R.id.btnStartQuiz);

        StartQuizButtonClickHandler newclick = new StartQuizButtonClickHandler();
        startQuiz.setOnClickListener(newclick);
    }//End onCreate

    class StartQuizButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Shows the Quiz Page
            Intent changeActivityIntent = new Intent(MainActivity.this, QuestionsActivity.class);
            startActivity(changeActivityIntent);
        }//end onClick
    }//Endclicklistener
}//End of Class
