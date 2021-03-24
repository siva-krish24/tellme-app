package com.telme.tellme;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchIntrestedCustomerData extends AsyncTask<Void, Void, Void> {

    public String data;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url =  new URL( "http://34.229.136.212:8080/getintrested");
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
