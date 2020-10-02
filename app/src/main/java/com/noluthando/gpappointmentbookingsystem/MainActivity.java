package com.noluthando.gpappointmentbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_username, editText_password, editText_confirmPassword;
    Button button_register, button_login;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_confirmPassword = (EditText) findViewById(R.id.editText_confirmPassword);
        button_register = (Button) findViewById(R.id.button_register);
        button_login = (Button) findViewById(R.id.button_login);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editText_username.getText().toString();
                String passWord = editText_password.getText().toString();
                String confirmPassword = editText_confirmPassword.getText().toString();

                if (userName.equals("") || passWord.equals("") || confirmPassword.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter your details: ", Toast.LENGTH_LONG).show();
                else {
                      Toast.makeText(MainActivity.this, "You have been successfully registered: ", Toast.LENGTH_LONG).show();
                                Intent RegisterIntent = new Intent(getApplicationContext(), AppointmentBooking.class);
                                startActivity(RegisterIntent);
                            }
                        }
                    });

button_login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent LoginIntent = new Intent(getApplicationContext(), Login.class);
        startActivity(LoginIntent);

    }
});
                }
            }
