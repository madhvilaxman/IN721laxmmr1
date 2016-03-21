package bit.laxmmr1.fragmentsintro;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by laxmmr1 on 17/03/2016.
 */

public class ShowListViewFragment extends Fragment
{
    public  ShowListViewFragment(){

    }//Empty Constructor

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
        ArrayAdapter<String> cityNamesAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,cityNamesArray);

        //4.Pass it to the setAdapter method of the ListView
        lvCities.setAdapter(cityNamesAdapter);

        //Return the fully configured View
        return fragmentView;
    }
}