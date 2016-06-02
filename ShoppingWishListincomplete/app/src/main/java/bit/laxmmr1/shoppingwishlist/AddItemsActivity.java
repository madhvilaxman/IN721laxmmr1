package bit.laxmmr1.shoppingwishlist;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.graphics.Bitmap.createScaledBitmap;

/**
 * Created by laxmmr1 on 27/05/2016.
 */
public class AddItemsActivity extends MainActivity
{
    //Variables
    private File photoFile;
    private Uri photoFileUri;
    private String photoFileName;
    private final int REQUEST_CODE_CAMERA = 1;
    ImageView displaySavedImage;
    ListView lvWishList;
    EditText editVendor;
    EditText editPrice;
    EditText editTitle;
    ArrayList<String> strings;
    ArrayAdapter<String> adapter;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additems);

        Button takeAndSavePciture = (Button) findViewById(R.id.btnTakePicture);
        takeAndSavePciture.setOnClickListener(new PhotoIntent());

        displaySavedImage = (ImageView) findViewById(R.id.ivSavedImage);

        lvWishList = (ListView) findViewById(R.id.lvAllItems);

        editVendor = (EditText) findViewById(R.id.editVendors);
        editPrice = (EditText)findViewById(R.id.editPrice);
        editTitle = (EditText) findViewById(R.id.editTitle);

        strings = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strings);
        //lvWishList.setAdapter(adapter);

        //=======================BTN SAVE =========================================================
        btnSave = (Button) findViewById(R.id.btnSaveToWishList);
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SimpleDateFormat timeStamp = new SimpleDateFormat("EEE dd MMM yyyy HH:mm");
                Date time = new Date();
                String timeStampformat = timeStamp.format(time);

                //User must complete all the fields
                if((editPrice.getText().toString().equals("")) ||(editVendor.getText().toString().equals(""))){
                    Toast.makeText(AddItemsActivity.this, "Please fill in all the required fields", Toast.LENGTH_LONG).show();
                }else{
                    //User can put upto 2 df
                    double convertTheStringtoDouble = Double.parseDouble(editPrice.getText().toString());
                    String formatPrice = String.format("%.2f", convertTheStringtoDouble);

                    //insert and Save it to the Database
                    String query = "INSERT INTO tblPhotos (itemName, picture, price, vendor, ) VALUES('"+editTitle.getText()+"', '"+photoFileName+"','"+formatPrice+"','"+editVendor.getText()+"');";
                    //Show the info on the info Activity
                    Intent launchActivity = new Intent(AddItemsActivity.this, InfoWishListActivity.class);
                    launchActivity.putExtra("Query String", query);
                    startActivity(launchActivity);

                }//End Else
            }//End on CLick
        });//End Onclick Listener


    }//End Oncreate

    //=================PhotoIntent===============================================
    public class PhotoIntent implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {
            //Creates  the time stamped file to hold the image data
            photoFile = createTimeStampedFile();

            //Generate a URI from the file instance
            photoFileUri = Uri.fromFile(photoFile);

            //Create an intent for the image capture content provider
            Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //Attach your URI to the intent
            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFileUri);

            // /Launch the intent, waiting for result
            //User will use the camera, and onActivity Result is raised
            startActivityForResult(imageCaptureIntent, REQUEST_CODE_CAMERA);
        }//End onClick
    }//End onClickListener
    //=================Method CreateTimeStamp===============================================
    public File createTimeStampedFile()
    {
        //Fetches the system Folder Name
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //Making a subFolder
        File imageStorageDirectory = new File(imageRootPath, "Shopping Wish List");
        if (!imageStorageDirectory.exists())
        {
            imageStorageDirectory.mkdirs();
        }

        //Get timeStamp
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        //Make File name
        photoFileName = "IMAGE_" + timeStamp +".jpg";

        //Make the Photo object from directory and file name
        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + photoFileName);
        return photoFile;
    }//End createTimeStampedFile
    //=================Method onActivityResult===============================================
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        //Resizes the Bitmap
        int imageSize = 400;
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                String realFilePath = photoFile.getPath();
                Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilePath);
                displaySavedImage.setImageBitmap(userPhotoBitmap);
                userPhotoBitmap= createScaledBitmap(userPhotoBitmap, imageSize, imageSize, false);
            }
            else
            {
                Toast.makeText(this, "No Photo saved.Sorry", Toast.LENGTH_LONG).show();
            }
            }
    }//End onActivityResult
    //===================SaveItem to Wish List================================================

}//End AddItemsActivity
