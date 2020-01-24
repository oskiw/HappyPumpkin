package com.example.happypumpkin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HttpGetRequest extends AsyncTask<String, Void, Boolean> {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Boolean doInBackground(String... urls) {
        getKittens1(urls[0]);
        getKittens2(urls[1]);

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getKittens1(String url) {
        String prefix = "&picture='+encodeURIComponent('";
        String suffix = "') +'\\";

        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            InputStream response = connection.getInputStream();

            try (Scanner scanner = new Scanner(response)) {
                while (scanner.hasNext()) {
                    String responseBody = scanner.nextLine().trim();

                    if (responseBody.startsWith(prefix) && responseBody.endsWith(suffix)) {
                        String b = responseBody.substring(prefix.length(), responseBody.length() - suffix.length());
                        try {
                            InputStream in = new java.net.URL(b).openStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(in);
                            MainActivity.images.add(bitmap);
                            System.out.println("Added 2");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getKittens2(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            InputStream response = connection.getInputStream();

            try (Scanner scanner = new Scanner(response)) {
                while (scanner.hasNext()) {
                    String responseBody = scanner.nextLine().trim();

                    if (responseBody.contains("<img src=")) {
                        String a = responseBody.substring(10);
                        String b = a.substring(0, a.indexOf("\""));
                        try {
                            InputStream in = new java.net.URL(b).openStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(in);
                            MainActivity.images.add(bitmap);
                            System.out.println("Added 1");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
