package com.example.notanotherworkoutapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment implements View.OnClickListener {



    public interface ButtonListener{
        void didButtonPressed(int buttonID);
    }

    private ButtonListener listener;

    public ButtonListener getListener() {
        return listener;
    }

    public void setListener(ButtonListener listener) {
        this.listener = listener;
    }

    public static TopFragment newInstance(int screenType) {

        Bundle args = new Bundle();

        TopFragment fragment = new TopFragment();
        args.putInt("screenType", screenType);

        fragment.setArguments(args);

        return fragment;
    }

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        //TextView homeTxtFragm = view.findViewById(R.id.homeTxtFagm);

        Button backBtnFragm = view.findViewById(R.id.backBtnFragm);
        Button DarkModeBtn = view.findViewById(R.id.DarkModeBtn);

        int screenType = getArguments().getInt("screenType", -1);
        if(screenType == WorkoutAct.HOME_SCREEN){
            backBtnFragm.setVisibility(View.GONE);

            backBtnFragm.setOnClickListener(null);

        }
        else{
            backBtnFragm.setVisibility(View.VISIBLE);

            backBtnFragm.setOnClickListener(this);
        }

        return view;
    }
    @Override
    public void onClick(View view) {

        if(listener != null){
            listener.didButtonPressed(view.getId());
        }

    }
}
