package com.telme.tellme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.lang.UScript;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {

    private static EditText username;
    private static EditText password;
    private static Button login_button;
    private static Button signUp_Button;
    private List<User> users=new ArrayList<>();
    private static final String  TAG  = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        UserRestUtil.fetchData("getusers",users);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LoginButton();

    }



    public void LoginButton(){
        username = (EditText) findViewById(R.id.username);
        password = (EditText)findViewById(R.id.editTextTextPassword);
        login_button = (Button)findViewById(R.id.login);
        signUp_Button = (Button)findViewById(R.id.signup);
        signUp_Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(LoginActivity.this, UserRegActivity.class);
                        startActivity(myIntent);
                    }
                }
        );

        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        List<User> user = authenticate(username.getText().toString(), password.getText().toString());
                        if(user==null || user.size()==0) return;
                        if (user.get(0).getUserType().toLowerCase().equals("user")){

                            Toast.makeText(LoginActivity.this,"Username and password is correct",
                                    Toast.LENGTH_SHORT).show();

                            Intent myIntent = new Intent(LoginActivity.this, UserHome.class);
                            Bundle b =new Bundle();
                            b.putString("Username",username.getText().toString());
                            b.putString("Password",password.getText().toString());
                            myIntent.putExtras(b);
                            startActivity(myIntent);

                        }
                        else if (user.get(0).getUserType().toLowerCase().equals("admin")){
                            Toast.makeText(LoginActivity.this,"Username and password is correct",
                                    Toast.LENGTH_SHORT).show();

                            Intent myIntent = new Intent(LoginActivity.this, AdminHomeActivity.class);

                            startActivity(myIntent);

                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Username and password is NOT correct",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<User> authenticate(final String user, final String pass) {
        return users.stream()
                .filter(  entity  ->
                                entity.getName().equals(user)&& entity.getPassword().equals(pass))
                .collect(Collectors.toList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() throws IOException, JSONException {

        String response = getData("http://192.168.29.120:8080/getusers");
        Log.d(TAG, response);
        JSONArray jsonArray = new JSONArray(response);
        Log.d(TAG,jsonArray.get(0).toString());
        addItemsFromJSONArray(jsonArray);
    }

    private void addItemsFromJSONArray(JSONArray data) throws IOException {
        try {
            for (int i=0; i<data.length(); ++i) {

                JSONObject itemObj = data.getJSONObject(i);
                User user = new ObjectMapper().readValue(itemObj.toString(), User.class);

                users.add(user);
            }

        } catch (JSONException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(String urlstr) throws IOException {
        URL url = new URL(urlstr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("content-type", "application/json");
        String response = "";


        urlConnection.connect();
        int responseCode=urlConnection.getResponseCode();

        if (responseCode == HttpsURLConnection.HTTP_OK) {
            String line;
            BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line=br.readLine()) != null) {
                response+=line;
            }
        }
        else {
            response="";

        }
        return response;
    }
}
