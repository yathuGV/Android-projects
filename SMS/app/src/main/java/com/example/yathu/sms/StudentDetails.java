package com.example.yathu.sms;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentDetails extends AppCompatActivity {

    DatabaseHelper customerDb;
    EditText stufname,stulname,stuid,studob,stuclass,stusection,stuphone,stubloodgrp;
    Button stubtnsave,stubtnview,stubtnupdate,stubtndlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        customerDb =new DatabaseHelper(getApplicationContext());

        stufname = (EditText)findViewById(R.id.Stu_dlt_first_name);
        stulname = (EditText)findViewById(R.id.Stu_dlt_last_name);
        stuid = (EditText)findViewById(R.id.Stu_dlt_id);
        studob = (EditText)findViewById(R.id.stu_dlt_dob);
        stuclass = (EditText)findViewById(R.id.stu_dlt_class);
        stusection = (EditText)findViewById(R.id.stu_dlt_section);
        stuphone = (EditText)findViewById(R.id.stu_dlt_phone);
        stubloodgrp = (EditText)findViewById(R.id.stu_dlt_bloodgrp);
        stubtnsave=(Button)findViewById(R.id.btn_ad_save);
        stubtnview=(Button)findViewById(R.id.btn_ad_search);
        stubtnupdate=(Button)findViewById(R.id.btn_ad_update);
        stubtndlt=(Button)findViewById(R.id.btn_ad_view);

        StuAddData();
        StuViewData();
        StuUpdateData();
        StuDeleteData();
    }
    private void StuAddData()
    {
        stubtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = customerDb.insertStuDetails(stufname.getText().toString().trim(),
                        stulname.getText().toString().trim(),
                        stuid.getText().toString().trim(),
                        studob.getText().toString().trim(),
                        stuclass.getText().toString().trim(),
                        stusection.getText().toString().trim(),
                        stuphone.getText().toString().trim(),
                        stubloodgrp.getText().toString().trim());

                if (isInserted == true)
                    Toast.makeText(StudentDetails.this, "Student Details Added Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(StudentDetails.this, "Student Details Added Unsuccessfull..please consider input id & phone in numbers", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void StuViewData()
    {
        stubtnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=customerDb.getStuData();
                if (res.getCount()==0)
                {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("STUDENT FIRST NAME:"+ res.getString(1)+ "\n");
                    buffer.append("STUDENT LAST NAME:"+ res.getString(2)+ "\n");
                    buffer.append("STUDENT ID:"+ res.getString(3)+ "\n");
                    buffer.append("STUDENT DOB:"+ res.getString(4)+ "\n");
                    buffer.append("STUDENT CLASS:"+ res.getString(5)+ "\n");
                    buffer.append("STUDENT SECTION:"+ res.getString(6)+ "\n");
                    buffer.append("STUDENT PHONE NUMBER:"+ res.getString(7)+ "\n\n");
//                   buffer.append("STUDENT BLOOD GROUP:"+ res.getString(8)+ "\n");
                }
                showMessage("Student  Details",buffer.toString());
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
    public void StuUpdateData()
    {
        stubtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=customerDb.UpdateStuDetails(stufname.getText().toString(),
                        stulname.getText().toString(),
                        stuid.getText().toString(),
                        studob.getText().toString(),
                        stuclass.getText().toString(),
                        stusection.getText().toString(),
                        stuphone.getText().toString(),
                        stubloodgrp.getText().toString());

                if (isUpdate==true)
                    Toast.makeText(StudentDetails.this, "Student Details Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(StudentDetails.this, "Student Details Not Updated", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void StuDeleteData()
    {
        stubtndlt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRows =customerDb.StuDeleteData(stuid.getText().toString());
                        if (deleteRows>0)
                            Toast.makeText(StudentDetails.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(StudentDetails.this, "Data Not Deleted", Toast.LENGTH_LONG).show();


                    }
                }
        );
    }
}
