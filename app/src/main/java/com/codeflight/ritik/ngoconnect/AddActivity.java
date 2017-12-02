package com.codeflight.ritik.ngoconnect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddActivity extends AppCompatActivity {

    private EditText activity_title, activity_description, activity_category, activity_location, activity_helptype;
    private String title,desc,category,location,helps;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase,ref1,ref2;
    private String ngo_name;
    private ValueEventListener ngoname_value_event_listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        activity_title = (EditText) findViewById(R.id.activity_title_id);
        activity_description = (EditText) findViewById(R.id.activity_description_id);
        activity_category = (EditText)findViewById(R.id.activity_category_id);
        activity_location = (EditText)findViewById(R.id.activity_location_id);
        activity_helptype = (EditText)findViewById(R.id.activity_help_id);

        ngoname_value_event_listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ngo_name = (String) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    public void submitActivity(View v){
        title = activity_title.getText().toString().trim();
        desc = activity_description.getText().toString().trim();
        category = activity_category.getText().toString().trim();
        location = activity_location.getText().toString().trim();
        helps = activity_helptype.getText().toString().trim();

        if(title != null && desc != null && category != null && location != null)
        {
            ref1 = mDatabase.child("users").child(auth.getCurrentUser().getUid()).child("activities");
            ref2 = mDatabase.child("activities");

            String id1 = ref1.push().getKey();
            String id2 = ref2.push().getKey();

            FeedItem feed1 = new FeedItem(title, desc, category, null, location, helps);
            FeedItem feed2 = new FeedItem(title, desc, category, ngo_name, location, helps);

            ref1.child(id1).setValue(feed1);
            ref2.child(id2).setValue(feed2);

            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.child("users").child(auth.getCurrentUser().getUid()).child("NGOName").addValueEventListener(ngoname_value_event_listener);
    }
}
