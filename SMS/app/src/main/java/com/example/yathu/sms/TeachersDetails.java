package com.example.yathu.sms;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeachersDetails extends AppCompatActivity {
    DatabaseHelper studentDb;
    EditText teaname,teaid,teaAge,teaAdd,teasub,teadob,teaphone,teaemail;
    Button teabtnsave,teabtnview,teabtnupdate,teabtndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_details);
        studentDb =new DatabaseHelper(getApplicationContext());

        teaname = (EditText)findViewById(R.id.txt_ad_name);
        teaid = (EditText)findViewById(R.id.txt_tea_id);
        teaAge = (EditText)findViewById(R.id.txt_tea_age);
        teaAdd = (EditText)findViewById(R.id.txt_tea_add);
        teasub= (EditText)findViewById(R.id.txt_tea_sub);
        teadob = (EditText)findViewById(R.id.txt_tea_dob);
        teaphone = (EditText)findViewById(R.id.txt_tea_phone);

        teabtnsave=(Button)findViewById(R.id.btn_ad_save);
        teabtnview=(Button)findViewById(R.id.btn_ad_search);
        teabtnupdate=(Button)findViewById(R.id.btn_ad_update);
        teabtndelete=(Button)findViewById(R.id.btn_ad_view);

        teaAddData();
        teaViewData();
      //  teaUpdateData();
       // teaDeleteData();
    }
    private void teaAddData()
    {
        teabtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = studentDb.insertTeaDetails(teaname.getText().toString().trim(),
                        teaid.getText().toString().trim(),
                        teaAge.getText().toString().trim(),
                        teaAdd.getText().toString().trim(),
                        teasub.getText().toString().trim(),
                        teadob.getText().toString().trim(),
                        teaphone.getText().toString().trim());

                if (isInserted == true)
                    Toast.makeText(TeachersDetails.this, "teachers Details Added Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(TeachersDetails.this, "teachers Details Added Unsuccessfull..insert id & phone as integer", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void teaViewData()
    {
        teabtnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=studentDb.getTeaData();
                if (res.getCount()==0)
                {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("teacher name:"+ res.getString(1)+ "\n");
                    buffer.append("teacher id:"+ res.getString(2)+ "\n");
                    buffer.append("teacher age:"+ res.getString(3)+ "\n");
                    buffer.append("teacher address:"+ res.getString(4)+ "\n");
                    buffer.append("teacher subjects:"+ res.getString(5)+ "\n");
                    buffer.append("teacher dob:"+ res.getString(6)+ "\n\n");
                  //  buffer.append("teacher phone:"+ res.getString(7)+ "\n\n");

                }
                showMessage("teachers Details",buffer.toString());
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
