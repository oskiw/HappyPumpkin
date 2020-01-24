package com.example.happypumpkin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity {

    static String kittensUrl1 = "https://www.boredpanda.com/cute-kittens/?utm_source=google&utm_medium=organic&utm_campaign=organic";
    static String kittensUrl2 = "https://www.buzzfeed.com/chelseamarshall/best-kitten-pictures";

    static List<Bitmap> images = new CopyOnWriteArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new HttpGetRequest().execute(kittensUrl1, kittensUrl2);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.happy_pumpkin);
    }

    public void setImage(View view) {
        ImageView imageView = findViewById(R.id.imageView);

        while (images.size() == 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int i = new Random().nextInt(images.size());
        Bitmap image = images.get(i);

        imageView.setImageBitmap(image);
    }
}
