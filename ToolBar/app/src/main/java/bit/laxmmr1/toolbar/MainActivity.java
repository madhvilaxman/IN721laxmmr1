package bit.laxmmr1.toolbar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvText = (TextView) findViewById(R.id.textView);

    }//End on create

        @Override
        public boolean onCreateOptionsMenu (Menu menu)
        {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.colour_menu_list, menu);
            return super.onCreateOptionsMenu(menu);

        }//End onCreateOptionsMenu

        public boolean onOptionsItemSelected(MenuItem item) {

            String itemTitle = (String) item.getTitle();

        switch (itemTitle) {
            case "Email":
                tvText.setText(R.string.Check_your_email);
                break;
            case "Messenger":
                tvText.setText(R.string.Check_your_messages);
                break;
            case "Phone":
                tvText.setText(R.string.Check_your_missedcalls);
                break;
        }// End Switch

        return true;
    }//End onOptionsItemSelected
}//End Main Activity

