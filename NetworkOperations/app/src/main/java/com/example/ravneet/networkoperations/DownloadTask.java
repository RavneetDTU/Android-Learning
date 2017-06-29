package com.example.ravneet.networkoperations;

import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ravneet on 25/6/17.
 */

public class DownloadTask extends AsyncTask<String,Void,String> {

    private TextView tvToChange = null;

    public DownloadTask(TextView tvToChange) {
        this.tvToChange = tvToChange;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;

        try{
            url = new URL(strings[0]);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            InputStream netinputStream = connection.getInputStream();

            InputStreamReader isr = new InputStreamReader(netinputStream);

            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String buffer = "";
            while (buffer != null){
                buffer = br.readLine();
                sb.append(buffer);
            }

            return sb.toString();

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        tvToChange.setText(s);
    }
}
