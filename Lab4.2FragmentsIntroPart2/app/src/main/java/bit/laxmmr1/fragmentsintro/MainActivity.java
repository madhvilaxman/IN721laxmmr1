package bit.laxmmr1.fragmentsintro;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SetonClick Button Listener
        Button btnImageFragment = (Button) findViewById(R.id.btnShowImageFragment);
        Button btnListFragment = (Button) findViewById(R.id.btnListView);

        btnImageFragment.setOnClickListener(new showPicture());
        btnListFragment.setOnClickListener(new showListView());
    }//End on create

        class showPicture implements View.OnClickListener
        {
            @Override
            public void onClick(View v)
            {
                //The Activity Instantiates a Fragment
                Fragment pictureFragment = new ShowImageFragment();
                FragmentManager fm = getFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.linearlayout,pictureFragment );
                ft.commit();
            }//endonCLickView
        }//End showpicture class

        class showListView implements View.OnClickListener
        {
            @Override
            public void onClick(View v)
            {
                Fragment listViewFragment = new ListFragment();
                FragmentManager fm = getFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.linearlayout,listViewFragment );
                ft.commit();

            }//End Method
        }//End Class ShowlistView
}//End Main Class
