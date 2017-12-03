package com.example.tharani.sqlitedemo;
//import is libraries imported for writing the code
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
//ArrayList is the size of the array used to store the elements in the list. It is always at least as large as the list size.

/**
 * Created by Tharani on 12/3/2017.
 */
//created class Database which extends SQLiteOpenHelper
    /*For maximum control over local data, iam using  SQLite
directly by leveraging SQLiteOpenHelper for executing SQL requests and managing a local database.*/
public class Database extends SQLiteOpenHelper {
    // Declaring database variables
    public static final String DATABASE_NAME ="student.db"; // Initializing Database_Name
    public static final String DATABASE_TABLE_NAME = "Students"; // Initializing Table_Name
    public static final String STUDENT_ID = "id"; // Initializing Student_ID
    public static final String STUDENT_FIRST_NAME = "FirstName"; // Initializing First_Name
    public static final String STUDENT_LAST_NAME = "LasttName"; // Initializing Last_Name
    //dbms query for CREATE_TABLE
    private static final String CREATE_TABLE =
            "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                    STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + STUDENT_FIRST_NAME + " text,"
                    + STUDENT_LAST_NAME + " text);";

     //Context allows access to application-specific resources and classes, as well as calls for application-level
    Database(Context context){
        super(context,DATABASE_NAME,null,2);
    }

    // onCreate is called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase database) {
        //here we creating table in database
        database.execSQL(CREATE_TABLE);

    }

    //onUpgrade method is called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS Students");
        onCreate(database);


    }
    //Allocates a Boolean object representing the value true or else returns false

    public boolean addStudent(Student student) { //Creating method
        /*
          getWritableDatabase() Create or open a database that will be used for
           reading and writing.
         */
        SQLiteDatabase database = this.getWritableDatabase();
        //ContentValues is used to get the values from database tables
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_FIRST_NAME, student.firstName);
        contentValues.put(STUDENT_LAST_NAME, student.lastName);
        database.insert(DATABASE_TABLE_NAME, null, contentValues);
        return true;//returns true
    }



    public int numberOfRows(){ //Creating method
        // getReadableDatabase() Create or open a database.

        SQLiteDatabase database = this.getReadableDatabase();//giving reference
        return (int) DatabaseUtils.queryNumEntries(database, DATABASE_TABLE_NAME);
    }

    public ArrayList<Student> getAllStudent() { // Creating an ArrayList
        ArrayList<Student> studentList = new ArrayList<>();
        String selectQuery = "Select * From Students";
        //here by using this dbms query we can get the required student details
        /**getWriteDatabase This method always returns very quickly.
         The database is not actually created or opened until one of getWritableDatabase() or getReadableDatabase() is called
        */
        SQLiteDatabase database = this.getWritableDatabase();

        //Cursor exposes results from a query on a SQLiteDatabase.
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) { // If statement
            do { // do while loop to add rows into the list
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setFirstName(cursor.getString(1));
                student.setLastName(cursor.getString(2));

                //Adding student data into list
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        //Closing database
        database.close();

        return studentList; // Return student list

    }

}

