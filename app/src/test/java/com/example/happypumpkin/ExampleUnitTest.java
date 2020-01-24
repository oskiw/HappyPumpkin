package com.example.happypumpkin;

import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testHttpRequest() throws InterruptedException {

        List<String> images = doInBackground(MainActivity.kittensUrl2);

        //System.out.println(images.get(0));

    }

    private static List<String> doInBackground(String... urls) {
        List<String> images = new ArrayList<>();
        String prefix = "&picture='+encodeURIComponent('";
        String suffix = "') +'\\";
        try {
            URLConnection connection = new URL(urls[0]).openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            InputStream response = connection.getInputStream();

            int counter = 0;
            try (Scanner scanner = new Scanner(response)) {
                while (scanner.hasNext()) {
                    String responseBody = scanner.nextLine().trim();
                    if (responseBody.startsWith(prefix) && responseBody.endsWith(suffix)) {
                        counter ++;

                        String a = responseBody.substring(prefix.length(), responseBody.length() - suffix.length());
                        System.out.println(responseBody);
                        System.out.println(a);
                        System.out.println(counter);
                    }

//
//                    if (responseBody.contains("<img src=")) {
//                        String a = responseBody.substring(10);
//                        String b = a.substring(0, a.indexOf("\""));
//                        images.add(b);
//                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return images;
    }
}