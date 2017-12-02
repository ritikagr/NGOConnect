package com.codeflight.ritik.ngoconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Activity_Details extends AppCompatActivity {

    private FeedItem feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__details);

        feed = new FeedItem();
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null)
        {
            feed = b.getParcelable("Details");

        }
        else
        {
            Toast.makeText(this, "Error in fetching details", Toast.LENGTH_SHORT).show();
        }
    }
}
