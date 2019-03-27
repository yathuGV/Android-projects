package com.example.yathu.sms;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper customerDb;
    EditText editName,  editpass;
    private Button blogin, bnewuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        customerDb=new DatabaseHelper(this);
        editName=(EditText)findViewById(R.id.editText_username);
        editpass=(EditText)findViewById(R.id.editText_password);
        blogin=(Button)findViewById(R.id.btn_login);
        bnewuser=(Button) findViewById(R.id.btn_newuser);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((editName.getText().length() < 1)&&(editpass.getText().length() <1)) {
                    editName.setError("Enter Username");
                    editpass.setError("Enter Password");
                } else {
                    String name = editName.getText().toString();
                    String password = editpass.getText().toString();
                    Boolean chkNamePass = customerDb.NamePassword(name, password);

                    if (chkNamePass == true) {
                        Intent intent1 = new Intent(MainActivity.this, display.class);
                        startActivity(intent1);
                        Toast.makeText(getApplicationContext(), "successfully login", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "wrong name or password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        bnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRegister();
            }
        });
    }

    public void openActivityRegister()
    {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}

