package com.mumu.mutools.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mumu.kernel.ui.view.sidebar.MuAZSidebar;
import com.mumu.mutools.R;

public class AZSidebarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azsidebar);

        MuAZSidebar sidebar = findViewById(R.id.activity_azsidebar);
        sidebar.setOnTouchLetterChangeListener(new MuAZSidebar.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                Log.i("点击", "onLetterChange: " + letter);
            }
        });
    }
}
