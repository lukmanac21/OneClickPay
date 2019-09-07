package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PostProcessor;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    String TAG = "GenerateQrCode" ;
    String inputValue ;
    EditText edttxt,edttxt2,edtTotalString ;
    ImageView qrimg ;
    Button start ;
    Bitmap bitmap ;
    QRGEncoder qrgEncoder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrimg = (ImageView)findViewById(R.id.qrcode) ;
        edttxt = (EditText)findViewById(R.id.edttxt) ;
        edttxt2 = (EditText)findViewById(R.id.edttxt2) ;
        edtTotalString = (EditText)findViewById(R.id.edttxttotal) ;
        start = (Button)findViewById(R.id.btncreate) ;
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTotalString.setText(edttxt.getText().toString()+ " "+edttxt2.getText().toString());
                inputValue=edtTotalString.getText().toString().trim() ;
                if (inputValue.length() > 0){
                    WindowManager manager = (WindowManager)getSystemService(WINDOW_SERVICE) ;
                    Display display = manager.getDefaultDisplay() ;
                    Point point = new Point() ;
                    display.getSize(point) ;
                    int width = point.x ;
                    int height = point.y ;
                    int smallerdimension = width<height ? width:height;
                    smallerdimension = smallerdimension * 3 / 4 ;
                    qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT,smallerdimension) ;
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap() ;
                        qrimg.setImageBitmap(bitmap);
                    }
                    catch (WriterException e){
                        Log.v(TAG, e.toString()) ;
                    }
                }
                else {
                    edttxt.setError("Required ");
                }
            }
        });
    }
}
