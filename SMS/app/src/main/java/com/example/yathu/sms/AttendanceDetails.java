package com.example.yathu.sms;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AttendanceDetails extends AppCompatActivity {
    DatabaseHelper studentDb;
    EditText adname,adid,adclass,adsection,adDate,attendance,remark;
    Button adbtnsave,adbtnview,adbtnupdate,adbtndlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_details);

        studentDb =new DatabaseHelper(getApplicationContext());

        adname = (EditText)findViewById(R.id.txt_ad_name);
        adid = (EditText)findViewById(R.id.txt_ad_id);
        adclass = (EditText)findViewById(R.id.txt_ad_class);
        adsection = (EditText)findViewById(R.id.txt_ad_section);
        adDate = (EditText)findViewById(R.id.txt_ad_date);
        attendance = (EditText)findViewById(R.id.txt_ad_att);
        remark=(EditText)findViewById(R.id.txt_ad_remark);
        adbtnsave=(Button)findViewById(R.id.btn_ad_save);
        adbtnview=(Button)findViewById(R.id.btn_ad_view);
        adbtnupdate=(Button)findViewById(R.id.btn_ad_update);
        adbtndlt=(Button)findViewById(R.id.btn_ad_dlt);

        AttAddData();
        AttViewData();
    }
    private void AttAddData()
    {
        adbtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = studentDb.insertAttendanceDetails(adname.getText().toString().trim(),
                        adid.getText().toString().trim(),
                        adclass.getText().toString().trim(),
                        adsection.getText().toString().trim(),
                        adDate.getText().toString().trim(),
                        attendance.getText().toString().trim(),
                        remark.getText().toString().trim());


                if (isInserted == true)
                    Toast.makeText(AttendanceDetails.this, "Attendance Details Added Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AttendanceDetails.this, "Attendance Details Added Unsuccessfull..insert id as a integer", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void AttViewData()
    {
        adbtnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=studentDb.getAttData();
                if (res.getCount()==0)
                {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append(" NAME:"+ res.getString(1)+ "\n");
                    buffer.append("ID:"+ res.getString(2)+ "\n");
                    buffer.append("CLASS:"+ res.getString(3)+ "\n");
                    buffer.append("SECTION:"+ res.getString(4)+ "\n");
                    buffer.append("DATE:"+ res.getString(5)+ "\n");
                    buffer.append("ATTENDANCE:"+ res.getString(6)+ "\n\n");
                  //  buffer.append("REMARK:"+ res.getString(7)+"\n");

                }
                showMessage("Attendance Details",buffer.toString());
            }
        });
    }
    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
