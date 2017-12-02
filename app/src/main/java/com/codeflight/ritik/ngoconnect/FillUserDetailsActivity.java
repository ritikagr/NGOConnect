package com.codeflight.ritik.ngoconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FillUserDetailsActivity extends AppCompatActivity {

    private EditText name,mobile,city,state;
    private RadioGroup interested_fields;
    private RadioButton f1,f2,f3,f4;
    private Button btn_continue;
    private User user;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_user_details);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        interested_fields = (RadioGroup) findViewById(R.id.interested_fields);
        f1 = (RadioButton) findViewById(R.id.nd);
        f2 = (RadioButton) findViewById(R.id.ta);
        f3 = (RadioButton) findViewById(R.id.ed);
        f4 = (RadioButton) findViewById(R.id.cw);

        btn_continue = (Button) findViewById(R.id.btn_continue);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = auth.getCurrentUser().getUid();
                if(name.getText().toString().trim() != null && mobile.getText().toString().trim() != null &&
                        city.getText().toString().trim() != null && state.getText().toString().trim() != null &&
                        (f1.getText().toString().trim() != null || f2.getText().toString().trim() != null || f3.getText().toString().trim() != null || f4.getText().toString().trim() != null)) {
                    mDatabase.child("users").child(uid).child("Full Name").setValue(name.getText());
                    mDatabase.child("users").child(uid).child("Mobile").setValue(mobile.getText());
                    mDatabase.child("users").child(uid).child("City").setValue(city.getText());
                    mDatabase.child("users").child(uid).child("State").setValue(state.getText());

                    ArrayList<String> fields = new ArrayList<>();
                    if(f1.getText().toString().trim() != null)
                        fields.add(f1.getText().toString().trim());

                    if(f2.getText().toString().trim() != null)
                        fields.add(f2.getText().toString().trim());

                    if(f3.getText().toString().trim() != null)
                        fields.add(f3.getText().toString().trim());

                    if(f4.getText().toString().trim() != null)
                        fields.add(f4.getText().toString().trim());

                    mDatabase.child("users").child(uid).child("Interested Fields").setValue(fields);

                    startActivity(new Intent(FillUserDetailsActivity.this, UserHome.class));
                }
                else
                {
                    Toast.makeText(FillUserDetailsActivity.this, "Please Fill all Details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
