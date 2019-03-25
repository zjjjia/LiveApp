package com.example.jiabo.liveapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jiabo.liveapp.R;

public class CreateLiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_live);
    }

    @Override
    public void onPause(){
        super.onPause();

        this.finish();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

}
