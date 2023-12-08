package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.kyanogen.signatureview.SignatureView;

import java.io.File;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    SignatureView signatureView;
    ImageButton imgEraser,imgColor;
    SeekBar seekBar;
    TextView txtpensize;
    int defaultcolor;

    private static String filename;
    File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ZAMETKII");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signatureView = findViewById(R.id.signature_view);
        imgColor= findViewById(R.id.btnColor);
        imgEraser=findViewById(R.id.btnEraser);
        seekBar=findViewById(R.id.penSize);
        txtpensize=findViewById(R.id.TxtPenSize);






        defaultcolor= ContextCompat.getColor(MainActivity.this,R.color.black);

        imgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int progress = 1;
                txtpensize.setText(i+"dp");
                signatureView.setPenSize(i);
                txtpensize.setText(String.valueOf(seekBar.getProgress()));

                seekBar.setMax(100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        imgEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { signatureView.clearCanvas();

            }
        });
    }
    private void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultcolor=color;
                signatureView.setPenColor(color);

            }
        });
        ambilWarnaDialog.show();
    }

}