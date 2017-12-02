package com.codeflight.ritik.ngoconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WhoAreYouActivity extends AppCompatActivity {

    private RadioGroup userTypeGroup;
    private RadioButton ngo,individual;
    private Button btnContinue;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private String userType = null;
    private String uid,email,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_are_you);

        userTypeGroup = (RadioGroup) findViewById(R.id.usertype_group);
        ngo = (RadioButton) findViewById(R.id.ngo);
        individual = (RadioButton) findViewById(R.id.individual);
        btnContinue = (Button) findViewById(R.id.btn_continue);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        userTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.ngo)
                {
                    userType = ngo.getText().toString();
                }
                else if(i == R.id.individual)
                {
                    userType = individual.getText().toString();
                }
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userType == null)
                    Toast.makeText(WhoAreYouActivity.this, "Please select on option!", Toast.LENGTH_SHORT).show();
                else
                {
                    uid = auth.getCurrentUser().getUid();
                    email = auth.getCurrentUser().getEmail();

                    mDatabase.child("users").child(uid).child("type").setValue(userType);

                    if(userType == getString(R.string.individual))
                    {
                        Intent intent = new Intent(WhoAreYouActivity.this, FillUserDetailsActivity.class);
                        intent.putExtra("User_Type", userType);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Intent intent = new Intent(WhoAreYouActivity.this, FillNGODetailsAcitvity.class);
                        intent.putExtra("User_Type", userType);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
