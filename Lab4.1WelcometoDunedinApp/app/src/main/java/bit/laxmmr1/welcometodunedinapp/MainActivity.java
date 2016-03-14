package bit.laxmmr1.welcometodunedinapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpActivitiesGroupList();

        final ListView changeActivity =(ListView) findViewById(R.id.listView);



    public void setUpActivitiesGroupList() {
            String[] groups = {"Services", "Fun Things To Do", "Dining", "Shopping"};
            //An ArrayAdapter
            ArrayAdapter<String> activitesAdapter = new ArrayAdapter<String>(this, R.layout.activity_main, groups);
            //ListView binded with the Adapter
            ListView activitiesGroupListView = (ListView) findViewById(R.id.listView);
            activitiesGroupListView.setAdapter(activitesAdapter);
    }

    public class ListViewWithToastHandler implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick (AdapterView<?> parent, View view, int position, long id){
            String clickedItemString = (String)parent.getItemAtPosition(position).toString();
            Toast.makeText(MainNavigation.this, clickedItemString, Toast.LENGTH_LONG.show);
        }
    }
}
}
