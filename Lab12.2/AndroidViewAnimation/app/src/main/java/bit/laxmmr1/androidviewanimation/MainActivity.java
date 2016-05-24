package bit.laxmmr1.androidviewanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageAnimate = (ImageView) findViewById(R.id.imageView);
        Button btnAnimate = (Button) findViewById(R.id.buttonAnimate);

        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                YoYo.with(Techniques.StandUp).duration(3000).playOn(imageAnimate);
            }
        });
    }
}
