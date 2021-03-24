package com.telme.tellme;

import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerAdapter(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);

        FetchCustomerData fetchData = new FetchCustomerData();
        fetchData.execute();

        while (fetchData.data==null || fetchData.data.equals(""))  Log.d("info","loading....");
        try {
            JSONArray jsonArray = new JSONArray(fetchData.data);
            Log.d("info",jsonArray.get(0).toString());
            addItemsFromJSONArray(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Json Response", fetchData.data);

    }

private void addItemsFromJSONArray(JSONArray data){
    try {
        for (int i=0; i<data.length(); ++i) {

            JSONObject itemObj = data.getJSONObject(i);
            Customer sample = new ObjectMapper().readValue(itemObj.toString(), Customer.class);
            Holidays holidays = new Holidays(sample.name, sample.mobile,sample.varient,sample.hypo);
            viewItems.add(holidays);
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

    public void showcontact(View view){
        showcontact((TextView)view);
    }
    public void showcontact(TextView textView){
        TextView tv = (TextView) findViewById(R.id.mobile);
        Linkify.addLinks(textView, Linkify.PHONE_NUMBERS);
    }

    public void attemptLogIn(View view) {
    }
}