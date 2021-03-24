package com.telme.tellme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telme.tellme.reclyeradpaters.admin.AdminUserUserStatsRecyclerAdapter;
import com.telme.tellme.reclyeradpaters.user.UserFreshDataRecyclerAdapter;
import com.telme.tellme.rest.Constants;
import com.telme.tellme.rest.UserDateRestUtil;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class AdminUserStatsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "AUStatsActivity";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_stats);

        mRecyclerView = (RecyclerView) findViewById(R.id.admin_user_userstats_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AdminUserUserStatsRecyclerAdapter(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
            Bundle b = getIntent().getExtras( );
            UserDateRestUtil.fetchData(b,"getstats",viewItems);
            fetchData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void fetchData() throws IOException, JSONException {
        Bundle b = getIntent().getExtras( );

        UserDate userDate = new UserDate();
        userDate.setDays(b.getInt("days"));
        userDate.setUserId(b.getString("Username"));

        String response = getData(Constants.HOSTNAME+"getstats",userDate.toString());
        Log.i(TAG,response);
        addItemsFromJson(response);
    }

    private void addItemsFromJson(String data) throws IOException {
        try {

             States states = new ObjectMapper().readValue(data, States.class);
             viewItems.add(states);
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
}