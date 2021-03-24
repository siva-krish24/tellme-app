package com.telme.tellme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telme.tellme.reclyeradpaters.user.UserFreshDataRecyclerAdapter;
import com.telme.tellme.reclyeradpaters.user.UserLoginsRecyclerAdapter;
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

public class MyLoginsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "MyLoginsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_my_logins);
        mRecyclerView = (RecyclerView) findViewById(R.id.user_mylogin_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new UserLoginsRecyclerAdapter(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
            Bundle b = getIntent().getExtras( );
            UserCustomeRestUtil.fetchData(b,"getlogins",viewItems);

    } catch (JSONException | IOException e) {
        e.printStackTrace();
    }
    }
//    private void fetchData() throws IOException, JSONException {
//        Bundle b = getIntent().getExtras( );
//        UserAuth user = new UserAuth();
//        user.setUserId(b.getString("Username"));
//        String userstr = user.toString();
//        String response = getData("http://192.168.29.120:8080/getlogins",userstr);
//        Log.i(TAG, response);
//        JSONArray jsonArray = new JSONArray(response);
//        Log.i(TAG,jsonArray.get(0).toString());
//        addItemsFromJSONArray(jsonArray);
//    }
//
//    private void addItemsFromJSONArray(JSONArray data) throws IOException {
//        try {
//            for (int i=0; i<data.length(); ++i) {
//
//                JSONObject itemObj = data.getJSONObject(i);
//                UserCustomerPojo userCustomerPojo = new ObjectMapper().readValue(itemObj.toString(), UserCustomerPojo.class);
//                Customer customer = userCustomerPojo.getCustomer();
//                Holidays holidays = new Holidays(customer.name, customer.mobile,
//                        customer.varient,customer.hypo);
//                viewItems.add(userCustomerPojo);
//            }
//
//        } catch (JSONException e) {
//            Log.d(TAG, "addItemsFromJSON: ", e);
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getData(String urlstr, String body) throws IOException {
//        URL url = new URL(urlstr);
//
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        urlConnection.setRequestMethod("POST");
//        urlConnection.setRequestProperty("content-type", "application/json");
//        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
//        String response = "";
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//        writer.write(body);
//        writer.flush();
//        writer.close();
//        out.close();
//
//        urlConnection.connect();
//        int responseCode=urlConnection.getResponseCode();
//
//        if (responseCode == HttpsURLConnection.HTTP_OK) {
//            String line;
//            BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            while ((line=br.readLine()) != null) {
//                response+=line;
//            }
//        }
//        else {
//            response="";
//
//        }
//        return response;
//    }
}