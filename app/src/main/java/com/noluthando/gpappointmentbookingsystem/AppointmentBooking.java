package com.noluthando.gpappointmentbookingsystem;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AppointmentBooking extends AppCompatActivity {
    DBHelper dbHelper;
    EditText editText_ID, editText_name, editText_surname, editText_date, editText_time;
    Button button_addData, button_deleteData, button_updateData, button_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        dbHelper = new DBHelper(this);
        editText_ID = (EditText) findViewById(R.id.editText_ID);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_surname = (EditText) findViewById(R.id.editText_surname);
        editText_date = (EditText) findViewById(R.id.editText_date);
        editText_time = (EditText) findViewById(R.id.editText_time);
        button_addData = (Button) findViewById(R.id.button_addData);
        button_deleteData = (Button) findViewById(R.id.button_deleteData);
        button_updateData = (Button) findViewById(R.id.button_updateData);
        button_View = (Button) findViewById(R.id.button_View);
        AddData();
        DeleteData();
        ViewData();
        UpdateData();

    }
    public void AddData() {
        button_addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                boolean isInserted = dbHelper.addData(editText_name.getText().toString(),
                        editText_surname.getText().toString(), editText_date.getText().toString(),editText_time.getText().toString());
                if(isInserted == true)
                    Toast.makeText(AppointmentBooking.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AppointmentBooking.this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }

        });

    }
    public void UpdateData() {
        button_updateData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                boolean update = dbHelper.updateData(editText_ID.getText().toString(), editText_name.getText().toString(),
                        editText_surname.getText().toString(), editText_date.getText().toString(),editText_time.getText().toString());
                if(update == true)
                    Toast.makeText(AppointmentBooking.this, "Data is Updated: ", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AppointmentBooking.this, "Data is not Updated: ", Toast.LENGTH_LONG).show();
            }

        });

    }
    public void DeleteData() {
        button_deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = dbHelper.deleteData(editText_ID.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(AppointmentBooking.this, "Data is Deleted: ", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AppointmentBooking.this, "Data not Deleted: ", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void ViewData() {
        button_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = dbHelper.getAll();
                if(res.getCount() == 0){

                    showMessage("Error: ", "No Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("Name : " + res.getString(1) + "\n");
                    buffer.append("Surname : " + res.getString(2) + "\n");
                    buffer.append("Date : " + res.getString(3) + "\n");
                    buffer.append("Time : " + res.getString(4) + "\n\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
