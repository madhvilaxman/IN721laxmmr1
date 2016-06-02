package bit.laxmmr1.shoppingwishlist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by laxmmr1 on 29/05/2016.
 */
public class InfoWishListActivity extends MainActivity
{
    private SQLiteDatabase PhotoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_wish_lists);

        //Create an Array of Items
        ArrayList<String> items = new ArrayList<String>();

        //Get the intent from the Listed Items on List view
        Intent getIntent = getIntent();

        //Fetch the file name of the picture
        String photoFileName = getIntent.getStringExtra("Photo File Name");

        //Fetch the path for the image
       File imageFile = new File("/Shopping Wish List/" + photoFileName);

        //Save the information to a string
        String vendorName = getIntent.getStringExtra("Vendor Name");
        String Price = getIntent.getStringExtra("Item Price");
        String Title = getIntent.getStringExtra("Title Saved");

        //TextView Variables and setting text assigned to it
        TextView vendor = (TextView) findViewById(R.id.tvVendor);
        vendor.setText(vendorName);

        TextView price = (TextView) findViewById(R.id.tvPrice);
        price.setText(Price);

        TextView title  = (TextView) findViewById(R.id.tvTitle);
        title.setText(Title);

        //Get the image and save it here
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        ImageView savedImage = (ImageView) findViewById(R.id.imageViewPhoto);
        savedImage.setImageBitmap(bitmap);

        //On button Click (Back to Add or View)
        Button backtoWishList = (Button) findViewById(R.id.btnBackToWishList);
        backtoWishList.setOnClickListener(new BackToWishList());
    }//End Oncreate
//================================BacktoADDorVIEW WISHLIST====================================
    public class BackToWishList implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent takeusertoAddorViewItemsActivity = new Intent(InfoWishListActivity.this, AddOrViewWishListActivity.class);
            startActivity(takeusertoAddorViewItemsActivity);
        }//End on Click
    }//End Listener

}//End Main Activity
