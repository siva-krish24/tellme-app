package com.telme.tellme.rest;

import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telme.tellme.Customer;
import com.telme.tellme.Holidays;
import com.telme.tellme.UserAuth;
import com.telme.tellme.UserCustomerPojo;

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

public class UserCustomeRestUtil {
    private static final String HOSTNAME = Constants.HOSTNAME;
    private static final String TAG = "RestUtil";

    public static void fetchData(Bundle b, String path, List<Object> viewitems) throws IOException, JSONException {
        UserAuth user = new UserAuth();
        user.setUserId(b.getString("Username"));
        String userstr = user.toString();
        String response = getData(HOSTNAME+path,userstr);
        JSONArray jsonArray = new JSONArray(response);
        addItemsFromJSONArray(jsonArray, viewitems);
    }
    public static UserCustomerPojo fetchData(String usrstr, String path) throws IOException, JSONException {
        String response = getData(HOSTNAME+path,usrstr);
        UserCustomerPojo userCustomerPojo = new ObjectMapper().readValue(response, UserCustomerPojo.class);
        return userCustomerPojo;
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

    private static void addItemsFromJSONArray(JSONArray data, List<Object> viewItems) throws IOException {
        try {
            for (int i=0; i<data.length(); ++i) {

                JSONObject itemObj = data.getJSONObject(i);
                UserCustomerPojo userCustomerPojo = new ObjectMapper().readValue(itemObj.toString(), UserCustomerPojo.class);
                Customer customer = userCustomerPojo.getCustomer();

                viewItems.add(userCustomerPojo);
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

    public static void postData(String path, String body) throws IOException {
        URL url = new URL(HOSTNAME+path);

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

    }


}
