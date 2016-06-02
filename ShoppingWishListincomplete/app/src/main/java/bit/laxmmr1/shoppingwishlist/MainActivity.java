package bit.laxmmr1.shoppingwishlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addorViewWishList = (Button) findViewById(R.id.buttonAddorViewWishList);
        addorViewWishList.setOnClickListener(new AddorViewWishList());

    }//end Method On Create

    public class AddorViewWishList implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent changeActivity = new Intent(MainActivity.this, AddOrViewWishListActivity.class);
            startActivity(changeActivity);
        }//End Onclick
    }//End OnClick Listener
}//End Main Activity


