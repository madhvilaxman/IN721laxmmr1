package nz.ac.op.paffjj1student.groupapp;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static nz.ac.op.paffjj1student.groupapp.R.anim.animation_imageview;

public class CheckSensorsActivity extends AppCompatActivity {

    private TextView lightView;
    private TextView tiltView;
    private SensorManager mLightSensorManager;
    private SensorManager mTiltSensorManager;
    private Sensor mLight;
    private Sensor mTilt;
    private ImageView ivAnimateImage;
    public static int x;
    public static int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sensors);

        lightView = (TextView) findViewById(R.id.textViewLight);
        tiltView = (TextView) findViewById(R.id.textViewTilt);

        //Light and Tilt Sensor managers
        mLightSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mTiltSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mLight = mLightSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mTilt = mTiltSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        ivAnimateImage =(ImageView) findViewById(R.id.imageView);


    }//End on Create
//--------------------------------------------------------------------------------------
    protected void onResume() {
        super.onResume();
        mLightSensorManager.registerListener(new LightSensorChanged(), mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mTiltSensorManager.registerListener(new TiltSensorChanged(), mTilt, SensorManager.SENSOR_DELAY_NORMAL);
    }//End onResume

    protected void onPause() {
        super.onPause();
        mLightSensorManager.unregisterListener(new LightSensorChanged());
        mTiltSensorManager.unregisterListener(new TiltSensorChanged());
    }//End onPause

    //=========LightSensorChanged Event Listener ========================================
    private class LightSensorChanged implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            String display = value + "";
            lightView.setText(display);
        }//End onSensorChanged

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }//End LightSensorChaged

    //================================================================================
    //=========TiltSensorChanged Event Listener ========================================
    private class TiltSensorChanged implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            tiltView.setText("x: " + x + ", y: " + y + ", z: " + z);

            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                x -= (int) Math.pow(event.values[0],1);
                y += (int) Math.pow(event.values[1], 1);
            }

        }//EndonSensorChanged

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }//End onAccuracyChanged
//========================================================================================
}//EndMainActivity
