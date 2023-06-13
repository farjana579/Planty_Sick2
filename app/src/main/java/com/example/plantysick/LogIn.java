package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity implements View.OnClickListener {


    public EditText userName, password;
    public Button login, register, rememberMe;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

         userName=(EditText) findViewById(R.id.userName);
         password = (EditText) findViewById(R.id.userPassword);
         login = findViewById(R.id.logInButton);
         register = findViewById(R.id.register_button);
         rememberMe = findViewById(R.id.rememberMe);
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        rememberMe.setOnClickListener(this);
    }
    public void onStart() {

        super.onStart();
        SessionManagement sessionManagement = new SessionManagement(LogIn.this);
        String username = sessionManagement.getSession();
        if(!username.equals("1")) {
            moveMainactivity();
        }
        else{

        }
    }

    @Override
    public void onClick(View view) {
        String user_name = userName.getText().toString();
        String Password = password.getText().toString();
        if(view.getId()==R.id.logInButton){

            //it will check user and password is correct or not if anyone want to log in
            boolean result = databaseHelper.findpassword(user_name, Password);
            Log.i("farjana", "onClick: " + result);
            if(result == true){



                    SessionManagement sessionManagement = new SessionManagement(LogIn.this);
                    sessionManagement.saveSession(user_name);

                    moveMainactivity();


            }
            else{
                Toast.makeText(getApplicationContext(),"wrong user_name or password", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId()==R.id.register_button){
            Intent intent;
            intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
            finish();
        }
    }
    public void moveMainactivity(){
        Intent intent;
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}