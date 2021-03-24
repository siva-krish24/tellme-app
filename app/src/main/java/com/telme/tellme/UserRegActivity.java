package com.telme.tellme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telme.tellme.rest.UserRestUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class UserRegActivity extends AppCompatActivity {
    private static Button signUp_Button;
    private static String username;
    private static String password;
    private static String mobile;
    private static String email;
    private List<User> users = new ArrayList<>();
    private static final String  TAG  = "UserRegActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            UserRestUtil.fetchData("getusers",users);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        signup();

    }

    private void signup() {
        signUp_Button = (Button)findViewById(R.id.register);
        signUp_Button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                try {
                    if(register(v)){
                    Intent myIntent = new Intent(UserRegActivity.this,LoginActivity.class);
                    startActivity(myIntent);
                }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean register(View view) throws IOException, JSONException {
         username = ((EditText)findViewById(R.id.username)).getText().toString();
         password = ((EditText)findViewById(R.id.editTextTextPassword)).getText().toString();
         mobile =  ((EditText)findViewById(R.id.mobile)).getText().toString();
         email = ((EditText)findViewById(R.id.email)).getText().toString();
         User user  = new User(username,password,mobile,email,"user");
        Log.d("user",user.toString());
        if(username.equals("")||username==null)   {
            Toast.makeText(this,"UserName is Empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.equals("")||password==null){
            Toast.makeText(this,"Password is Empty",Toast.LENGTH_SHORT).show();

            return false;
        }
        if(mobile.equals("")||mobile==null){
            Toast.makeText(this,"Mobileno is Empty",Toast.LENGTH_SHORT).show();

            return false;
        }
        if(email.equals("")||email==null) {
            Toast.makeText(this,"Email is Empty",Toast.LENGTH_SHORT).show();
            return false;
        }
       if (((int)users.stream().filter(entity -> entity.getName().equals(username)).count())>0)
           Toast.makeText(this,"UserId exists please choose another one".toString(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Registration Success".toString(),Toast.LENGTH_LONG).show();
        UserRestUtil.uploadUser(user);
        return true;
    }


}