package com.example.yathu.sms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OtherStaffDetails extends AppCompatActivity {
    DatabaseHelper customerDb;
    EditText osname,osid,osage,osadd,osjob,osdob,osphone;
    Button ossave,osview,osupdate,osdlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_staff_details);
        customerDb=new DatabaseHelper(getApplicationContext());

        osname=(EditText)findViewById(R.id.txt_ad_name);
        osid=(EditText)findViewById(R.id.txt_ot_staffid);
        osage=(EditText)findViewById(R.id.txt_ot_age);
        osadd=(EditText)findViewById(R.id.txt_ot_add);
        osjob=(EditText)findViewById(R.id.txt_ot_job);
        osdob=(EditText)findViewById(R.id.txt_ot_dob);
        osphone=(EditText)findViewById(R.id.txt_ot_phone);

        ossave=(Button)findViewById(R.id.btn_ad_save);
        osview=(Button)findViewById(R.id.btn_ad_search);
        osupdate=(Button)findViewById(R.id.btn_ad_update);
        osdlt=(Button)findViewById(R.id.btn_ad_view);

        OtherStaffAddData();
    }
    private void OtherStaffAddData()
    {
        ossave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted= customerDb.insertOtherStaffDetails(
                        osname.getText().toString().trim(),
                        osid.getText().toString().trim(),
                        osage.getText().toString().trim(),
                        osadd.getText().toString().trim(),
                        osjob.getText().toString().trim(),
                        osdob.getText().toString().trim(),
                        osphone.getText().toString().trim());
                if (isInserted==true)
                    Toast.makeText(OtherStaffDetails.this, "Other staff details added successfully",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(OtherStaffDetails.this,"other staff details added unsuccessfully..insert id,age & phone as integer",Toast.LENGTH_LONG).show();
            }
        });
    }
}
