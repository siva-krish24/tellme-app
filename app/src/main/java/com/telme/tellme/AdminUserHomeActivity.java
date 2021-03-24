package com.telme.tellme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminUserHomeActivity extends AppCompatActivity {

    private static Button followups;
    private static Button logins;
    private static Button leadsclosed;
    private static Button userstats;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_home);
        Bundle b = getIntent().getExtras();
        userName = b.getString("Username");
        takeAction();
    }

    private void takeAction() {
        followups = (Button) findViewById(R.id.adminuserfollowups);
        logins = (Button)findViewById(R.id.adminusermylogins);
        leadsclosed = (Button)findViewById(R.id.adminuserleadclosed);
        userstats = (Button)findViewById(R.id.adminuserfstats);
        final Bundle b = new Bundle();
        b.putString("Username",userName);
        followups.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminUserHomeActivity.this, AdminUserFollowUpsActivity.class);

                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        logins.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminUserHomeActivity.this, AdminUserMyLoginsActivity.class);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        leadsclosed.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminUserHomeActivity.this, AdminUserMyClosedLeadsActivity.class);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        userstats.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(AdminUserHomeActivity.this, AdminUserDayListActivity.class);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );

    }

}