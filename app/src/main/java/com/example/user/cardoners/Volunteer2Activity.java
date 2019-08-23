package com.example.user.cardoners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.cardoners.dbmanager.DBManager;
import com.example.user.cardoners.dbmanager.Driver;

public class Volunteer2Activity extends AppCompatActivity {

    DBManager dbManager = new DBManager(
            this,
            "register",
            null,1
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer2);

        TextView name = findViewById(R.id.text_volunteer1);
        TextView phone = findViewById(R.id.text_volunteer2);
        TextView age = findViewById(R.id.text_volunteer3);
        TextView carType = findViewById(R.id.text_volunteer4);
        TextView carNumber = findViewById(R.id.text_volunteer5);
        TextView licenseNumber = findViewById(R.id.text_volunteer6);
        TextView licenseType = findViewById(R.id.text_volunteer7);
        TextView licenseDate = findViewById(R.id.text_volunteer8);
        TextView licenseFinish = findViewById(R.id.text_volunteer9);

        Driver UserData =  dbManager.getDriverData();

        name.setText(UserData.getName());
        phone.setText(UserData.getPhone());
        age.setText(UserData.getAge());
        carType.setText(UserData.getCarType());
        carNumber.setText(UserData.getCarNumber());
        licenseNumber.setText(UserData.getLicenseNumber());
        licenseType.setText(UserData.getLicenseType());
        licenseDate.setText(UserData.getLicenseDate());
        licenseFinish.setText(UserData.getLicenseFinish());
    }
}
