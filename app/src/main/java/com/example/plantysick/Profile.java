package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Profile extends AppCompatActivity {


    ImageButton imageButton;
    Calendar calendar;
    TextView DOB;
    DatabaseHelper databaseHelper;
    SessionManagement sessionManagement;
    TextView userNameTv;
    EditText fullname, email, contact;
    Button saveBtn;
    private String selectedState,selectedDistrict;
    TextView tvStateSpinner, tvDistrictSpinner;
    private Spinner stateSpinner, districtSpinner;
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        getting user name from shared preference
        sessionManagement = new SessionManagement(this);
        String userName = sessionManagement.getSession();

//        getting full information from the database.
        databaseHelper = new DatabaseHelper(this);
        UserDetails userDetails = databaseHelper.finduser(userName);
//        getting views from the layout.


        fullname = findViewById(R.id.Profile_Full_Name);
        contact = findViewById(R.id.Profile_Contact);
        email = findViewById(R.id.Profile_Email);
        userNameTv = findViewById(R.id.Profile_username);
        imageButton = findViewById(R.id.Date_of_birth);
        DOB = findViewById(R.id.set_dob);
        saveBtn = findViewById(R.id.profile_save_button);
        stateSpinner = findViewById(R.id.spinnerBangladeshState);

        calendar = Calendar.getInstance();
//        setting values
        contact.setText(userDetails.getUserContact());
        email.setText(userDetails.getUserEmail());
        userNameTv.setText(userDetails.getUserName());

        String dobText = userDetails.getDOB() != null ? userDetails.getDOB() : "";
        String NameText = userDetails.getFullName() != null ? userDetails.getFullName() : "";
       // String NameText = fullname.getText().toString();
        //String dobText = DOB.getText().toString();
        DOB.setText(dobText);
        fullname.setText(NameText);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(calendar.YEAR, year);
                calendar.set(calendar.MONTH, month);
                calendar.set(calendar.DAY_OF_MONTH, dayOfMonth);
                updateCalender();
            }

            public void updateCalender() {
                String format = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                DOB.setText(sdf.format(calendar.getTime()));
            }
        };
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Profile.this, date, calendar.get(calendar.YEAR),
                        calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH)).show();
            }
        });
        stateAdapter = ArrayAdapter.createFromResource(this,R.array.array_indian_state, R.layout.spinner_layout);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                districtSpinner = findViewById(R.id.spinnerBangladeshDistrict);
                selectedState = stateSpinner.getSelectedItem().toString();

                int parentID = ((ViewGroup)view.getParent()).getId();
                if(parentID == R.id.spinnerBangladeshState){
                    switch (selectedState){
                        case "Select your District": districtAdapter = ArrayAdapter.createFromResource
                                (((ViewGroup) view.getParent()).getContext(),
                                        R.array.array_default_districts, R.layout.spinner_layout);
                            break;
                        case "Chittagong": districtAdapter = ArrayAdapter.createFromResource
                                (((ViewGroup) view.getParent()).getContext(),
                                        R.array.Chittagong, R.layout.spinner_layout);
                            break;
                        case "Dhaka": districtAdapter = ArrayAdapter.createFromResource
                                (((ViewGroup) view.getParent()).getContext(),
                                        R.array.Dhaka, R.layout.spinner_layout);
                            break;
                        case "Rajshahi": districtAdapter = ArrayAdapter.createFromResource
                                (((ViewGroup) view.getParent()).getContext(),
                                        R.array.Rajshahi, R.layout.spinner_layout);
                            break;
                        default:break;
                    }
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    districtSpinner.setAdapter(districtAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String state = stateSpinner.getSelectedItem().toString();
                String district = districtSpinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), state + " " + district, Toast.LENGTH_SHORT).show();

                databaseHelper.updateUserInfo(userNameTv.getText().toString(),
                        fullname.getText().toString(),
                        contact.getText().toString(),
                        DOB.getText().toString(),
                        email.getText().toString(),
                        state, district
                );
            }
        });



    }
}