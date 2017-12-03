package com.example.x550j.mysensor;

import android.content.pm.ActivityInfo;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{
    SensorManager sm;
    Sensor sr;
    TextView txv;
    ImageView igv;
    RelativeLayout layout;
    double mx=0,my=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        sm=(SensorManager)getSystemService(SENSOR_SERVICE);

        sr=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        txv=(TextView)findViewById(R.id.txvIno);

        igv=(ImageView)findViewById(R.id.igvMove);

        layout=(RelativeLayout)findViewById(R.id.layout);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(mx==0){
            mx=(layout.getWidth()-igv.getWidth())/20.0;

            my=(layout.getHeight()-igv.getHeight())/20.0;
        }
        RelativeLayout.LayoutParams parms=(RelativeLayout.LayoutParams)igv.getLayoutParams();

        parms.leftMargin=(int) ((10-event.values[0])*mx);

        parms.topMargin=(int)((10+event.values[1])*my);

        igv.setLayoutParams(parms);

        txv.setText(String.format("X軸: %1.2f , Y軸: %1.2f, Z軸: %1.2f",event.values[0]event.values[1],event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {    }
    @Override
    protected void onResume(){
        super.onResume();
        sm.unregisterListener(this,sr,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause(){
        super.onPause();
        sm.unregisterListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu;
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }
}
