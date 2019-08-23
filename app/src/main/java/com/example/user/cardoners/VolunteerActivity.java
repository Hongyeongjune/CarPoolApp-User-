package com.example.user.cardoners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.cardoners.dbmanager.DBManager;
import com.example.user.cardoners.dbmanager.Driver;

public class VolunteerActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_volunteer);

        button = (Button) findViewById(R.id.dreiverinfo_next);

        dbManager = new DBManager(
                this,
                "register",
                null,1
        );

        final EditText editTextName = (EditText) findViewById(R.id.edit_volunteer1);
        final EditText editTextPhone = (EditText) findViewById(R.id.edit_volunteer2);
        final EditText editTextAge = (EditText) findViewById(R.id.edit_volunteer3);
        final EditText editTextCarType = (EditText) findViewById(R.id.edit_volunteer4);
        final EditText editTextCarNumber = (EditText) findViewById(R.id.edit_volunteer5);
        final EditText editTextLicenseNumber = (EditText) findViewById(R.id.edit_volunteer6);
        final EditText editTextLicenseType = (EditText) findViewById(R.id.edit_volunteer7);
        final EditText editTextLicenseDate = (EditText) findViewById(R.id.edit_volunteer8);
        final EditText editTextLicenseFinish = (EditText) findViewById(R.id.edit_volunteer9);




        /*
            TextView name = findViewById(R.id.text_volunteer1);
            TextView phone = findViewById(R.id.text_volunteer2);
            TextView age = findViewById(R.id.text_volunteer3);

            Driver UserData =  dbManager.getDriverData();

            name.setText("이름 :"+UserData.getName());
            phone.setText("핸드폰번호 :"+UserData.getPhone());
            age.setText("생년월일 :"+UserData.getAge());
            */


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String name = editTextName.getText().toString();
                String phone = editTextPhone.getText().toString();
                String age = editTextAge.getText().toString();
                String carType = editTextCarType.getText().toString();
                String carNumber = editTextCarNumber.getText().toString();
                String licenseNumber = editTextLicenseNumber.getText().toString();
                String licenseType = editTextLicenseType.getText().toString();
                String licenseDate = editTextLicenseDate.getText().toString();
                String licenseFinish = editTextLicenseFinish.getText().toString();

                Driver driver = new Driver();

                driver.setName(name);
                driver.setPhone(phone);
                driver.setAge(age);
                driver.setCarType(carType);
                driver.setCarNumber(carNumber);
                driver.setLicenseNumber(licenseNumber);
                driver.setLicenseType(licenseType);
                driver.setLicenseDate(licenseDate);
                driver.setLicenseFinish(licenseFinish);

                dbManager.addDriver(driver);

                Intent intent = new Intent(VolunteerActivity.this, Main2Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

    }

}
