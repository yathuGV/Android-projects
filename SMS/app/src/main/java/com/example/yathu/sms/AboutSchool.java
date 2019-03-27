package com.example.yathu.sms;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AboutSchool extends AppCompatActivity {
DatabaseHelper studentDb;
EditText sclname,scladd,sclreg,princi,numtea,numnonstaff,numofstu;
Button btnsave,btnview,btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_school);
        studentDb =new DatabaseHelper(getApplicationContext());

        sclname = (EditText)findViewById(R.id.txt_scl_name);
        scladd = (EditText)findViewById(R.id.txt_scl_add);
        sclreg = (EditText)findViewById(R.id.txt_scl_regno);
        princi = (EditText)findViewById(R.id.txt_principal);
        numtea = (EditText)findViewById(R.id.txt_no_of_tea);
        numnonstaff = (EditText)findViewById(R.id.txt_no_of_nonacc_staffs);
        numofstu = (EditText)findViewById(R.id.txt_no_of_stu);
        btnsave=(Button)findViewById(R.id.btn_save);
        btnview=(Button)findViewById(R.id.btn_view);
        btnupdate=(Button)findViewById(R.id.btn_update);

        addData();
        viewAll();
        UpdateData();
    }
   private void addData()
    {
       btnsave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               boolean isInserted = studentDb.insertSclDetails(sclname.getText().toString().trim(),
                       scladd.getText().toString().trim(),
                       sclreg.getText().toString().trim(),
                       princi.getText().toString().trim(),
                       numtea.getText().toString().trim(),
                       numnonstaff.getText().toString().trim(),
                       numofstu.getText().toString().trim());

               if (isInserted == true)
                   Toast.makeText(AboutSchool.this, "School Details Added Successfully", Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(AboutSchool.this, "School Details Added Unsuccessfull", Toast.LENGTH_LONG).show();
           }
       });
    }

    private void viewAll()
    {
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=studentDb.getSclData();
                if (res.getCount()==0)
                {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("SCHOOL NAME:"+ res.getString(1)+ "\n");
                    buffer.append("SCHOOL ADDRESS:"+ res.getString(2)+ "\n");
                    buffer.append("SCHOOL REGISTRATION NUMBER:"+ res.getString(3)+ "\n");
                    buffer.append("PRINCIPAL NAME:"+ res.getString(4)+ "\n");
                    buffer.append("NUMBER OF TEACHERS:"+ res.getString(5)+ "\n");
                    buffer.append("NUMBER OF NON ACCEDAMIC STAFFS:"+ res.getString(6)+ "\n");
                    buffer.append("NUMBER OF STUDENTS:"+ res.getString(7)+ "\n\n");
                   // buffer.append("NUMBER OF STUDENTS:"+ res.getString(7)+ "\n\n");
                }
                showMessage("School Details",buffer.toString());
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
    public void UpdateData()
    {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=studentDb.UpdateSclDetails(sclname.getText().toString(),
                        scladd.getText().toString(),
                        sclreg.getText().toString(),
                        princi.getText().toString(),
                        numtea.getText().toString(),
                        numnonstaff.getText().toString(),
                        numofstu.getText().toString());

                if (isUpdate==true)
                    Toast.makeText(AboutSchool.this, "School Details Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AboutSchool.this, "School Details Not Updated", Toast.LENGTH_LONG).show();
            }
        });
    }

}
