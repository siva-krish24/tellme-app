package com.telme.tellme.rest;

import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telme.tellme.States;
import com.telme.tellme.User;
import com.telme.tellme.UserDate;

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
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class UserDateRestUtil {
    private static final String HOSTNAME =  Constants.HOSTNAME;
    private static final String TAG = "RestUtil";

    public static void fetchData(Bundle b, String path, List<Object> viewitems) throws IOException, JSONException {

        UserDate userDate = new UserDate();
        userDate.setDays(b.getInt("days"));
        userDate.setUserId(b.getString("Username"));

        String response = getData(HOSTNAME+path,userDate.toString());
        Log.i(TAG,response);
        addItemsFromJson(response,viewitems);

    }
    public static String getData(String urlstr, String body) throws IOException {
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

    private static void addItemsFromJson(String data, List<Object> viewItems) throws IOException {
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


}
