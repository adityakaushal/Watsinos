package com.example.vmac.WatBot;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by adityakaushal on 03/10/17.
 */

public class DownloadUrl {
    public  String readUrl(String myUrl) throws IOException {
        String data = "";
        InputStream ip= null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(myUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            ip = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(ip));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            data = sb.toString();
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            ip.close();
            urlConnection.disconnect();
        }
        Log.d("DownloadURL","Returning data= "+data);
return  data;
    }

}
