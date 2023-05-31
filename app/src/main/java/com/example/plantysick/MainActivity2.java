package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private String selectedState,selectedDistrict;
    private Spinner stateSpinner, districtSpinner;
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        stateSpinner = findViewById(R.id.spinnerIndianState);
        stateAdapter = ArrayAdapter.createFromResource(this,R.array.array_indian_state, R.layout.spinner_layout);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                districtSpinner = findViewById(R.id.spinnerIndiandistrict);
                selectedState = stateSpinner.getSelectedItem().toString();

                 int parentID = ((ViewGroup)view.getParent()).getId();
                 if(parentID == R.id.spinnerIndianState){
                     switch (selectedState){
                         case "Select your state": districtAdapter = ArrayAdapter.createFromResource
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
     }
}