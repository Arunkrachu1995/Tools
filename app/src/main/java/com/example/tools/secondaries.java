package com.example.tools;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.tools.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class secondaries extends AppCompatActivity {
    TextView out3,out4,out5,out6,out7,out8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondaries);



        out3 =  findViewById((R.id.out3));
        out4 =  findViewById((R.id.out4));
        out5 =  findViewById((R.id.out5));
        out6 =  findViewById((R.id.out6));
        out7 =  findViewById((R.id.out7));
        out8 =  findViewById((R.id.out8));
        new MyTask().execute();
    }

private class MyTask extends AsyncTask<Void,Void,Void> {

        int o3,o7;
        String o4,o5,o6,o8;


        @Override
        protected Void doInBackground(Void... params) {
            URL url=null;
            Intent myNewIntent=getIntent();
            int InfoReceivedId=myNewIntent.getIntExtra("ID" ,2 );
            try{
                url=new URL("http://172.26.30.3:8080/webservicecalls/webresources/toolrental/user1/userinfo&"+InfoReceivedId);
                HttpURLConnection client=null;
                client=(HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                int responseCode=client.getResponseCode();
                System.out.println("\n Sending 'GET' request to URL: "+url);
                System.out.println("Response code : responseCode");
                InputStreamReader myInput=new InputStreamReader(client.getInputStream());
                BufferedReader in=new BufferedReader(myInput);
                String inputLine;
                StringBuffer response=new StringBuffer();
                while((inputLine=in.readLine())!=null){
                    response.append(inputLine);
                }
                in.close();
                //print result
                System.out.println(response.toString());

                JSONObject obj=new JSONObject  (response.toString());


                o3=obj.getInt("u_id");
                o4=obj.getString("fname");
                o5=obj.getString("lname");
                o6=obj.getString("email");
                o7= obj.getInt("phone");
                o8=obj.getString("address");
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();


            }
            catch(IOException e)
            {
                e.printStackTrace();

            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            return null;

        }

    @Override
    protected void onPostExecute(Void aVoid) {
            out3.setText(o3);
            out4.setText(o4);
            out5.setText(o5);
            out6.setText(o6);
            out7.setText(o7);
            out8.setText(o8);
        super.onPostExecute(aVoid);
    }
}
}
