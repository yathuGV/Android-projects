package com.example.yathu.sms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper customerDb;
    Button Regi, btn_b_login;
    EditText uname, email,psw, conpsw;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        customerDb= new DatabaseHelper(this);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        uname= (EditText)findViewById(R.id.editText_name);
        email = (EditText)findViewById(R.id.editText_email);
        psw = (EditText)findViewById(R.id.editText_pass);
        conpsw = (EditText)findViewById(R.id.editText_conpass);
        Regi = (Button) findViewById(R.id.btn_register);
        Register();

        awesomeValidation.addValidation(RegisterActivity.this,R.id.editText_name,"[a-zA-Z\\s]+",R.string.nameerror);
        awesomeValidation.addValidation(RegisterActivity.this,R.id.editText_email,android.util.Patterns.EMAIL_ADDRESS,R.string.emailerror);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(RegisterActivity.this,R.id.editText_pass,regexPassword,R.string.passerror);
        awesomeValidation.addValidation(RegisterActivity.this,R.id.editText_conpass,R.id.editText_pass,R.string.conpasserror);

        String namestr = uname.getText().toString();
        String emailstr = email.getText().toString();
        String pswstr = psw.getText().toString();
        String conpswstr = conpsw.getText().toString();


        btn_b_login=(Button)findViewById(R.id.btn_back_login);

        btn_b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFirstActivity();
            }
        });
    }
    public void openFirstActivity()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void Register()
    {
        Regi.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted =  customerDb.insertData(uname.getText().toString(),
                                email.getText().toString(),
                                psw.getText().toString(),
                                conpsw.getText().toString());
                        if (awesomeValidation.validate())
                             if (isInserted == true)
                                    {
                                    Toast.makeText(RegisterActivity.this, "You Register Successfully", Toast.LENGTH_LONG).show();
                                    Intent intent1 = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                     }
                            else
                                Toast.makeText(RegisterActivity.this, "You not register", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}

