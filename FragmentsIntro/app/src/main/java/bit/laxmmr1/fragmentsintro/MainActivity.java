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

        //SetonClick Button Listener
        Button btnImageFragment = (Button) findViewById(R.id.btnShowImageFragment);
        btnImageFragment.setOnClickListener(btnImageFragment);

        class BtnImageFragmentHandler implements View.onClickListener
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
        });//EndOnClickListener
    }//End method on create
}
