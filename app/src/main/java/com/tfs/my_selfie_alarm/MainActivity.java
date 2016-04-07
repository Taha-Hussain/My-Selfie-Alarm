package com.tfs.my_selfie_alarm;
import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Context context;
    private static final int CAMERA_REQUEST = 1888;
    Bitmap photo;
    ImageView Captured_ImageView;
    PendingIntent pendingIntent1;
    PendingIntent pendingIntent2;
    PendingIntent pendingIntent3;
    PendingIntent pendingIntent4;
    AlarmManager mgrAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        Captured_ImageView = (ImageView) findViewById(R.id.Captured_ImageView);
    }

    public void startAlert_click(View view) {

        Toast.makeText(MainActivity.this, "Alarm Is Set", Toast.LENGTH_SHORT).show();

         mgrAlarm = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

        Date dat_1  = new Date();//initializes to now
        Calendar cal_alarm_1 = Calendar.getInstance();
        Calendar cal_now_1 = Calendar.getInstance();
        cal_now_1.setTime(dat_1);
        cal_alarm_1.setTime(dat_1);
        cal_alarm_1.set(Calendar.HOUR_OF_DAY,10);//set the alarm time
        cal_alarm_1.set(Calendar.MINUTE, 0);
        cal_alarm_1.set(Calendar.SECOND, 0);
        if(cal_alarm_1.before(cal_now_1)){//if its in the past increment
            cal_alarm_1.add(Calendar.DATE,1);
        }

        Intent intent1 = new Intent(context, AlarmReceiver.class);
        // Loop counter `i` is used as a `requestCode`
        pendingIntent1 = PendingIntent.getBroadcast(context, 1, intent1, 0);
        // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
        mgrAlarm.set(AlarmManager.RTC_WAKEUP, cal_alarm_1.getTimeInMillis(), pendingIntent1);

        intentArray.add(pendingIntent1);


        Date dat_2  = new Date();//initializes to now
        Calendar cal_alarm_2 = Calendar.getInstance();
        Calendar cal_now_2 = Calendar.getInstance();
        cal_now_2.setTime(dat_2);
        cal_alarm_2.setTime(dat_2);
        cal_alarm_2.set(Calendar.HOUR_OF_DAY,15);//set the alarm time
        cal_alarm_2.set(Calendar.MINUTE, 0);
        cal_alarm_2.set(Calendar.SECOND, 0);
        if(cal_alarm_2.before(cal_now_2)){//if its in the past increment
            cal_alarm_2.add(Calendar.DATE,1);
        }


        Intent intent2 = new Intent(context, AlarmReceiver.class);
        // Loop counter `i` is used as a `requestCode`
        pendingIntent2 = PendingIntent.getBroadcast(context, 2, intent2, 0);
        // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
        mgrAlarm.set(AlarmManager.RTC_WAKEUP, cal_alarm_2.getTimeInMillis(), pendingIntent2);

        intentArray.add(pendingIntent2);


        Date dat_3  = new Date();//initializes to now
        Calendar cal_alarm_3 = Calendar.getInstance();
        Calendar cal_now_3 = Calendar.getInstance();
        cal_now_3.setTime(dat_3);
        cal_alarm_3.setTime(dat_3);
        cal_alarm_3.set(Calendar.HOUR_OF_DAY,19);//set the alarm time
        cal_alarm_3.set(Calendar.MINUTE, 0);
        cal_alarm_3.set(Calendar.SECOND, 0);
        if(cal_alarm_3.before(cal_now_3)){//if its in the past increment
            cal_alarm_3.add(Calendar.DATE,1);
        }

        Intent intent3 = new Intent(context, AlarmReceiver.class);
        // Loop counter `i` is used as a `requestCode`
        pendingIntent3 = PendingIntent.getBroadcast(context, 3, intent3, 0);
        // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
        mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, cal_alarm_3.getTimeInMillis(), pendingIntent3);

        intentArray.add(pendingIntent3);

        Intent intent4 = new Intent(context, AlarmReceiver.class);
        // Loop counter `i` is used as a `requestCode`
        pendingIntent4 = PendingIntent.getBroadcast(context, 4, intent4, 0);
        // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
        mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 5 * 1000, pendingIntent4);

        intentArray.add(pendingIntent4);
    }

    public void Selfie_click(View view)
    {
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        mgrAlarm.cancel(pendingIntent1);
        mgrAlarm.cancel(pendingIntent2);
        mgrAlarm.cancel(pendingIntent3);
        mgrAlarm.cancel(pendingIntent4);

        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();

        if (mgrAlarm!= null) {
            mgrAlarm.cancel(pendingIntent1);
            mgrAlarm.cancel(pendingIntent2);
            mgrAlarm.cancel(pendingIntent3);
            mgrAlarm.cancel(pendingIntent4);
        }

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            Captured_ImageView.setImageBitmap(photo);
        }
    }
}
