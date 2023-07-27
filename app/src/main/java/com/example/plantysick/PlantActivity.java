package com.example.plantysick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantActivity extends AppCompatActivity {
    private static APIinterface apIinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);
        apIinterface = API.getRetrofit().create(APIinterface.class);

        apIinterface.getPlants().enqueue(new Callback<List<Plants>>() {
            @Override
            public void onResponse(Call<List<Plants>> call, Response<List<Plants>> response) {
                Log.i("knock", "onResponse: empty");
            }

            @Override
            public void onFailure(Call<List<Plants>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),  t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}