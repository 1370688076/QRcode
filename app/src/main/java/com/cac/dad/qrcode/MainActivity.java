package com.cac.dad.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private EditText editText;
    private ImageView image;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.result);
        editText= (EditText) findViewById(R.id.ettext);
        image= (ImageView) findViewById(R.id.image);
        checkBox= (CheckBox) findViewById(R.id.check);
    }

    public void Scan(View view) {

        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String s = bundle.getString("result");
            text.setText(s);
        }
    }

    public void make(View v)
    {
        String s=editText.getText().toString();
        if(s.equals(""))
        {
            Toast.makeText(this,"您输入的内容为空",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Bitmap map= EncodingUtils.createQRCode(s,500,500,checkBox.isChecked()? BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher):null);
            image.setImageBitmap(map);
        }




    }
}
