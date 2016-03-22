package bit.laxmmr1.musicschoolappuseofspinner;

import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by laxmmr1 on 21/03/2016.
 */
public class MusicSchoolDialog extends DialogFragment
{
    MainActivity mainActivity;
    public MusicSchoolDialog(){} //Empty Constructor

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mainActivity =(MainActivity)getActivity();

        //builder.setIcon(R.drawable.musicnew);
        builder.setTitle("Do you Really want to ENROL? ");
        builder.setPositiveButton("YES", new YesButtonHandler());
        builder.setNegativeButton("NO", new NoButtonHandler());

        Dialog customDialog = builder.create();
        return customDialog;
    }//EndDialogs Oncreate

    //======================Button Handlers=============================================
    public class YesButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            mainActivity =(MainActivity)getActivity();
            mainActivity.giveMeMyData(true);
            mainActivity.setTitle("Thank you For Enrolling");
        }//End Method

    }//End class Yesbuttonhandler
    //====================================================================================
    public class NoButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            mainActivity =(MainActivity)getActivity();
            mainActivity.giveMeMyData(false);
            mainActivity.setTitle("Thank you, May be Next Time");
        }//end Method
    }//End Nobutton Handler Class
}//End Class
