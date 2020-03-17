package com.example.notanotherworkoutapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {
    //class to fetch data from the RestAPI
    String quotetext = "";
    String quoteParsed = "";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //make a connection to the Rest server
            URL url = new URL("https://api.myjson.com/bins/181loy");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line =  bufferedReader.readLine();
                quotetext = quotetext + line;
            }
            //parse the jsonobject
            JSONArray jsonArray = new JSONArray(quotetext);
            for (int i =0 ;i<jsonArray.length();i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                quoteParsed = "" + jsonObject.get("quote");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //display the parsed data in the TextView
        MainActivity.quotetext.setText(this.quoteParsed);

    }
}
