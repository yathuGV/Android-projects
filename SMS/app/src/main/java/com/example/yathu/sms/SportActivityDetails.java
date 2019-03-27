package com.example.yathu.sms;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SportActivityDetails extends AppCompatActivity {
    DatabaseHelper studentDb;
    EditText stuname,stuid,stuclass,sport,stage,award,remark;
    Button sprtbtnsave,sprtbtnview,sprtbtnupdate,sprtbtnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_details);
        studentDb =new DatabaseHelper(getApplicationContext());

        stuname = (EditText)findViewById(R.id.txt_ad_name);
        stuid = (EditText)findViewById(R.id.sp_stu_id);
        stuclass = (EditText)findViewById(R.id.sp_stu_class);
        sport = (EditText)findViewById(R.id.sp_stu_sport);
        stage = (EditText)findViewById(R.id.sp_stu_stage);
        award = (EditText)findViewById(R.id.sp_stu_award);
        remark = (EditText)findViewById(R.id.sp_stu_remark);
        sprtbtnsave=(Button)findViewById(R.id.btn_ad_save);
        sprtbtnview=(Button)findViewById(R.id.btn_ad_search);
        sprtbtnupdate=(Button)findViewById(R.id.btn_ad_update);
        sprtbtnsearch=(Button)findViewById(R.id.btn_ad_view);

        addSportData();
        viewSportData();
       updateSportData();
        searchSportData();
    }

    private void addSportData()
    {
        sprtbtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = studentDb.insertSportDetails(stuname.getText().toString().trim(),
                        stuid.getText().toString().trim(),
                        stuclass.getText().toString().trim(),
                        sport.getText().toString().trim(),
                        stage.getText().toString().trim(),
                        award.getText().toString().trim(),
                        remark.getText().toString().trim());

                if (isInserted == true)
                    Toast.makeText(SportActivityDetails.this, "Sport Details Added Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SportActivityDetails.this, "Sport Details Added Unsuccessfull", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void viewSportData()
    {
        sprtbtnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=studentDb.getSportData();
                if (res.getCount()==0)
                {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("STUDENT NAME:"+ res.getString(1)+ "\n");
                    buffer.append("STUDENT ID:"+ res.getString(2)+ "\n");
                    buffer.append("STUDENT CLASS:"+ res.getString(3)+ "\n");
                    buffer.append("SPORT:"+ res.getString(4)+ "\n");
                    buffer.append("STAGE:"+ res.getString(5)+ "\n");
                    buffer.append("AWARD:"+ res.getString(6)+ "\n");
                    buffer.append("REMARK:"+ res.getString(7)+ "\n\n");
                }
                showMessage("Sport Details",buffer.toString());
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
    public void updateSportData()
    {
        sprtbtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=studentDb.UpdateSportDetails(stuname.getText().toString(),
                        stuid.getText().toString(),
                        stuclass.getText().toString(),
                        sport.getText().toString(),
                        stage.getText().toString(),
                        award.getText().toString(),
                        remark.getText().toString());

                if (isUpdate==true)
                    Toast.makeText(SportActivityDetails.this, "Sport Details Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SportActivityDetails.this, "Sport Details Not Updated", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void searchSportData()
    {
        sprtbtnsearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(stuid.getText().toString().trim().length()==0)
                        {
                            showMessage("Error", "Please enter student id:");
                            return;
                        }
                        Cursor re=studentDb.searchSportData(stuid.getText().toString());
                        if(re.moveToFirst())
                        {
                            stuname.setText(re.getString(1));
                            stuclass.setText(re.getString(3));
                            sport.setText(re.getString(4));
                            stage.setText(re.getString(5));
                            award.setText(re.getString(6));
                            remark.setText(re.getString(7));
                        }
                        else
                        {
                            showMessage("Error" , "Invalid student id");
                        }
                    }
                }
        );
    }

}
