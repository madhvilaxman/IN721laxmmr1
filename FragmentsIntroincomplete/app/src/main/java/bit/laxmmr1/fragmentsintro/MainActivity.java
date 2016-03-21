package bit.laxmmr1.fragmentsintro;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SetonClick Button Listner
        Button btnImageFragment = (Button) findViewById(R.id.btnShowImageFragment);
        btnImageFragment.setOnClickListener(btnImageFragment);

        btnImageFragment.setOnClickListener(new View.onClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //The Activity Instantiates a Fragment
                Fragment dynamicFragment = new ShowImageFragment();
                FragmentManager fm = getFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, dynamicFragment);
                ft.commit();
            }//endonCLickView
        });
    }//End method on create

    public class ShowImageFragment extends Fragment
    {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState)
        {
            View fragmentView = inflater.inflate(R.layout.show_image_fragment,container,false);
            return fragmentView;
        }//End OnCreateView

    }//EndofFragmentClass

    public class ShowListViewFragment extends Fragment
    {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View fragmentView = inflater.inflate(R.layout.show_listview_fragment, container, false);

            //1.Get a reference to the ListView with fragmentView.findViewById
            ListView lvCities = (ListView) fragmentView.findViewById(R.id.lvcities);

            //2.Get your array of strings
            Resources resourceMachine = getResources();
            String[] cityNamesArray = resourceMachine.getStringArray(R.array.city_names_array);

            //3.Create the Adapter
            ArrayAdapter<String>  cityNamesAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,cityNamesArray);

            //4.Pass it to the setAdapter method of the ListView
            lvCities.setAdapter(cityNamesAdapter);

            //Return the fully configured View
            return fragmentView;
        }
    }
}
