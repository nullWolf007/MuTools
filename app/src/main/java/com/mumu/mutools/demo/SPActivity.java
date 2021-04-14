package com.mumu.mutools.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mumu.kernel.sp.MuSP;
import com.mumu.kernel.toast.MuToast;
import com.mumu.mutools.R;

import java.util.Calendar;

public class SPActivity extends AppCompatActivity implements View.OnClickListener {

    //SharedPreference
    private TextView tv_put_avatar_name;
    private TextView tv_get_avatar_name;
    private TextView tv_remove_avatar_name;
    private TextView tv_put_avatar_age;
    private TextView tv_get_avatar_age;
    private TextView tv_remove_avatar_age;
    private TextView tv_remove_avatar;
    private TextView tv_put_event_time;
    private TextView tv_get_event_time;
    private TextView tv_remove_event_time;
    private TextView tv_remove_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        tv_put_avatar_name = findViewById(R.id.sp_put_avatar_name);
        tv_get_avatar_name = findViewById(R.id.sp_get_avatar_name);
        tv_remove_avatar_name = findViewById(R.id.sp_remove_avatar_name);
        tv_put_avatar_age = findViewById(R.id.sp_put_avatar_age);
        tv_get_avatar_age = findViewById(R.id.sp_get_avatar_age);
        tv_remove_avatar_age = findViewById(R.id.sp_remove_avatar_age);
        tv_remove_avatar = findViewById(R.id.sp_remove_avatar);
        tv_put_event_time = findViewById(R.id.sp_put_event_time);
        tv_get_event_time = findViewById(R.id.sp_get_event_time);
        tv_remove_event_time = findViewById(R.id.sp_remove_event_time);
        tv_remove_flag = findViewById(R.id.sp_remove_time);

        tv_put_avatar_name.setOnClickListener(this);
        tv_get_avatar_name.setOnClickListener(this);
        tv_remove_avatar_name.setOnClickListener(this);
        tv_put_avatar_age.setOnClickListener(this);
        tv_get_avatar_age.setOnClickListener(this);
        tv_remove_avatar_age.setOnClickListener(this);
        tv_remove_avatar.setOnClickListener(this);
        tv_put_event_time.setOnClickListener(this);
        tv_get_event_time.setOnClickListener(this);
        tv_remove_event_time.setOnClickListener(this);
        tv_remove_flag.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sp_put_avatar_name:
                MuSP.putString(this, "avatar", "name", "nullWolf");
                break;
            case R.id.sp_get_avatar_name:
                MuToast.info(MuSP.getString(this, "avatar", "name", ""));
                break;
            case R.id.sp_remove_avatar_name:
                MuSP.clearPreferenceByNameAndKey(this, "avatar", "name");
                break;
            case R.id.sp_put_avatar_age:
                MuSP.putInt(this, "avatar", "age", 18);
                break;
            case R.id.sp_get_avatar_age:
                MuToast.info(MuSP.getInt(this, "avatar", "age", 0) + "");
                break;
            case R.id.sp_remove_avatar_age:
                MuSP.clearPreferenceByNameAndKey(this, "avatar", "age");
                break;
            case R.id.sp_remove_avatar:
                MuSP.clearPreferenceByName(this, "avatar");
                break;
            case R.id.sp_put_event_time:
                MuSP.putLong(this, "time", "event_time", Calendar.getInstance().getTime().getTime());
                break;
            case R.id.sp_get_event_time:
                MuToast.info(MuSP.getLong(this, "time", "event_time", 0L) + "");
                break;
            case R.id.sp_remove_event_time:
                MuSP.clearPreferenceByNameAndKey(this, "time", "event_time");
                break;
            case R.id.sp_remove_time:
                MuSP.clearPreferenceByName(this, "time");
                break;
        }
    }
}
