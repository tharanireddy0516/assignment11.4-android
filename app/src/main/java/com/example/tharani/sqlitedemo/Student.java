package com.example.tharani.sqlitedemo;
//import is libraries imported for writing the code

/**
 * Created by Tharani on 12/3/2017.
 */
//created java class student
public  class Student {

    // Declaring Variables
    int id;//taking integer for id
    String firstName;//taking string for firstName,lastName
    String lastName;
     //string is a sequence of characters
    public Student(){

    }

    /*Creating getter and setter for the method
    * getters and setters are used to get and set the values*/
    public int getId() {
        return id;//returns id
    }

    public void setId(int id) {//storing id details
        this.id = id;//giving reference to id
    }

    public String getFirstName() {
        return firstName;//returns firstName
    }

    public void setFirstName(String firstName) {//by using set method storing firstName
        this.firstName = firstName;//giving reference to firstName
    }

    public String getLastName() {
        return lastName;//returning lastName
    }

    public void setLastName(String lastName) {//by using set method storing lastName
        this.lastName = lastName;//giving reference to lastName
    }


}

