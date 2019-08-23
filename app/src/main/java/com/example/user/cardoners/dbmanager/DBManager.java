package com.example.user.cardoners.dbmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper{

    private Context context;
    private String name;

    //생성자 생성
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //context, db네임, 팩토리 null, 버전 1, DB생성
        super(context, name, factory, version);
        this.context = context;
        this.name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //테이블 생성
        if(name == "register") {

            StringBuffer sb = new StringBuffer();
            sb.append(" CREATE TABLE TEST_TABLE ( ");
            sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sb.append(" NAME TEXT, ");
            sb.append(" PHONE TEXT, ");
            sb.append(" AGE TEXT, ");
            sb.append(" CAR_TYPE TEXT, ");
            sb.append(" CAR_NUMBER TEXT, ");
            sb.append(" LICENSE_NUMBER TEXT, ");
            sb.append(" LICENSE_TYPE TEXT, ");
            sb.append(" LICENSE_DATE TEXT, ");
            sb.append(" CAR_COLOR TEXT ) ");

            db.execSQL(sb.toString());


        }
        else {

            StringBuffer sb = new StringBuffer();
            sb.append(" CREATE TABLE TEST_TABLE ( ");
            sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sb.append(" DISTANCE TEXT, ");
            sb.append(" TIME TEXT, ");
            sb.append(" DEPARTURE TEXT, ");
            sb.append(" DESTINATION TEXT ) ");

            db.execSQL(sb.toString());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           // Toast.makeText(null, "업데이트", Toast.LENGTH_SHORT).show();
    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }

    public void addDriver(Driver driver) {

        //객체 가져오기
        SQLiteDatabase db = getWritableDatabase();

        //driver insert
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO TEST_TABLE ( ");
        sb.append(" NAME, PHONE, AGE, CAR_TYPE, CAR_NUMBER, LICENSE_NUMBER, LICENSE_TYPE, LICENSE_DATE, CAR_COLOR )");
        sb.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?  )");
        db.execSQL(sb.toString(), new Object[] {
                driver.getName(),
                driver.getPhone(),
                driver.getAge(),
                driver.getCarType(),
                driver.getCarNumber(),
                driver.getLicenseNumber(),
                driver.getLicenseType(),
                driver.getLicenseDate(),
                driver.getLicenseFinish() });
    }

    public Driver getDriverData() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, NAME, PHONE, AGE, CAR_TYPE, CAR_NUMBER, LICENSE_NUMBER, LICENSE_TYPE, LICENSE_DATE, CAR_COLOR FROM TEST_TABLE ");

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sb.toString(),null);

        Driver driver = null;

        while( cursor.moveToNext()) {
            driver = new Driver();
            driver.set_id(cursor.getInt(0));
            driver.setName(cursor.getString(1));
            driver.setPhone(cursor.getString(2));
            driver.setAge(cursor.getString(3));
            driver.setCarType(cursor.getString(4));
            driver.setCarNumber(cursor.getString(5));
            driver.setLicenseNumber(cursor.getString(6));
            driver.setLicenseType(cursor.getString(7));
            driver.setLicenseDate(cursor.getString(8));
            driver.setLicenseFinish(cursor.getString(9));
        }

        return driver;
    }

    public void addHistory(DriverHistory dh) {

        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO TEST_TABLE ( ");
        sb.append(" DISTANCE, TIME, DEPARTURE, DESTINATION ) ");
        sb.append(" VALUES ( ?, ?, ?, ? ) ");

        db.execSQL(sb.toString(),
                new Object[] {
                        dh.getDistance(),
                        dh.getTime(),
                        dh.getDeparture(),
                        dh.getDestination()
        });
    }

    public List getHistroy() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, DISTANCE, TIME, DEPARTURE, DESTINATION FROM TEST_TABLE ");

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sb.toString(), null);
        List historyArray = new ArrayList();
        DriverHistory history = null;

        while(cursor.moveToNext()) {
            history = new DriverHistory();
            history.set_id(cursor.getInt(0));
            history.setDistance(cursor.getString(1));
            history.setTime(cursor.getString(2));
            history.setDeparture(cursor.getString(3));
            history.setDestination(cursor.getString(4));

        }
        return historyArray;
    }

}
