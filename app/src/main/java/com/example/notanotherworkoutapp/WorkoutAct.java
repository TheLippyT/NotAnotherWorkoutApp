package com.example.notanotherworkoutapp;

import android.content.Intent;
import android.graphics.Typeface;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import com.example.notanotherworkoutapp.database.DataBaseHelper;

public class WorkoutAct extends AppCompatActivity implements TopFragment.ButtonListener {
    DataBaseHelper myDb;
    
    //FRAGM
    public static final int HOME_SCREEN = 0;
    public static final int StartWorkAct = 1;
    public static final int EditWorkAct = 2;


    TextView titlepage, subtitlepage, intropage, subintropage,
            btnexercise, fitonetitle, fitonedesc, fittwotitle, fittwodesc,
            fitthreetitle, fitthreedesc, fitfourtitle, fitfourdesc;
    Animation bttone, bttwo, bttfour, bttfive, bttsix, bttseven, btteight;
    View divpage, bgprogress;
    LinearLayout fitone, fittwo, fitthree, fitfour;
    Button addWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        myDb = new DataBaseHelper(this);

        myDb.getAllWorkouts();
        
        //fragment
        TopFragment topFragment = TopFragment.newInstance(WorkoutAct.HOME_SCREEN);
        topFragment.setListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //transaction.add(R.id.topFragment.newINstance(WorkoutAct.HOME_SCREEN)};
        transaction.add(R.id.topFragment, topFragment);

        transaction.commit();

        // load animations
        bttone = AnimationUtils.loadAnimation(this, R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this, R.anim.bttwo);
        bttfour = AnimationUtils.loadAnimation(this, R.anim.bttfour);
        bttfive = AnimationUtils.loadAnimation(this, R.anim.bttfive);
        bttsix = AnimationUtils.loadAnimation(this, R.anim.bttsix);
        bttseven = AnimationUtils.loadAnimation(this, R.anim.bttseven);
        btteight = AnimationUtils.loadAnimation(this, R.anim.btteight);


        titlepage = (TextView) findViewById(R.id.titlepage);
        subtitlepage = (TextView) findViewById(R.id.subtitlepage);
        intropage = (TextView) findViewById(R.id.intropage);
        subintropage = (TextView) findViewById(R.id.subintropage);
        btnexercise = (TextView) findViewById(R.id.btnexercise);
        divpage = (View) findViewById(R.id.divpage);
        bgprogress = (View) findViewById(R.id.bgprogress);


        // give an event to another page
        btnexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(WorkoutAct.this,StartWorkAct.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
        // goto editworkoutactivity
        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutAct.this, EditWorkAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });


        // item fit layout
        fitone = (LinearLayout) findViewById(R.id.fitone);
        fittwo = (LinearLayout) findViewById(R.id.fittwo);
        fitthree = (LinearLayout) findViewById(R.id.fitthree);
        fitfour = (LinearLayout) findViewById(R.id.fitfour);




        // item fit
        fitonetitle = (TextView) findViewById(R.id.fitonetitle);
        fitonedesc = (TextView) findViewById(R.id.fitonedesc);

        fittwodesc = (TextView) findViewById(R.id.fittwodesc);

        fitthreetitle = (TextView) findViewById(R.id.fitthreetitle);
        fitthreedesc = (TextView) findViewById(R.id.fitthreedesc);

        fitfourtitle = (TextView) findViewById(R.id.fitfourtitle);
        fitfourdesc = (TextView) findViewById(R.id.fitfourdesc);



        // assign the animations
        titlepage.startAnimation(bttone);
        subtitlepage.startAnimation(bttone);
        divpage.startAnimation(bttone);

        intropage.startAnimation(bttwo);
        subintropage.startAnimation(bttwo);

        fitone.startAnimation(bttwo);
        fittwo.startAnimation(bttfour);
        fitthree.startAnimation(bttfive);
        fitfour.startAnimation(bttsix);

        btnexercise.startAnimation(btteight);
        bgprogress.startAnimation(bttseven);

    }
       @Override
    public void didButtonPressed(int buttonID) {
        switch (buttonID) {
            case R.id.backBtnFragm:
                Log.e("EditWorkAct", "backBtnFregm Pressed");
                Intent intent = new Intent(this,WorkoutAct.class);
                startActivity(intent);
                Log.e("StartWorkAct", "backBtnFregm Pressed");
                intent = new Intent(this, WorkoutAct.class);
                startActivity(intent);
                Log.e("WorkoutAct", "backBtnFregm Pressed");
                intent = new Intent(this, WorkoutAct.class);
                startActivity(intent);
                break;
        }
    }
}
