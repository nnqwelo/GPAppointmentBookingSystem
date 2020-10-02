package com.noluthando.gpappointmentbookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    DBHelper dbHelper;
    EditText editText_username, editText_password;
    Button button_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        editText_username = (EditText) findViewById(R.id.editText_username1);
        editText_password = (EditText) findViewById(R.id.editText_password1);
        button_login = (Button) findViewById(R.id.button_login1);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editText_username.getText().toString();
                String passWord = editText_password.getText().toString();

                if (userName.equals("") || passWord.equals(""))
                    Toast.makeText(Login.this, "Please enter your details: ", Toast.LENGTH_LONG).show();
                else {

                    Boolean checkUserPassword = dbHelper.checkUsernamePassword(userName, passWord);
                    if (checkUserPassword == true) {
                        Toast.makeText(Login.this, "You have successfully logged in: ", Toast.LENGTH_LONG).show();
                        Intent LoginIntent = new Intent(getApplicationContext(), AppointmentBooking.class);
                        startActivity(LoginIntent);
                    } else {
                        Toast.makeText(Login.this, "Sorry, invalid credentials: ", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}

