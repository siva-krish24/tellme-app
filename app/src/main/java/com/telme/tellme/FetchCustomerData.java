package com.telme.tellme;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchCustomerData extends AsyncTask<Void, Void, Void> {

    public String data;
    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url =  new URL( "http://192.168.29.120:8080/customers");
            data = "";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream istream = httpURLConnection.getInputStream();

            BufferedReader br = new BufferedReader( new InputStreamReader(istream));
            String line = "";

            while(line!=null){
                line = br.readLine();
               if(line!=null) data += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
