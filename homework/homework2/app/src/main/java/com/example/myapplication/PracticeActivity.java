package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticeActivity extends AppCompatActivity {

    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        Button but1=findViewById(R.id.btpra1);
        final TextView tv1=findViewById(R.id.tv1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count<=50)
                tv1.setText("表扬次数 "+count+"/50 "+"次");
                else
                {
                    Toast.makeText( PracticeActivity.this, "表扬次数已经最高啦！",Toast.LENGTH_SHORT).show();
                    tv1.setText("表扬次数 "+"50/50 " +"次");
                }
            }
        });
    }
}