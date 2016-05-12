package bit.laxmmr1.lab111photomosaic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnImageView;
    ImageView displayImage1;
    ImageView displayImage2;
    ImageView displayImage3;
    ImageView displayImage4;
    String PhotoFileName;
    File photoFile;
    Uri photoFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnImageView = (Button) findViewById(R.id.buttonViewImage);
        displayImage1 = (ImageView)findViewById(R.id.imageView1);
        displayImage2 = (ImageView)findViewById(R.id.imageView2);
        displayImage3 = (ImageView)findViewById(R.id.imageView3);
        displayImage4 = (ImageView)findViewById(R.id.imageView4);

        btnImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creates time stamped file to hold the image data
               photoFile = createImageFile();

                //Generate Uri from the File Instance
                photoFileUri = Uri.fromFile(photoFile);

                //Create an intent for the image capture content provider
                Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //Attach your URI to the intent
                imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFileUri);

                //Launch the intent, waiting for result
                //User will use the camera, and onActivity Result is raised
                startActivityForResult(imageCaptureIntent,1);

            }//End onClick
        });//End onClickListener
    }//End Oncreate

    private File createImageFile()
    {
        //Fetches the system Folder Name
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //Making a subFolder
        File imageStorageDirectory = new File(imageRootPath, "PhotoMosaic");
        if (!imageStorageDirectory.exists())
        {
            imageStorageDirectory.mkdirs();
        }
        //Get timeStamp
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        //Make File name
        PhotoFileName = "IMAGE_" + timeStamp +".jpg";

        //Make the Photo object from directory and file name
        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + PhotoFileName);
        return photoFile;
    }//end method createImageFile

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data)
    {
       if(requestCode ==1)
       {
           if(resultCode == RESULT_OK)
           {
               String realFilePath = photoFile.getPath();
               Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilePath);
               displayImage1.setImageBitmap(userPhotoBitmap);
               displayImage2.setImageBitmap(userPhotoBitmap);
               displayImage3.setImageBitmap(userPhotoBitmap);
               displayImage4.setImageBitmap(userPhotoBitmap);
           }
           else
           {
               Toast.makeText(this, "No Photo saved.Sorry", Toast.LENGTH_LONG).show();
           }
           }
    }//End method onActivityResult
}//End Main Activity
