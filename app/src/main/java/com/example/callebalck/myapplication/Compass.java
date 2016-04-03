package com.example.callebalck.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.hardware.SensorEventListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Compass extends Activity implements SensorEventListener{
    private ImageView image;
    private float currentDegree = 0f;
    private SensorManager SM;
    //Presents the current direction
    TextView tvHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        //
        image = (ImageView)findViewById(R.id.imageView);
        tvHeading = (TextView)findViewById(R.id.textView);
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);
        tvHeading.setText("Riktning: " + Float.toString(degree) + " grader");
        //create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //how long the animation will take place
        ra.setDuration(210);
        ra.setFillAfter(true);
        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //not in use

    }

    @Override
    protected void onPause(){
        super.onPause();

        //stops the listener and saves battery
        SM.unregisterListener(this);
    }
    @Override

    protected void onResume(){
        super.onResume();
        //for the system's orientation sensor registered listener
        SM.registerListener(this, SM.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);


    }
}
