package com.wangban.yzbbanban.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private EditText et;
    private ImageView iv;
    private Bitmap logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        et = (EditText) findViewById(R.id.et);
        iv = (ImageView) findViewById(R.id.iv);
        logo = BitmapFactory.decodeResource(getResources(), R.mipmap.my_logo);

    }

    public void scan(View view) {
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
    }

    public void make(View view) {
        String intput = et.getText().toString().trim();
        if ("".equals(intput)) {
            Toast.makeText(this, "输入不为空", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap b = EncodingUtils.createQRCode(intput, 500, 500, logo);
            iv.setImageBitmap(b);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String text = bundle.getString("result");
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            tv.setText(text);
        }
    }

}
