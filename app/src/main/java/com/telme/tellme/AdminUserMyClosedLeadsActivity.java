package com.telme.tellme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telme.tellme.reclyeradpaters.admin.AdminUserClosedLeadsRecyclerAdapter;
import com.telme.tellme.rest.UserCustomeRestUtil;

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

public class AdminUserMyClosedLeadsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private List<Object> viewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "AUMyClosedLeadsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_my_closed_leads);

        mRecyclerview =  findViewById(R.id.admin_user_myclosed_recycler_view);
        mRecyclerview.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);

        mAdapter = new AdminUserClosedLeadsRecyclerAdapter(this,viewItems);
        mRecyclerview.setAdapter(mAdapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        UserCustomeRestUtil.fetchData(getIntent().getExtras(),"getleadsclosed",viewItems);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchData() throws IOException, JSONException {
        Bundle b = getIntent().getExtras( );
        UserAuth user = new UserAuth();
        user.setUserId(b.getString("Username"));
        String userstr = user.toString();
        String response = getData("http://192.168.29.120:8080/getleadsclosed",userstr);
        Log.d(TAG, response);
        JSONArray jsonArray = new JSONArray(response);
        Log.d(TAG,jsonArray.get(0).toString());
        addItemsFromJSONArray(jsonArray);
    }

    private void addItemsFromJSONArray(JSONArray data) throws IOException {
        try {
            for (int i=0; i<data.length(); ++i) {

                JSONObject itemObj = data.getJSONObject(i);
                UserCustomerPojo userCustomerPojo = new ObjectMapper().readValue(itemObj.toString(), UserCustomerPojo.class);
                viewItems.add(userCustomerPojo);
            }

        } catch (JSONException e) {
            Log.d(TAG,  e.getMessage());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(String urlstr, String body) throws IOException {
        URL url = new URL(urlstr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("content-type", "application/json");
        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
        String response = "";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.write(body);
        writer.flush();
        writer.close();
        out.close();

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

    public void showcontact(View view){
        showcontact((TextView)view);
    }
    public void showcontact(TextView textView){
        TextView tv = (TextView) findViewById(R.id.mobile);
        Linkify.addLinks(textView, Linkify.PHONE_NUMBERS);
    }
}