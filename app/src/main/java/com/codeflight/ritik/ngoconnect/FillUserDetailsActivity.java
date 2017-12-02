package com.codeflight.ritik.ngoconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FillUserDetailsActivity extends AppCompatActivity {

    private EditText et_name,et_mobile,et_city,et_state;
    private CheckBox f1,f2,f3,f4;
    private Button btn_continue;
    private User user;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private String uid,email,name,city,state,mobile,user_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_user_details);

        Intent intent = getIntent();
        user_type = intent.getStringExtra("User_Type");

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        et_name = (EditText) findViewById(R.id.name);
        et_mobile = (EditText) findViewById(R.id.mobile);
        et_city = (EditText) findViewById(R.id.city);
        et_state = (EditText) findViewById(R.id.state);

        f1 = (CheckBox) findViewById(R.id.nd);
        f2 = (CheckBox) findViewById(R.id.ta);
        f3 = (CheckBox) findViewById(R.id.ed);
        f4 = (CheckBox) findViewById(R.id.cw);

        btn_continue = (Button) findViewById(R.id.btn_continue);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = auth.getCurrentUser().getUid();
                email = auth.getCurrentUser().getEmail();
                name = et_name.getText().toString().trim();
                mobile = et_mobile.getText().toString().trim();
                city = et_city.getText().toString().trim();
                state = et_state.getText().toString().trim();

                if(email != null && name != null && mobile != null && city != null && state != null &&
                        (f1.isChecked() || f2.isChecked() || f3.isChecked() || f4.isChecked()))
                    {
                    List<String> fields = new ArrayList<>();
                    if(f1.isChecked())
                        fields.add(f1.getText().toString().trim());

                    if(f2.isChecked())
                        fields.add(f2.getText().toString().trim());

                    if(f3.isChecked())
                        fields.add(f3.getText().toString().trim());

                    if(f4.isChecked())
                        fields.add(f4.getText().toString().trim());

                    User user = new User(email,name,mobile,city,state,fields,user_type);
                    mDatabase.child("users").child(uid).setValue(user);

                    startActivity(new Intent(FillUserDetailsActivity.this, UserHome.class));
                    finish();
                }
                else
                {
                    Toast.makeText(FillUserDetailsActivity.this, "Please Fill all Details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
