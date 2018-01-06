package com.ryanstoughton.lolookup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SummonerDetailActivity extends AppCompatActivity {

    TextView summonerDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner_detail);

        summonerDetailTextView = findViewById(R.id.summonerDetailTextView);
        summonerDetailTextView.setText(getIntent().getStringExtra("SummonerInfo"));
    }

}
