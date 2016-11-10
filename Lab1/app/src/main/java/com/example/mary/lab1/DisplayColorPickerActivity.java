package com.example.mary.lab1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.mary.lab1.R.id.surfaceView;

public class DisplayColorPickerActivity extends AppCompatActivity {
    TextView textViewRed;
    TextView textViewGreen;
    TextView textViewBlue;
    SeekBar seekBarRed;
    SeekBar seekBarGreen;
    SeekBar seekBarBlue;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_color_picker);

        textViewRed = (TextView)findViewById(R.id.textViewRed);
        textViewGreen = (TextView)findViewById(R.id.textViewGreen);
        textViewBlue = (TextView)findViewById(R.id.textViewBlue);

        seekBarRed = (SeekBar)findViewById(R.id.seekBarRed);
        seekBarGreen = (SeekBar)findViewById(R.id.seekBarGreen);
        seekBarBlue = (SeekBar)findViewById(R.id.seekBarBlue);

        setSeekbarsListener(seekBarRed, textViewRed);
        setSeekbarsListener(seekBarGreen, textViewGreen);
        setSeekbarsListener(seekBarBlue, textViewBlue);

        int c = Color.rgb(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
        v = (View)findViewById(surfaceView);
        v.setBackgroundColor(c);
    }

    public int getIntFromColor(int Red, int Green, int Blue){
        Red = (Red << 16) & 0x00FF0000;
        Green = (Green << 8) & 0x0000FF00;
        Blue = Blue & 0x000000FF;

        return 0xFF000000 | Red | Green | Blue;
    }

    public void setSeekbarsListener(SeekBar seekBar, final TextView seekBarValue) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(String.valueOf(progress));

                int color = getIntFromColor(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
                int c = Color.rgb(seekBarRed.getProgress(), seekBarGreen.getProgress(), seekBarBlue.getProgress());
                v.setBackgroundColor(c);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}
