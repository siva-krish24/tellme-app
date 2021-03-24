package com.telme.tellme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telme.tellme.reclyeradpaters.admin.AdminUserUsersListRecyclerAdapter;
import com.telme.tellme.reclyeradpaters.user.UserFreshDataRecyclerAdapter;
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

public class AdminUsersListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<User> viewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "AdminUsersListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users_list);

            mRecyclerView = (RecyclerView) findViewById(R.id.admin_user_userslist_recycler_view);
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);

            // specify an adapter (see also next example)
            mAdapter = new AdminUserUsersListRecyclerAdapter(this, viewItems);
            mRecyclerView.setAdapter(mAdapter);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            UserRestUtil.fetchData("getusers",viewItems);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
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
                User user = new ObjectMapper().readValue(itemObj.toString(),User.class);

                viewItems.add(user);
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

    public void startAdminUserHome(View view){
        TextView curView = (TextView)view;
        final Bundle b = new Bundle();
        b.putString("Username",curView.getText().toString());
        Intent myIntent = new Intent(AdminUsersListActivity.this, AdminUserHomeActivity.class);
        myIntent.putExtras(b);
        startActivity(myIntent);
    }
    public void showcontact(TextView textView){
        TextView tv = (TextView) findViewById(R.id.mobile);
        Linkify.addLinks(textView, Linkify.PHONE_NUMBERS);
    }
}