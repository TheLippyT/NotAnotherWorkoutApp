package com.example.notanotherworkoutapp.activity;

import android.os.Bundle;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notanotherworkoutapp.R;
import com.example.notanotherworkoutapp.api.fetchData;
import com.example.notanotherworkoutapp.app.MainActivity;
import com.example.notanotherworkoutapp.database.DataBaseHelper;

public class LoginActivity extends MainActivity {
//    EditText mTextUsername;
//    EditText mTextPassword;
//    Button mButtonLogin;
//    TextView mTextViewRegister;
//    DataBaseHelper db;
//    ViewGroup progressView;
//    protected boolean isProgressShowing = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar);
//        View v = this.getLayoutInflater().inflate(R.layout.progress_bar,null);
//        dialog.setContentView(v);
//        dialog.show();
//
//        db = new DataBaseHelper(this);
//        mTextUsername = (EditText)findViewById(R.id.edittext_username);
//        mTextPassword = (EditText)findViewById(R.id.edittext_password);
//        mButtonLogin = (Button)findViewById(R.id.button_login);
//        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
//        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(registerIntent);
//            }
//        });
//
//        mButtonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String user = mTextUsername.getText().toString().trim();
//                String pwd = mTextPassword.getText().toString().trim();
//                Boolean res = db.checkUser(user, pwd);
//                if(res == true)
//                {
//                    Intent HomePage = new Intent(LoginActivity.this, WorkoutAct.class);
//                    startActivity(HomePage);
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    public void showProgressingView() {
//
//        if (!isProgressShowing) {
//            View view=findViewById(R.id.progressBar1);
//            view.bringToFront();
//        }
//    }
//
//    public void hideProgressingView() {
//        View v = this.findViewById(android.R.id.content).getRootView();
//        ViewGroup viewGroup = (ViewGroup) v;
//        viewGroup.removeView(progressView);
//        isProgressShowing = false;
//    }
        DataBaseHelper myDb;

    public static TextView quotetext;
    TextView titlepage, btnexercise;
    ImageView imgpage;
    Animation animimgpage, bttone, bttwo, btthree, ltr;
    View bgprogress, bgprogresstop;
    Button inspire, deleteUser;
    private EditText User;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(this);



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
        deleteUser = (Button) findViewById(R.id.deleteUser);



        //display Rest data into the TextView
        inspire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData fetchData = new fetchData();
                fetchData.execute();
                DeleteData();

            }
        });

        btnexercise = (TextView) findViewById(R.id.btnexercise);
        imgpage = (ImageView) findViewById(R.id.imgpage);
        bgprogress = (View) findViewById(R.id.bgprogress);
        bgprogresstop = (View) findViewById(R.id.bgprogresstop);
        User = (EditText) findViewById(R.id.User);


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
        Intent a = new Intent(LoginActivity.this, WorkoutAct.class);
        a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(a);


    }
    });

    }

    public void DeleteData(){
        deleteUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRows = myDb.deleteData(User.getText().toString());
                        if(deleteRows>0)
                            Toast.makeText(LoginActivity.this,"User removed", Toast.LENGTH_LONG).show();
                         else
                             Toast.makeText(LoginActivity.this,"User not removed",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

}


