package com.codeflight.ritik.ngoconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FillNGODetailsAcitvity extends AppCompatActivity {

    private EditText name,website,office_email,office_phone,address,unique_id;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_ngodetails_acitvity);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        name = (EditText)findViewById(R.id.name_of_org);
        website = (EditText)findViewById(R.id.website);
        office_email = (EditText)findViewById(R.id.email);
        office_phone = (EditText)findViewById(R.id.office_phone);
        address = (EditText)findViewById(R.id.address);
        unique_id = (EditText)findViewById(R.id.unique_id);
    }

    public void submit(View v){
        String email = auth.getCurrentUser().getEmail();
        String sname = name.getText().toString();
        String swebsite = website.getText().toString();
        String sofficial_email = office_email.getText().toString();
        String soffice_phone = office_phone.getText().toString();
        String saddress = address.getText().toString();
        String sunique_id = unique_id.getText().toString();
        String type = "NGO";
        String uid = auth.getCurrentUser().getUid();

        if(email!= null && sname != null && swebsite != null && sofficial_email != null && soffice_phone != null && saddress != null && sunique_id != null)
        {
            NGO ngo = new NGO(sname,swebsite,email,soffice_phone,saddress,sunique_id,type,sofficial_email);
            mDatabase.child("users").child(uid).setValue(ngo);

            startActivity(new Intent(FillNGODetailsAcitvity.this, NGOHome.class));
            finish();
        }
        else {
            Toast.makeText(this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
        }
    }

}
