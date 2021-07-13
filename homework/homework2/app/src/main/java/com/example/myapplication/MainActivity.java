package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="Activity_log";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"-->onCreate");
        setContentView(R.layout.activity_main);
        Button but1=findViewById(R.id.btn1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this, "button click",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
        Button but2=findViewById(R.id.btn2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this, "button click",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, PracticeActivity.class);
                startActivity(intent);
            }
        });
        Button but3=findViewById(R.id.btn3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this, "button click",Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });
        Button but4=findViewById(R.id.btn4);
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this, "button click",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(MainActivity.this, RankActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void onStart() {

        super.onStart();
        Log.i(TAG,"-->onStart");
    }
    protected void onResume() {

        super.onResume();
        Log.i(TAG,"-->onResume");
    }
    protected void onPause() {

        super.onPause();
        Log.i(TAG,"-->onPause");
    }
    protected void onStop() {

        super.onStop();
        Log.i(TAG,"-->onStop");
    }
    protected void onDestroy() {

        super.onDestroy();
        Log.i(TAG,"-->onDestroy");
    }
}