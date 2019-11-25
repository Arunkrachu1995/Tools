package com.example.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tools.myapplication.R;


public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText in1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.btn);

        in1=(EditText) findViewById(R.id.editText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(v);
            }
        });

    }
    public void nextPage(View myView){

        Intent myIntent=new Intent(this, secondaries.class);
        myIntent.putExtra("ID",Integer.parseInt(in1.getText().toString()));
        startActivity(myIntent);


    }
}
