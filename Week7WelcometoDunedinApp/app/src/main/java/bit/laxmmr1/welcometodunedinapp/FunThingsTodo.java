package bit.laxmmr1.welcometodunedinapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FunThingsTodo extends AppCompatActivity
{
    CustomClassFunThingsToDo[] CustomClassFunThingsToDoArray;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_things_todo);

        initialiseDataArray();

        //Creating Custom Adapter
        CustomArrayAdapter CustomAdapter = new CustomArrayAdapter(this,R.layout.custom_listview_item,CustomClassFunThingsToDoArray);

        //Binding ListView to the Adapter
        ListView lvFunthingsToDo = (ListView) findViewById(R.id.lvFunthingsToDo);
        lvFunthingsToDo.setAdapter(CustomAdapter);
    }//End on Create

    //===================================================================================================================
    public void initialiseDataArray()
    {
        Resources resourceMachine = getResources();

        Drawable larnachCastleImage = resourceMachine.getDrawable(R.drawable.larnach_castle,null);
        Drawable moanaPoolImage = resourceMachine.getDrawable(R.drawable.moana_pool, null);
        Drawable monarchCruiseImage = resourceMachine.getDrawable(R.drawable.monarch, null);
        Drawable octagonImage = resourceMachine.getDrawable(R.drawable.octagon, null);
        Drawable OlvestonImage = resourceMachine.getDrawable(R.drawable.olveston, null);
        Drawable peninsulaImage = resourceMachine.getDrawable(R.drawable.peninsula, null);

        //Initiliase the Data Array
        CustomClassFunThingsToDoArray = new CustomClassFunThingsToDo[6];

        CustomClassFunThingsToDoArray[0] = new CustomClassFunThingsToDo("Larnach Castle", larnachCastleImage);
        CustomClassFunThingsToDoArray[1] = new CustomClassFunThingsToDo("Moana Pool", moanaPoolImage);
        CustomClassFunThingsToDoArray[2] = new CustomClassFunThingsToDo("MonarchCruise", monarchCruiseImage);
        CustomClassFunThingsToDoArray[3] = new CustomClassFunThingsToDo("Octagon", octagonImage);
        CustomClassFunThingsToDoArray[4] = new CustomClassFunThingsToDo("Olveston", OlvestonImage);
        CustomClassFunThingsToDoArray[5] = new CustomClassFunThingsToDo("Peninsula",peninsulaImage );
    }//end method  initiliaseDataArray
    //======================================================================================================================
    public class CustomArrayAdapter extends ArrayAdapter<CustomClassFunThingsToDo>
    {
        public CustomArrayAdapter(Context context, int resource, CustomClassFunThingsToDo[] objects)
        {
            super(context, resource, objects);
        }//end of MethodConstructor

        public View getView(int position, View convertView, ViewGroup container)
        {
            //Get an inflater from the Activity
            LayoutInflater inflater = LayoutInflater.from(FunThingsTodo.this);

            //Inflate the customview
            View customView = inflater.inflate(R.layout.custom_listview_item, container, false);

            //Grab references to its control
            ImageView itemImageView = (ImageView) customView.findViewById(R.id.ivItemImage);
            TextView itemTextView = (TextView) customView.findViewById(R.id.tvItemWords);

            //Get the Currentinput item ( the CustomFunThingstodo class)from the array
            CustomClassFunThingsToDo currentItem = getItem(position);

            //Use the Instance data to initialise the controls
            itemImageView.setImageDrawable(currentItem.imagesForActivities);
            itemTextView.setText(currentItem.toString());

            //Return the View
            return customView;
        }//End getView
    }//End of ClassCustomAdapter
}//End Class
