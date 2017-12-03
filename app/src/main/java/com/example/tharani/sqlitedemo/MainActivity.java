package com.example.tharani.sqlitedemo;
/*import is libraries imported for writing the code
* AppCompatActivity is base class for activities
* Bundle handles the orientation of the activity
*/
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
//ArrayList is the size of the array used to store the elements in the list. It is always at least as large as the list size.
import java.util.ArrayList;
/*onCreate is the first method in the life cycle of an activity
   * savedInstance passes data to super class,data is pull to store state of application
   * setContentView is used to set layout for the activity
   *  R is a resource and it is auto generate file
   * activity_main assign an integer value
   */


public class MainActivity extends AppCompatActivity {
    //Declaring variables
    TextView textView;
    Student student;
    String[]  firstName, lastName;//using string for first name,last name
    //string is  a sequence of character
    private Database database;//taking  database as private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adding first name
        firstName = new String[]{"Tharani","Bhavitha","Mithra","Mani"};
        //Adding last name
        lastName = new String[]{"Chelumalla","Guduri","Devaram","Annam"};

        textView =  findViewById(R.id.textView); // Initializing object by ID
        /*The Logcat window in Android Studio displays system messages, such as when a garbage collection occurs
         messages that you added to your app with the Log class*/
        Log.d("Insert: ", "Inserting .."); // Log.d() method is used to log debug messages.

        database = new Database(this); // Creating database object
        if(database.numberOfRows()>0){
            Log.e("DB","Database already exist."); // Log.e() method is used to log errors.
        }
        else{
            saveDataInDB(); // Saving database
            Log.e("DB ","Data Saved in Database.");// Log.e() method is used to log errors.

        }

        /*
        Handler() is a default constructor associates this handler with the Looper for the
           current thread.
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {//void wont returns any value
                if (loadDataFromDB()) {
                    //if statement executes from top to down and decides whether a certain statement will executes or not
                    Log.e("DB ","Data Loaded from Database.");
                }
            }
        }, 0);

    }

    Boolean saveDataInDB() {
        //Saving Student details into the database
        student = new Student();
        for (int i=0;i<firstName.length;i++){
            /*for loop provides clear information in short way of writing the loop structure and easy to debug*/
            student.setFirstName(firstName[i]);
            student.setLastName(lastName[i]);
           // using log for inserting
            Log.d("Insert: ", "Inserting ..");
            database.addStudent(student);
        }

        return true;//returns true

    }

    Boolean loadDataFromDB() {
        try {//if their is any exception found in try block  it will be catch by catch block
            // Retrieve or load student data from the database
            ArrayList array_list = database.getAllStudent();
            Log.e("Employee Size ", String.valueOf(array_list.size()));

            if(!array_list.isEmpty()){ // if Statement executes from top to down and decides whether a certain statement will executes or not

                /*
                  StringBuilder class is used to create mutable string and
                   it is non-synchronized.
                 */
                StringBuilder stringBuilderFull,stringBuilderStudent;
                stringBuilderFull = new StringBuilder(); // Creating object

                // Creating an ArrayList
                for (int i=0;i<array_list.size();i++){
                   // for loop provides clear information in short way of writing the loop structure and easy to debug*/
                    Student stu = (Student) array_list.get(i);
                    /*by taking stringBuilder the student details cant gets synchronized
                    * creating new stringBuilder using new keyword */

                    stringBuilderStudent=  new StringBuilder().append("\nId : ").append(stu.getId()).append("\n")
                            .append("First Name: ").append(stu.getFirstName()).append("\n")
                            .append("Last Name: ").append(stu.getLastName())
                            .append("\n").append("\n");
                    stringBuilderFull.append(stringBuilderStudent);
                }

                textView.setText(stringBuilderFull);//setting text
               // when you're building the string to pass into Log.d, the compiler uses a StringBuilder
                Log.e("DB ", "Full details displayed.");
            }else {
                Log.e("DB ", "No Employee available.");
            }

            return true;//returns true
        } catch (Exception e) {//or else catch exception

            return false;//returns false
        }
    }
}
