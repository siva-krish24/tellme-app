package com.telme.tellme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminUserDayListActivity extends AppCompatActivity {
    private static Button today;
    private static Button yesterday;
    private static Button daybyd;
    private static Button lastweek;
    private static Button lastmonth;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_day_list);
        Bundle b = getIntent().getExtras();
        userName = b.getString("Username");
        takeAction();
    }
    private void takeAction() {
        today = (Button) findViewById(R.id.today);
        yesterday = (Button)findViewById(R.id.yesterday);
        daybyd = (Button)findViewById(R.id.daybeforeyesterday);
        lastweek = (Button)findViewById(R.id.lastweek);
        lastmonth = (Button)findViewById(R.id.lastmonth);
        final Bundle b = new Bundle();
        b.putString("Username",userName);
        today.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminUserDayListActivity.this, AdminUserStatsActivity.class);
                        b.putInt("days",0);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        yesterday.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminUserDayListActivity.this, AdminUserStatsActivity.class);
                        b.putInt("days",1);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        daybyd.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminUserDayListActivity.this, AdminUserStatsActivity.class);
                        b.putInt("days",2);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        lastweek.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminUserDayListActivity.this, AdminUserStatsActivity.class);
                        b.putInt("days",7);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        lastmonth.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminUserDayListActivity.this, AdminUserStatsActivity.class);
                        b.putInt("days",30);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );

    }

}