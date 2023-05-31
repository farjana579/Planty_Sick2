package com.example.plantysick;

import static org.tensorflow.lite.support.image.TensorImage.fromBitmap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantysick.ml.Model;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;

import java.io.IOException;
import java.util.List;

public class Home extends AppCompatActivity {

    TextView result;
    Button camera, gallery;
    ImageView capturedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //finding the "take a picture" button for camera permission
        camera = findViewById(R.id.scan_button);

        //FINDING THE "CHOOSEN FROM GALLERY " BUTTON FOR GALLERY PERMISSION
        gallery = findViewById(R.id.scan_button_gallery);

        //FINDING THE IMAGE VIEW FOR CAPTURED IMAGE TO SHOW
        capturedImage=findViewById(R.id.CapturedImage);

        //FINDING THE TEXT TP SHOW THE PREDICTED RESULT
        result = findViewById(R.id.result);

        //checking camera permission
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 3);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                    }
                }
            }
        });

        //checking gallery permission
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent, 1);
            }
        });
    //end of on create method
    }
 


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){

            //PICTURE FROM CAMERA
            //requestcode for camera is 3 in line 47

            if(requestCode==3) {
             Bitmap img = (Bitmap) data.getExtras().get("data");
             capturedImage.setImageBitmap(img);
             classifyImage(img);
            }
            //PICTURE FROM GALLERY
            else{
                Uri dat = data.getData();
                Bitmap img = null;
                try{
                    img = MediaStore.Images.Media.getBitmap(this.getContentResolver(),dat);
                }
                 catch (IOException e){
                    e.printStackTrace();
                 }
                capturedImage.setImageBitmap(img);
                classifyImage(img);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void classifyImage(Bitmap img) {
        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorImage image = TensorImage.fromBitmap(img);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(image);
            List<Category> probability = outputs.getProbabilityAsCategoryList();
            //Toast.makeText(getApplicationContext(),"bkah blah",Toast.LENGTH_LONG).show();

            int index = getMax(probability);
            String disease = probability.get(index).getLabel();

            //getting the accuracy
            float guess = probability.get(index).getScore();
            result.setText("Your crop is affected by "+disease);
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    private int getMax(List<Category> prob) {
        float max = 0;
        int index = 0;
        for(int i=0; i<prob.size(); i++){
            if(max <= prob.get(i).getScore()){
                max = prob.get(i).getScore();
                index = i;
            }
        }
        return index;
    }
}
