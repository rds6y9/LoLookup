package com.ryanstoughton.lolookup;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class LookupActivity extends AppCompatActivity {

    TextView nameNotFoundErrorTextView;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            nameNotFoundErrorTextView = findViewById(R.id.nameNotFoundErrorTextView);

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nameNotFoundErrorTextView.setVisibility(View.INVISIBLE);
                    }
                });
            } catch (FileNotFoundException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nameNotFoundErrorTextView.setVisibility(View.VISIBLE);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

    }

    protected void lookupSummoner(View view) {
        EditText lookupEditText = findViewById(R.id.lookupEditText);
        String summonerName = lookupEditText.getText().toString();

        DownloadTask task = new DownloadTask();
        try {
            String result = task.execute("https://na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + summonerName + "?api_key=RGAPI-d1143ce4-626c-4897-8cdc-edc8a543f7cb").get();
            Log.i("Result of API Call", result);

            Intent intent = new Intent(this, SummonerDetailActivity.class).putExtra("SummonerInfo", result);
            startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);
    }
}
