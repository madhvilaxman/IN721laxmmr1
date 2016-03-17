package bit.laxmmr1.fragmentsintro;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by laxmmr1 on 17/03/2016.
 */

public class ShowImageFragment extends Fragment
{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState)
    {
        View fragmentView = inflater.inflate(R.layout.show_image_fragment,container,false);
        return fragmentView;
    }//End OnCreateView

}//EndofFragmentClass
