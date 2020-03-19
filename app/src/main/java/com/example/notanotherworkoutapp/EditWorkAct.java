package com.example.notanotherworkoutapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notanotherworkoutapp.database.DataBaseHelper;

public class EditWorkAct extends AppCompatActivity {

    DataBaseHelper myDb;

    TextView titlepage, subtitlepage, fitonetitle, fitonedesc,
            workvalue, fittwotitle, fittwodesc, btnexercise;

    Button btnlocked, btnadd, btnremove;

    View divpage, bgprogress;

    LinearLayout fitone, fittwo;

    Animation bttone, bttwo, bttfour, bttfive;

    int sumworkout = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_work);
        myDb = new DataBaseHelper(this);

        // load animations
        bttone = AnimationUtils.loadAnimation(this, R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this, R.anim.bttwo);
        bttfour = AnimationUtils.loadAnimation(this, R.anim.bttfour);
        bttfive = AnimationUtils.loadAnimation(this, R.anim.bttfive);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);

        fitone = findViewById(R.id.fitone);
        fittwo = findViewById(R.id.fittwo);

        divpage = findViewById(R.id.divpage);
        bgprogress = findViewById(R.id.bgprogress);

        fitonetitle = findViewById(R.id.fitonetitle);
        fitonedesc = findViewById(R.id.fitonedesc);
        fittwotitle = findViewById(R.id.fittwotitle);
        fittwodesc = findViewById(R.id.fittwodesc);

        btnlocked = findViewById(R.id.btnlocked);
        btnadd = findViewById(R.id.btnadd);
        btnremove = findViewById(R.id.btnremove);

        workvalue = findViewById(R.id.workvalue);

        btnexercise = findViewById(R.id.btnexercise);

       // myDb.addWorkout();
       // myDb.addExercise();
        //myDb.addExerciseToWorkout();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumworkout+=1;
                workvalue.setText(sumworkout+"");
                //myDb.addWorkout();
            }
        });

        btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sumworkout <= 0){
                    Toast.makeText(getApplicationContext(), "Sorry!", Toast.LENGTH_SHORT).show();
                } else {
                    sumworkout-=1;
                    workvalue.setText(sumworkout+"");
                    //myDb.delete();
                }
            }
        });

        // assign the animations
        titlepage.startAnimation(bttone);
        subtitlepage.startAnimation(bttone);
        divpage.startAnimation(bttone);

        fitone.startAnimation(bttwo);
        fittwo.startAnimation(bttfour);

        btnexercise.startAnimation(bttfive);
        bgprogress.startAnimation(bttfive);

    }

    public void addWorkout(){
        btnadd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //myDb.addExerciseToWorkout();
                    }
                }
        );
    }

    public  void deleteWorkout(){
        btnremove.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //myDb.delete();
                    }
                }
        );
    }

    public void updateData(){
        btnadd.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //myDb.updateExercise();
                    }
                }
        );
    }

}
