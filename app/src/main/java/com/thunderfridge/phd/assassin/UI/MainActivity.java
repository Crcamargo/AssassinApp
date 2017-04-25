package com.thunderfridge.phd.assassin.UI;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thunderfridge.phd.assassin.R;

public class MainActivity extends AppCompatActivity {


    public Button playBut, howBut, moreBut;
    public TextView title;

    public void init() {
        playBut = (Button) findViewById(R.id.buttonPlay);
        howBut = (Button) findViewById(R.id.buttonHow);
        moreBut = (Button) findViewById(R.id.buttonMore);
        title = (TextView) findViewById(R.id.textView3);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "font/blackcherry.ttf");
        title.setTypeface(customFont);
        playBut.setTypeface(customFont);
        howBut.setTypeface(customFont);
        moreBut.setTypeface(customFont);

        playBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, playActivity.class);
                startActivity(myIntent);
            }
        });

        moreBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, more.class);
                startActivity(myIntent);
            }
        });

        howBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, howTo.class);
                startActivity(myIntent);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
