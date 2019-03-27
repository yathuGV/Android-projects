package com.example.yathu.sms;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "customer.db";
    //create registration table
    public static final String TABLE_NAME = "customer_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";
    public static final String COL_5 = "CONFIRM_PASSWORD";
    //create school details table
    public static final String TABLE_SCL_DETAILS = "school_details_table";
    public static final String SCL_ID = "SCL_ID";
    public static final String SCL_NAME= "SCHOOL_NAME";
    public static final String SCL_ADD = "ADDRESS";
    public static final String SCL_REG = "REG_NUM";
    public static final String SCL_PRINCI = "PRINCIPAL";
    public static final String SCL_NO_OF_TEA = "NUM_OF_TEACHERS";
    public static final String SCL_NO_NON_ACC_STAFFS = "NUM_OF_NON_ACC_STAFFS";
    public static final String SCL_NO_OF_STU = "NUM_OF_STUDENTS";
    //create sport details table
    public static final String TABLE_SPORT_DETAILS = "sport_details_table";
    public static final String SPORT_ID = "SPORT_ID";
    public static final String STUDENT_NAME= "STU_NAME";
    public static final String STUDENT_ID = "STU_ID";
    public static final String STUDENT_CLASS = "STU_CLASS";
    public static final String SPORT = "SPORT";
    public static final String STAGE = "STAGE";
    public static final String AWARDS = "AWARDS";
    public static final String REMARK = "REMARK";
    //create student details table
    public static final String TABLE_STU_DETAILS = "student_details_table";
    public static final String STU_FNAME= "STU_FIRST_NAME";
    public static final String STU_lNAME = "STU_LAST_NAME";
    public static final String STU_ID = "STU_ID";
    public static final String STU_DOB = "STU_DOB";
    public static final String STU_CLASS = "STU_CLASS";
    public static final String STU_SECTION = "STU_SECTION";
    public static final String STU_PHONE = "STU_PHONE";
    public static final String STU_BLOODGRP = "STU_BLOOD_GRP";
    //create teachers details table
    public static final String TABLE_TEA_DETAILS = "teachers_details_table";
    public static final String TEA_NAME= "TEA_NAME";
    public static final String TEA_ID = "TEA_ID";
    public static final String TEA_AGE = "TEA_AGE";
    public static final String TEA_ADD = "TEA_ADD";
    public static final String TEA_SUB = "TEA_SUB";
    public static final String TEA_DOB = "TEA_DOB";
    public static final String TEA_PHONE = "TEA_PHONE";
    //create other staff details table
    public static final String TABLE_OTHER_STAFF_DETAILS="other_staff_details_table";
    public static final String OS_NAME="OS_NAME";
    public static final String OS_ID="OS_ID";
    public static final String OS_AGE="OS_AGE";
    public static final String OS_ADD="OS_ADD";
    public static final String OS_JOB="OS_JOB";
    public static final String OS_DOB="OS_DOB";
    public static final String OS_PHONE="OS_PHONE";
    //create attendance details table
    public static final String TABLE_ATTENDANCE_DETAILS = "attendance_details_table";
    public static final String AD_STU_NAME = "AD_STU_NAME";
    public static final String AD_STU_ID= "AD_STU_ID";
    public static final String AD_STU_CLASS = "CLASS";
    public static final String AD_STU_SECTION = "SECTION";
    public static final String AD_DATE = "DATE";
    public static final String AD_ATTENDANCE = "ATTENDANCE";
    public static final String AD_REMARK = "REMARK";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase customerdb) {
        customerdb.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, EMAIL TEXT , PASSWORD TEXT, CONFIRM_PASSWORD)");
        customerdb.execSQL("create table " + TABLE_SCL_DETAILS + " (SCL_ID TEXT PRIMARY KEY , SCHOOL_NAME TEXT , ADDRESS TEXT , REG_NUM INTEGER, PRINCIPAL TEXT, NUM_OF_TEACHERS INTEGER, NUM_OF_NON_ACC_STAFFS INTEGER, NUM_OF_STUDENTS TEXT)");
        customerdb.execSQL("create table " + TABLE_SPORT_DETAILS + " (SPORT_ID TEXT PRIMARY KEY , STU_NAME TEXT , STUDENT_ID TEXT , STU_CLASS TEXT, SPORT TEXT, STAGE TEXT, AWARDS TEXT, REMARK TEXT)");
        customerdb.execSQL("create table " + TABLE_STU_DETAILS + " (STU_FIRST_NAME TEXT , STU_LAST_NAME TEXT , STU_ID INTEGER PRIMARY KEY, STU_DOB DATE, STU_CLASS TEXT, STU_SECTION TEXT, STU_PHONE INTEGER,STU_BLOOD_GRP TEXT)");
        customerdb.execSQL("create table " + TABLE_TEA_DETAILS + " (TEA_NAME TEXT , TEA_ID INTEGER PRIMARY KEY, TEA_AGE INTEGER, TEA_ADD TEXT, TEA_SUB TEXT, TEA_DOB DATE, TEA_PHONE INTEGER)");
        customerdb.execSQL("create table " + TABLE_OTHER_STAFF_DETAILS + " (OS_NAME TEXT , OS_ID INTEGER PRIMARY KEY, OS_ADD TEXT , OS_JOB TEXT , OS_DOB DATE , OS_PHONE INTEGER)");
        customerdb.execSQL("create table " + TABLE_ATTENDANCE_DETAILS + " (AD_STU_NAME TEXT , AD_STU_ID INTEGER PRIMARY KEY, CLASS TEXT , SECTION TEXT, DATE DATE, ATTENDANCE TEXT , REMARK TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase customerdb, int i, int i1) {
        customerdb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        customerdb.execSQL("DROP TABLE IF EXISTS " + TABLE_SCL_DETAILS);
        customerdb.execSQL("DROP TABLE IF EXISTS " + TABLE_SPORT_DETAILS);
        customerdb.execSQL("DROP TABLE IF EXISTS " + TABLE_STU_DETAILS);
        customerdb.execSQL("DROP TABLE IF EXISTS " + TABLE_TEA_DETAILS);
        customerdb.execSQL("DROP TABLE IF EXISTS " + TABLE_OTHER_STAFF_DETAILS);
        customerdb.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE_DETAILS);

        onCreate(customerdb);
    }
    //insert data into registration table
    public boolean insertData(String name, String email, String password, String conpassword)
    {
        SQLiteDatabase customerdb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);
        contentValues.put(COL_5, conpassword);
        long result= customerdb.insert(TABLE_NAME, null, contentValues);
        if (result== -1)
            return false;
        else
            return true;

    }
    //Insert details into school details table
    public boolean insertSclDetails(String sname, String address, String regno, String princi, String numoftea, String numofnonaccstaff,String numodstu)
    {
        SQLiteDatabase customerdb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCL_NAME, sname);
        contentValues.put(SCL_ADD, address);
        contentValues.put(SCL_REG, regno);
        contentValues.put(SCL_PRINCI, princi);
        contentValues.put(SCL_NO_OF_TEA, numoftea);
        contentValues.put(SCL_NO_NON_ACC_STAFFS, numofnonaccstaff);
        contentValues.put(SCL_NO_OF_STU, numodstu);

        long result=customerdb.insert(TABLE_SCL_DETAILS, null, contentValues);
        if (result== -1)
            return false;
        else
            return true;

    }
    //insert details into sport details table
    public boolean insertSportDetails(String stuname, String stuid, String stuclass, String sport, String stage,String award, String remark)
    {
        SQLiteDatabase customerdb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NAME, stuname);
        contentValues.put(STUDENT_ID,stuid );
        contentValues.put(STUDENT_CLASS,stuclass);
        contentValues.put(SPORT, sport);
        contentValues.put(STAGE, stage);
        contentValues.put(AWARDS, award);
        contentValues.put(REMARK, remark);


        long result=customerdb.insert(TABLE_SPORT_DETAILS, null, contentValues);
        if (result== -1)
            return false;
        else
            return true;

    }
    //insert details into student details table
    public boolean insertStuDetails(String fname, String lname, String stid, String stdob, String stclass, String stsection,String stphone,String stbd)
    {
        SQLiteDatabase customerdb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STU_FNAME, fname);
        contentValues.put(STU_lNAME, lname);
        contentValues.put(STU_ID, stid);
        contentValues.put(STU_DOB, stdob);
        contentValues.put(STU_CLASS, stclass);
        contentValues.put(STU_SECTION, stsection);
        contentValues.put(STU_PHONE, stphone);
        contentValues.put(STU_BLOODGRP,stbd);

        long result=customerdb.insert(TABLE_STU_DETAILS, null, contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }
    //insert details into teachers details table
    public boolean insertTeaDetails(String tname, String tid, String tage, String tadd, String tsub, String tdob,String tphone)
    {
        SQLiteDatabase customerdb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEA_NAME, tname);
        contentValues.put(TEA_ID, tid);
        contentValues.put(TEA_AGE, tage);
        contentValues.put(TEA_ADD, tadd);
        contentValues.put(TEA_SUB, tsub);
        contentValues.put(TEA_DOB, tdob);
        contentValues.put(TEA_PHONE, tphone);


        long result=customerdb.insert(TABLE_TEA_DETAILS, null, contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }
    //insert details into other staff details table
    public boolean insertOtherStaffDetails(String oname,String oid,String oage,String oadd, String ojob, String odob, String ophone)
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OS_NAME,oname);
        contentValues.put(OS_ID,oid);
        contentValues.put(OS_AGE,oage);
        contentValues.put(OS_ADD,oadd);
        contentValues.put(OS_JOB,ojob);
        contentValues.put(OS_DOB,odob);
        contentValues.put(OS_PHONE,ophone);

        long result=customerdb.insert(TABLE_OTHER_STAFF_DETAILS,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;
    }
    //insert details into attendance details table
    public boolean insertAttendanceDetails(String adname, String adid, String adclass, String adsection, String addate, String adatt,String reamrk)
    {
        SQLiteDatabase customerdb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AD_STU_NAME, adname);
        contentValues.put(AD_STU_ID, adid);
        contentValues.put(AD_STU_CLASS, adclass);
        contentValues.put(AD_STU_SECTION, adsection);
        contentValues.put(AD_DATE, addate);
        contentValues.put(AD_ATTENDANCE, adatt);
        contentValues.put(AD_REMARK, reamrk);

        long result=customerdb.insert(TABLE_ATTENDANCE_DETAILS, null, contentValues);
        if (result== -1)
            return false;
        else
            return true;

    }
    //Get school data
    public Cursor getSclData()
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        Cursor res =customerdb.rawQuery("SELECT * FROM "+ TABLE_SCL_DETAILS,null);
        return res;
    }
    //get sport details
    public Cursor getSportData()
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        Cursor res =customerdb.rawQuery("SELECT * FROM "+ TABLE_SPORT_DETAILS,null);
        return res;
    }
    //get student details
    public Cursor getStuData()
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        Cursor res =customerdb.rawQuery("SELECT * FROM "+ TABLE_STU_DETAILS,null);
        return res;
    }
    //get teachers details
    public Cursor getTeaData()
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        Cursor res =customerdb.rawQuery("SELECT * FROM "+ TABLE_TEA_DETAILS,null);
        return res;
    }
    //get Attendance details
    public Cursor getAttData()
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        Cursor res =customerdb.rawQuery("SELECT * FROM "+ TABLE_ATTENDANCE_DETAILS,null);
        return res;
    }
    //update school details
    public boolean UpdateSclDetails(String sname, String address, String regno, String princi, String numoftea, String numofnonaccstaff,String numodstu)
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SCL_NAME,sname);
        contentValues.put(SCL_ADD, address);
        contentValues.put(SCL_REG, regno);
        contentValues.put(SCL_PRINCI, princi);
        contentValues.put(SCL_NO_OF_TEA, numoftea);
        contentValues.put(SCL_NO_NON_ACC_STAFFS, numofnonaccstaff);
        contentValues.put(SCL_NO_OF_STU, numodstu);
        customerdb.update(TABLE_SCL_DETAILS,contentValues,"SCHOOL_NAME=?",new String[]{sname});
        return true;
    }
    //update sports details
    public boolean UpdateSportDetails(String stuname, String stuid, String stuclass, String sport, String stage, String award,String remark)
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(STUDENT_NAME,stuname);
        contentValues.put(STUDENT_ID, stuid);
        contentValues.put(STUDENT_CLASS, stuclass);
        contentValues.put(SPORT, sport);
        contentValues.put(STAGE, stage);
        contentValues.put(AWARDS, award);
        contentValues.put(REMARK, remark);
        customerdb.update(TABLE_SPORT_DETAILS,contentValues,"STUDENT_NAME=?",new String[]{stuname});
        return true;
    }
    //update student details
    public boolean UpdateStuDetails(String stufname, String stulname, String stuid, String studob, String stuclass, String stusection,String stuphone,String stublooddrp )
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(STU_FNAME,stufname);
        contentValues.put(STU_lNAME, stulname);
        contentValues.put(STU_ID, stuid);
        contentValues.put(STU_DOB, studob);
        contentValues.put(STU_CLASS, stuclass);
        contentValues.put(STU_SECTION, stusection);
        contentValues.put(STU_PHONE, stuphone);
        contentValues.put(STU_BLOODGRP,stublooddrp);
        customerdb.update(TABLE_STU_DETAILS,contentValues,"STU_ID=?",new String[]{stuid});
        return true;
    }
    public Boolean NamePassword(String name,String password)
    {
        SQLiteDatabase customerdb=this.getReadableDatabase();
        String sqlQuery = "select * from "+TABLE_NAME+" where "+COL_2+"='"+name+"' and "+COL_4+"='"+password+"'";

        Cursor cursor =customerdb.rawQuery(sqlQuery,null);
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public  Cursor searchSportData(String rollno)
    {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor re =db.rawQuery("select * from TABLE_NAME where ROLL_NO='"+rollno+"'",null);
        //  Cursor re=db.rawQuery(sqlQuery,null);
        return re;
    }
    public Integer StuDeleteData(String stuid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_STU_DETAILS, "STU_ID = ?", new String[] {stuid});

    }



}

