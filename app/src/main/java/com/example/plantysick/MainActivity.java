package com.example.plantysick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView CardView_community = findViewById(R.id.community_cardView);
        CardView Cardview_profile = findViewById(R.id.profile_cardView);
        CardView Cardview_camera = findViewById(R.id.Identify_cardview);
        CardView Cardview_logout = findViewById(R.id.logout_cardView);
        CardView Cardview_feedback = findViewById(R.id.feedback_cardview);
        CardView CardView_diseases_cure = findViewById(R.id.diesease_cure_cardView);
        CardView_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Community.class);
                startActivity(intent);

            }
        });

        Cardview_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);

            }
        });

        Cardview_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Profile.class);
                startActivity(intent);

            }
        });

        Cardview_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                sessionManagement.removeSession();
                Intent intent = new Intent(getApplicationContext(),LogIn.class);
                startActivity(intent);
                finish();

            }
        });
        Cardview_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Show_Feedback.class);
                startActivity(intent);

            }
        });
        CardView_diseases_cure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DieasesAndCure.class);
                startActivity(intent);

            }
        });

     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.Quick_start){
            Intent intent = new Intent(getApplicationContext(),QuickStart.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"idetify click",Toast.LENGTH_LONG).show();
            return true;
        }
        if(item.getItemId()==R.id.About_us){
            //Intent intent = new Intent(getApplicationContext(),Home.class);
            //startActivity(intent);
            Toast.makeText(getApplicationContext(),"idetify click",Toast.LENGTH_LONG).show();
            return true;
        }
        if(item.getItemId()==R.id.field_monitor){
            Intent intent = new Intent(getApplicationContext(),Field_Monitor.class);
           startActivity(intent);
            Toast.makeText(getApplicationContext(),"field click",Toast.LENGTH_LONG).show();
            return true;
        }
        if(item.getItemId()==R.id.feedback){
            Intent intent = new Intent(getApplicationContext(),Feedback.class);
            startActivity(intent);
             return true;
        }

        return super.onContextItemSelected(item);
    }
}