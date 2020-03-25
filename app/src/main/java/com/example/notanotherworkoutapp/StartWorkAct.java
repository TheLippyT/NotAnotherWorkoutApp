package com.example.notanotherworkoutapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import static android.Manifest.permission.FOREGROUND_SERVICE;

public class StartWorkAct extends WorkoutAct implements TopFragment.ButtonListener {

    TextView intropage, subintropage, fitonetitle, fitonedesc, btnexercise;
    View divpage, bgprogress;
    //    ImageView imgtimer;
    LinearLayout fitone;
    private TextView showTime;
    private EditText enterTime;
    private Button start;

    Animation btthree, bttfour, ttbone, ttbtwo, alphagogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_work);
        // permission for foreground service
        ActivityCompat.requestPermissions(this, new String[]{FOREGROUND_SERVICE}, PackageManager.PERMISSION_GRANTED);
        showTime = (TextView) findViewById(R.id.showTime);
        enterTime = (EditText) findViewById(R.id.enterTime);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Counter");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Integer integerTime = intent.getIntExtra("TimeRemaining", 0);
                showTime.setText(integerTime.toString());
            }
        };
        registerReceiver(broadcastReceiver, intentFilter);

        TopFragment topFragment = TopFragment.newInstance(WorkoutAct.StartWorkAct);
        topFragment.setListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //transaction.add(R.id.topFragment.newINstance(WorkoutAct.HOME_SCREEN)};
        transaction.add(R.id.topFragment, topFragment);

        transaction.commit();


        // load animations
        btthree = AnimationUtils.loadAnimation(this, R.anim.btthree);
        bttfour = AnimationUtils.loadAnimation(this, R.anim.bttfour);
        ttbone = AnimationUtils.loadAnimation(this, R.anim.ttbone);
        ttbtwo = AnimationUtils.loadAnimation(this, R.anim.ttbtwo);
        alphagogo = AnimationUtils.loadAnimation(this, R.anim.alphagogo);



        intropage = (TextView) findViewById(R.id.intropage);
        subintropage = (TextView) findViewById(R.id.subintropage);
        btnexercise = (TextView) findViewById(R.id.btnexercise);
        fitonetitle = (TextView) findViewById(R.id.fitonetitle);
        fitonedesc = (TextView) findViewById(R.id.fitonedesc);
//        timerValue = (TextView) findViewById(R.id.timerValue);
        btnexercise = (TextView) findViewById(R.id.btnexercise);

        divpage = (View) findViewById(R.id.divpage);
        bgprogress = (View) findViewById(R.id.bgprogress);
        fitone = (LinearLayout) findViewById(R.id.fitone);
//        imgtimer = (ImageView) findViewById(R.id.imgtimer);


        // assign animation
        btnexercise.startAnimation(bttfour);
        bgprogress.startAnimation(btthree);
        fitone.startAnimation(ttbone);
        intropage.startAnimation(ttbtwo);
        subintropage.startAnimation(ttbtwo);
        divpage.startAnimation(ttbtwo);
//        timerValue.startAnimation(alphagogo);
//        imgtimer.startAnimation(alphagogo);

//        startTimer();

        // give an event to another page
        btnexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(StartWorkAct.this,EditWorkAct.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });


    }
    public void startButton(View view){
        Intent intentService = new Intent(this, TimerService.class);
        Integer integerTimeSet = Integer.parseInt(enterTime.getText().toString());
        intentService.putExtra("TimeValue", integerTimeSet);
        startService(intentService);
    }


}