package bit.laxmmr1.easyandroidanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView ivAnimate = (ImageView) findViewById(R.id.imageView);

        Button buttonAnimate =(Button) findViewById(R.id.btnAnimate);
        buttonAnimate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                ExplodeAnimation explodeAnimation = new ExplodeAnimation(ivAnimate);
                explodeAnimation.animate();

            }
        });
    }
}
