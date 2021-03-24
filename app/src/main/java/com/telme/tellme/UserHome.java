package com.telme.tellme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserHome extends AppCompatActivity {

    private static Button freshData;
    private static Button followupList;
    private static Button myLogins;
    private static Button closedLeads;
    private static Button notAnswered;
    private String userName;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Bundle b = getIntent().getExtras();
         userName = b.getString("Username");
        password = b.getString("Password");
        takeAction();
    }

    private void takeAction() {
        freshData = (Button) findViewById(R.id.freshdata);
        followupList = (Button)findViewById(R.id.followuplist);
        myLogins = (Button)findViewById(R.id.mylogins);
        closedLeads = (Button)findViewById(R.id.myclosedleads);
        notAnswered = (Button)findViewById(R.id.mynotanswered);
        final Bundle b = new Bundle();
        b.putString("Username",userName);
        b.putString("Password",userName);
        freshData.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(UserHome.this, FreshDataActivity.class);

                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        followupList.setOnClickListener(
                    new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent myIntent = new Intent(UserHome.this, FollowUpDataActivity.class);
                            myIntent.putExtras(b);
                            startActivity(myIntent);
                        }
                    }
            );
        myLogins.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(UserHome.this, MyLoginsActivity.class);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        closedLeads.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(UserHome.this, MyClosedLeadsActivity.class);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );
        notAnswered.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(UserHome.this, UserNotAnsweredActivity.class);
                        myIntent.putExtras(b);
                        startActivity(myIntent);
                    }
                }
        );


    }
    }