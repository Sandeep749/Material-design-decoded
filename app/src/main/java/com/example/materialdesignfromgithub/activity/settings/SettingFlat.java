package com.example.materialdesignfromgithub.activity.settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.materialdesignfromgithub.R;
import com.example.materialdesignfromgithub.utils.Tools;

public class SettingFlat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_flat);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }

}
