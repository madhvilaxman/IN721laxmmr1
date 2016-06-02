package bit.laxmmr1.shoppingwishlist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by laxmmr1 on 28/05/2016.
 */
public class AddOrViewWishListActivity extends MainActivity
{

    private SQLiteDatabase PhotoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_or_view_wishlist);

        Button addWishList = (Button) findViewById(R.id.btnAddWishList);
        addWishList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent changetoadditemsActivity = new Intent(AddOrViewWishListActivity.this, AddItemsActivity.class);
                startActivity(changetoadditemsActivity);
            }//End Onclick
        });//End Listener

        //=============================================================
        TextView textNoItem = (TextView)findViewById(R.id.tvNoItems);
        ArrayList<String> titles = new ArrayList<String>();

        //Refering to the Intent
        Intent intentPreviouslyLaunched = getIntent();

        //Creating a Database
        PhotoDB = openOrCreateDatabase("PhotoDB",MODE_PRIVATE,null);
        Toast.makeText(this, intentPreviouslyLaunched.toString(), Toast.LENGTH_LONG).show();

        //Delete table if it exists
        String dropQuery = "DROP TABLE IF EXISTS tblPhotos";
        PhotoDB.execSQL(dropQuery);

        //Create Table
        String createQuery = " CREATE TABLE IF NOT EXISTS tblPhotos (pictureID INTEGER PRIMARY KEY AUTOINCREMENT,"+ "itemName TEXT NOT NULL, picture TEXT NOT NULL, vendor TEXT NOT NULL, price TEXT NOT NULL);";
        PhotoDB.execSQL(createQuery);

        //Gets the Insert String from the Main Activity
        String query = intentPreviouslyLaunched.getStringExtra("Query String");
        //If Main Activity saved data
        if(query != null)
        {
            PhotoDB.execSQL(query);
            titles = GetData();
        }
        else{       //Show No ITEMS if nothing has been saved
            textNoItem.setText(" No Items To Display");

        }
        if(titles.size() > 1){
            Toast.makeText(this, "More than one item in this array", Toast.LENGTH_LONG).show();
        }

        //Array Adapter for List View
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        //Connect it with the listview
        ListView itemList = (ListView) findViewById(R.id.lvAllItems);
        itemList.setAdapter(adapter);
        itemList.setOnItemClickListener(new AdapterListClickHandler());
    }//End Oncreate

    //==========================Method GETDATA()=================================================
    public  ArrayList<String> GetData()
    {
        //Creae an Array List
        ArrayList<String> items = new ArrayList<String>();

        //Get the name from Database
        String selectQuery = "SELECT vendors FROM tblPhotos";
        final Cursor recordSet = PhotoDB.rawQuery(selectQuery, null);
        recordSet.moveToFirst();

        //Put the items into an array
        for (int i = 0; i<recordSet.getColumnCount(); i++){
            items.add(recordSet.getString(i));
        }
        return  items;
    }//End GetData()
    //========================================================================
    public class AdapterListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            ArrayList<String> items = new ArrayList<String>();
            int dbID = position + 1;

            //Select the items from the query
            String selectQuery = " SELECT itemName, picture, vendor, price FROM tblPhotos WHERE pictureID == " +dbID;

            final Cursor recordSet = PhotoDB.rawQuery(selectQuery, null);
            recordSet.moveToFirst();

            //Get the First Item "itemName" and return to a string
            String itemName = recordSet.getString(0);
            String photoFileName = recordSet.getString(1);
            String vendor = recordSet.getString(2);
            String price = recordSet.getString(3);


            //Intent and add the information to send it to the next Activity where it is viewed
            Intent newIntent = new Intent(AddOrViewWishListActivity.this, InfoWishListActivity.class);
            newIntent.putExtra("itemName", itemName);
            newIntent.putExtra("photoFileName", photoFileName);
            newIntent.putExtra("vendor", vendor);
            newIntent.putExtra("price", price);


            startActivity(newIntent);
        }//End onItemClick
    }//end AdapterListCLickListener
}//End Main Activity

