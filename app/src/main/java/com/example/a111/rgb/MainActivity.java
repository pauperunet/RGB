package com.example.a111.rgb;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private TextView textInform;
    private SeekBar seekBar;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    private Button button;
    private Button buttonGoToNext;

    private int red;
    private int green;
    private int blue;

    private FirebaseDatabase baseDate;
    private DatabaseReference baseDate_rgb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInform = (TextView) findViewById(R.id.textInform);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(255);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar2.setMax(255);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar3.setMax(255);
        button = (Button) findViewById(R.id.button);
        buttonGoToNext = (Button) findViewById(R.id.buttonGoToNext);

        seekBar.setBackgroundColor(Color.RED);
        seekBar2.setBackgroundColor(Color.GREEN);
        seekBar3.setBackgroundColor(Color.BLUE);

        red = 0;
        green = 0;
        blue = 0;


        baseDate = FirebaseDatabase.getInstance();
        baseDate_rgb = baseDate.getReference("colorBase");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                update();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Complaint colorBase = new Complaint();
                colorBase.setRedColor(String.valueOf(red));
                colorBase.setGreenColor(String.valueOf(green));
                colorBase.setBlueColor(String.valueOf(blue));
                save(colorBase);
            }
        });

        buttonGoToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    private void save(Complaint colorBase) {
        baseDate_rgb.push().setValue(colorBase);
    }

    private void update() {
        textInform.setBackgroundColor(Color.rgb(red, green, blue));

        textInform.setText(String.valueOf(red));
    }
}
