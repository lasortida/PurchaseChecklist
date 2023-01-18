package com.example.purchasechecklist;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity {

    ImageView imageView;
    Button scan, reshoot, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = findViewById(R.id.imageView);
        scan = findViewById(R.id.scan);
        reshoot = findViewById(R.id.reshoot);
        back = findViewById(R.id.back);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                            bitmap = Bitmap.createScaledBitmap(bitmap, 700, 700, true);
                            imageView.setImageBitmap(bitmap);
                        } else {
                            onBackPressed();
                        }
                    }
                }
        );

        resultLauncher.launch(intent);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        reshoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultLauncher.launch(intent);
            }
        });

    }
}