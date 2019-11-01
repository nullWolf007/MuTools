package com.mumu.mutools.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mumu.mutools.R;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        tv_title = findViewById(R.id.dialog_tv_title);

        tv_title.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_tv_title:
                break;
        }
    }
}
