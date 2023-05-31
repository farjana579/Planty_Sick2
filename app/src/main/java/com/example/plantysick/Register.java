package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
public class Register extends AppCompatActivity implements View.OnClickListener {

    //create variable for database helper class
    DatabaseHelper databaseHelper;

    //create variable for edit text and button
    public Button logIn, register;
    public EditText user_name, email, password, confirm_password, contact;

    //create variable for userDetails class
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //find the edit text by their id

        user_name=  findViewById(R.id.userNameEditText);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.PassWord);
        confirm_password = findViewById(R.id.editTextConfirmPassword);
        contact = findViewById(R.id.editTextContact);
        logIn = (Button) findViewById(R.id.logBtnInregister);
        register = findViewById(R.id.register_button);
        email = findViewById(R.id.editTextEmail);

        //end of finding id


        //create object for userDetails class and databaseHelper
         databaseHelper = new DatabaseHelper(this);

        //create an varbiable for sqlite database to write
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        //end of creating object

        //calling this method to check correctness of editText
        checkUserData();

        //set onClickListener on button
        register.setOnClickListener(this);
        logIn.setOnClickListener(this);
    }

    //this method to check user information on edit text change
    private void checkUserData() {
        user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String userName = String.valueOf(editable);
                Boolean result= databaseHelper.findUserName(userName);
                if(result==true){
                    user_name.setError("enter an unique user name");
                    user_name.requestFocus();
                    return;
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String userEmail = String.valueOf(editable);
             //checking email is empty or not
                if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    email.setError("Enter a valid email address");
                    email.requestFocus();
                    return;
                }

            }
        });
        contact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String userContact = String.valueOf(editable);
                if(userContact.length()!=11){
                    contact.setError(" contact no length should 11");
                    contact.requestFocus();
                    return;
                }
                else if(!userContact.matches("[0][1][^012]{1}[0-9]{8}")){
                    contact.setError("Enter an valid contact no");
                    contact.requestFocus();
                    return;
                }

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            String userPassword = String.valueOf(editable);
                if(userPassword.length()<8){
                    password.setError("Minimum password length should be 8");
                    password.requestFocus();
                    return;
                }
                if(!userPassword.matches("(.*[A-Z].*)")){
                    password.setError("Password should contain one upper case, lower case, a digit.");
                    password.requestFocus();
                    return;
                }
                if(!userPassword.matches("(.*[a-z].*)")){
                    password.setError("Password should contain one upper case, lower case, a digit.");
                    password.requestFocus();
                    return;
                }
                if(!userPassword.matches("(.*[0-9].*)")){
                    password.setError("Password should contain one upper case, lower case, a digit.");
                    password.requestFocus();
                    return;
                }
            }
        });
    }

    //implenting the method of setOnclickListener
    @Override
    public void onClick(View view) {

        //checking if the button is register or log in button
          if(view.getId()==R.id.register_button){
             if(user_name.getError()==null && email.getError()==null && password.getError()==null &&
                     confirm_password.getError()==null && contact.getError()==null) {
                 newUserRegister();
             }
             else{
                 Toast.makeText(this, "Fill Up Correctly", Toast.LENGTH_SHORT).show();
             }

         }
         else if(view.getId()==R.id.logBtnInregister){
             Intent intent = new Intent(getApplicationContext(), LogIn.class);
             startActivity(intent);
      }
    }


    //creating a new user with correct information
    private void newUserRegister() {

        //taking the userInformation from editText
        String userName = user_name.getText().toString();
        String userEmail = email.getText().toString();
        String userContact = contact.getText().toString();
        String userPassword = password.getText().toString();
        String userConfirmPassword = confirm_password.getText().toString();
        //end of taking information

        //setting error if any editText is empty
        if(userName.isEmpty()){
            user_name.setError("This field shoudn't be empty");
            user_name.requestFocus();
            return;
        }
        if(userEmail.isEmpty()){
            email.setError("Enter an email address");
            email.requestFocus();
            return;
        }
        if(userContact.isEmpty()){
            contact.setError("Enter an phone number");
            contact.requestFocus();
            return;
        }
        if(userPassword.isEmpty()){
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }
        if(userConfirmPassword.isEmpty()){
            confirm_password.setError("Enter a password");
            confirm_password.requestFocus();
            return;
        }
        if(!userConfirmPassword.equals(userPassword)){
            confirm_password.setError("Password not match");
            confirm_password.requestFocus();
            return;
        }
        //ending error

        //senditg the information to userDetail Class

        userDetails = new UserDetails(this, userName, userEmail, userContact,userConfirmPassword, "", "");
        //inserting the data in the database using databaseHelper object
        long rowId = databaseHelper.insertData(userDetails);
        if(rowId>0){
            Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),LogIn.class);
            startActivity(intent);
            finish();

        }
        else{
            Toast.makeText(getApplicationContext(),"Regitration failed ",Toast.LENGTH_SHORT).show();
        }
    }
}