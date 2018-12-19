package com.smilepass.facesdksample.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.smilepass.mobilesdk.model.AutoSelfie;
import com.smilepass.facesdksample.R;

import java.io.FileNotFoundException;

public class ImageViewerActivity extends AppCompatActivity {

    private Context context;
    private ImageView ivSelfie;

    public static void start(Context context, String fileName) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra(AutoSelfie.FILE_NAME, fileName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        context = ImageViewerActivity.this;
        initView();
    }

    private void initView() {
        ivSelfie = findViewById(R.id.ivSelfie);
        Intent intent = getIntent();
        String fileName = intent.getStringExtra(AutoSelfie.FILE_NAME);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(context
                    .openFileInput(fileName));
            ivSelfie.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
