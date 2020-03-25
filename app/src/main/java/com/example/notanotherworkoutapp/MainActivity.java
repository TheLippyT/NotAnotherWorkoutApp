package com.example.notanotherworkoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.notanotherworkoutapp.database.DataBaseHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //DataBaseHelper myDb;

    public static TextView quotetext;
    TextView titlepage, btnexercise;
    ImageView imgpage;
    Animation animimgpage, bttone, bttwo, btthree, ltr;
    View bgprogress, bgprogresstop;
    Button inspire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //myDb = new DataBaseHelper(this);



        // load animation
        animimgpage = AnimationUtils.loadAnimation(this, R.anim.animimgpage);
        bttone = AnimationUtils.loadAnimation(this, R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this, R.anim.bttwo);
        btthree = AnimationUtils.loadAnimation(this, R.anim.btthree);
        ltr = AnimationUtils.loadAnimation(this, R.anim.ltr);

        
        titlepage = (TextView) findViewById(R.id.titlepage);
        quotetext = (TextView) findViewById(R.id.subtitlepage);
        //inspire button
        inspire = (Button) findViewById(R.id.inspire);
        //display Rest data into the TextView
        inspire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData fetchData = new fetchData();
                fetchData.execute();

            }
        });

        btnexercise = (TextView) findViewById(R.id.btnexercise);
        imgpage = (ImageView) findViewById(R.id.imgpage);
        bgprogress = (View) findViewById(R.id.bgprogress);
        bgprogresstop = (View) findViewById(R.id.bgprogresstop);

       

        // export animate
        imgpage.startAnimation(animimgpage);
        titlepage.startAnimation(bttone);
        quotetext.startAnimation(bttone);

        btnexercise.startAnimation(btthree);
        bgprogress.startAnimation(bttwo);
        bgprogresstop.startAnimation(ltr);


    // give an event to another page
        btnexercise.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        Intent a = new Intent(MainActivity.this, LoginActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(a);


    }
    });

    }


}

