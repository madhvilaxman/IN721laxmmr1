package bit.laxmmr1.welcometodunedinapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpActivitiesGroupList();

        ListView changeActivity = (ListView) findViewById(R.id.listView);
        changeActivity.setOnItemClickListener(new ListViewClickHandler());
    }//end Method OnCreate

    public void setUpActivitiesGroupList()
    {
            String[] groups = {"Services", "Fun Things To Do", "Dining", "Shopping"};
            //An ArrayAdapter
            ArrayAdapter<String> activitesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
            //ListView binded with the Adapter
            ListView activitiesGroupListView = (ListView) findViewById(R.id.listView);
            activitiesGroupListView.setAdapter(activitesAdapter);
    }//End Method SetupActivities

    public class ListViewClickHandler implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick (AdapterView<?> parent, View view, int position, long id)
        {
            String clickedItemString = parent.getItemAtPosition(position).toString();
            Intent goToIntent;

            switch (clickedItemString)
            {
                case "Services":
                    goToIntent = new Intent(MainActivity.this, ServicesActivity.class);
                    break;
                case "Fun Things To Do":
                    goToIntent = new Intent(MainActivity.this, FunThingsTodo.class);
                    break;
                case "Dining":
                    goToIntent = new Intent(MainActivity.this, DiningActivity.class);
                    break;
                case "Shopping":
                    goToIntent = new Intent(MainActivity.this, Shopping.class);
                    break;
                  default:
                      goToIntent = null;
            }//end Switch

            if (goToIntent != null)
                startActivity(goToIntent);

        }//End Method onItemClick
    }//End Class ListView with Handler
}//End Main Activity
