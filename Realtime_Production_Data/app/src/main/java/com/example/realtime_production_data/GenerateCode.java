package com.example.realtime_production_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Build.VERSION_CODES.O;
import static java.lang.System.out;

public class GenerateCode extends AppCompatActivity {
public static final int QRCodeWidth=500;
Bitmap bitmap;
private EditText text;
private Button download;

private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);
        text=findViewById(R.id.ettextimg);
        download=findViewById(R.id.Download);


        iv=findViewById(R.id.image);
        Button generate;
        generate=findViewById(R.id.generate);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.getText().toString().trim().length()==0){
                    Toast.makeText(GenerateCode.this,"Enter Text",Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        bitmap= textToImageEncode(text.getText().toString());
                        iv.setImageBitmap(bitmap);
                        download.setVisibility(View.VISIBLE);
                        download.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                saveImageToInternalStorage(GenerateCode.this,bitmap);
                                MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"code_scanner",null);
                                Toast.makeText(GenerateCode.this,"Saved to Galary",Toast.LENGTH_SHORT).show();;
                            }
                        });

                    } catch (WriterException e){
                        e.printStackTrace();
                    }

                }
            }
        });

    }
    private Bitmap textToImageEncode(String value) throws WriterException{
        BitMatrix bitMatrix;
        try{
            bitMatrix=new MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE,QRCodeWidth,QRCodeWidth,null);
        } catch (IllegalArgumentException e){
            return null;
        }
        int bitMatrixWidth=bitMatrix.getWidth();
        int bitMatrixHeight=bitMatrix.getHeight();
        int[] pixels=new int[bitMatrixWidth*bitMatrixHeight];

        for (int y=0;y<bitMatrixHeight;y++){
            int offset =y*bitMatrixWidth;
            for (int x=0;x<bitMatrixWidth;x++){
                pixels[offset+x]=bitMatrix.get(x,y)?
                       getResources().getColor(R.color.black):getResources().getColor(R.color.white);

            }
        }
        Bitmap bitmap= Bitmap.createBitmap(bitMatrixWidth,bitMatrixHeight,Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels,0,500,0,0,bitMatrixWidth,bitMatrixHeight);
        return bitmap;


    }

    public static Uri saveImageToInternalStorage(Context mcontext, Bitmap bitmap){
        String mTimeStamp= new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        String mImageName="snap_"+mTimeStamp+".jpg";
        ContextWrapper wrapper=new ContextWrapper(mcontext);
        File file=wrapper.getDir("Images",MODE_PRIVATE);
        file=new File(file,"snap_"+mImageName+".jpg");
        try {
            OutputStream stream=null;
            stream=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri mImageUri=Uri.parse(file.getAbsolutePath());
        return mImageUri;
    }
}
