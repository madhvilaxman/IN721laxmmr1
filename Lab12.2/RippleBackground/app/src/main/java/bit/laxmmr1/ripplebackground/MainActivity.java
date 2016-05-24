package bit.laxmmr1.ripplebackground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    ImageView ivMessageIcon;
    private boolean rippleStartorStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RippleBackground rippleBackground = (RippleBackground)findViewById(R.id.content);
        ivMessageIcon =(ImageView)findViewById(R.id.centerImage);
        rippleStartorStop = false;

        ivMessageIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rippleStartorStop = !rippleStartorStop;
                if (!rippleStartorStop)
                    rippleBackground.startRippleAnimation();
                else
                    rippleBackground.stopRippleAnimation();
            }//End OnClick
        });
    }//End onCreate
}//End Main Activity
