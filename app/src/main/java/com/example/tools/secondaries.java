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
    TextView out1,out2,out3,out4,out5,out6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondaries);



        out1 =  findViewById((R.id.out1));
        out2 =  findViewById((R.id.out2));
        out3 =  findViewById((R.id.out3));
        out4 =  findViewById((R.id.out4));
        out5 =  findViewById((R.id.out5));
        out6 =  findViewById((R.id.out6));
        new MyTask().execute();
    }

private class MyTask extends AsyncTask<Void,Void,Void> {

        int o5;
        String o1,o2,o3,o4,o6;


        @Override
        protected Void doInBackground(Void... params) {
            URL url=null;
            Intent myNewIntent=getIntent();
            String InfoReceivedId=myNewIntent.getStringExtra("ID"  );
            try{
                url=new URL("http://172.26.30.3:8080/toolsrental/webresources/toolrental/user1/userinfo&"+InfoReceivedId);
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


                o1=obj.getString("u_id");
                o2=obj.getString("fname");
                o3=obj.getString("lname");
                o4=obj.getString("email");
               // o5= obj.getInt("phone");
                o6=obj.getString("address");
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
            out1.setText(o1);
            out2.setText(o2);
            out3.setText(o3);
            out4.setText(o4);
            out5.setText(o5);
            out6.setText(o6);
        super.onPostExecute(aVoid);
    }
}
}
