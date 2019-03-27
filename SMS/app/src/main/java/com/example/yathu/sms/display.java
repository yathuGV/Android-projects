package com.example.yathu.sms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Button scldetails,studetails,teachersdetails,otherstaffdetails,attendancedetails,tests,fees,sportactdetails;

        scldetails=(Button)findViewById(R.id.btn_abt_scl);
        studetails=(Button)findViewById(R.id.btn_stu_details);
        teachersdetails=(Button)findViewById(R.id.btn_tea_details);
        otherstaffdetails=(Button)findViewById(R.id.btn_other_staff_details);
        attendancedetails=(Button)findViewById(R.id.btn_attedance_details);
        tests=(Button)findViewById(R.id.btn_tests);
        fees=(Button)findViewById(R.id.btn_fees);
        sportactdetails=(Button)findViewById(R.id.btn_sport_act_details);

        scldetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(display.this,AboutSchool.class);
                startActivity(intent1);
            }
        });

        studetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(display.this,StudentDetails.class);
                startActivity(intent1);
            }
        });

        teachersdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(display.this,TeachersDetails.class);
                startActivity(intent1);
            }
        });

        otherstaffdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(display.this,OtherStaffDetails.class);
                startActivity(intent1);
            }
        });

        attendancedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(display.this,AttendanceDetails.class);
                startActivity(intent1);
            }
        });

        tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(display.this,Tests.class);
                startActivity(intent1);
            }
        });

        fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(display.this,Fees.class);
                startActivity(intent1);
            }
        });

        sportactdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(display.this,SportActivityDetails.class);
                startActivity(intent1);
            }
        });
    }
}
