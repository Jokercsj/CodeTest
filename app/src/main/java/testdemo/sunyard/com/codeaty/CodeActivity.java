package testdemo.sunyard.com.codeaty;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import testdemo.sunyard.com.ImageSurfaceView;
import testdemo.sunyard.com.R;
import testdemo.sunyard.com.qrcod.activity.CaptureActivity;
import testdemo.sunyard.com.util.CommomDialogUtils;
import testdemo.sunyard.com.util.QRCodeUtil;

public class CodeActivity extends Activity implements View.OnClickListener {
    ImageView imageView;
    private final int REQUEST_QRCODE = 10;
    int Degree;
    Button btnLeftRight;
    String s;
    boolean isScaleY = false;
    Button btnCreateCode, btnCreateColorCode, btnTop, btnIntent, btnIntroduce;
    ImageSurfaceView imageSurfaceView;
    Bitmap bitmap;
    TextView textView, tvCodeContent;
    EditText editText;
    String choseCode;
    private static final String TAG = "CodeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initData();

        Intent intent = getIntent();
        choseCode = intent.getStringExtra("choseCode");
        textView.setText(choseCode);
        if (choseCode.equals("QR_CODE")) {
            btnCreateColorCode.setVisibility(View.VISIBLE);
        } else {
            btnCreateColorCode.setVisibility(View.GONE);
        }
        switch (choseCode) {
            case "QR_CODE":
                bitmap = QRCodeUtil.createQRImage("hello world", BarcodeFormat.QR_CODE, 500, 300, null, null);
                tvCodeContent.setText("hello world");
                imageView.setImageBitmap(bitmap);
                break;
            case "CODE_128":
                bitmap = QRCodeUtil.createMultiFormatImage("hello world", BarcodeFormat.CODE_128, 500, 300, null, null);
                tvCodeContent.setText("hello world");
                imageView.setImageBitmap(bitmap);
                break;
            case "EAN_13":
                bitmap = QRCodeUtil.createEAN13WriterImage("6901234567892", BarcodeFormat.EAN_13, 500, 300, null, null);
                tvCodeContent.setText("6901234567892");
                imageView.setImageBitmap(bitmap);
                break;
            case "EAN_8":

                bitmap = QRCodeUtil.createEAN8WriterImage("47112346", BarcodeFormat.EAN_8, 500, 300, null, null);
                tvCodeContent.setText("47112346");
                imageView.setImageBitmap(bitmap);
                break;

            case "CODABAR":

                bitmap = QRCodeUtil.createMultiFormatImage("1234456", BarcodeFormat.CODABAR, 500, 300, null, null);
                tvCodeContent.setText("1234456");
                imageView.setImageBitmap(bitmap);
                break;
            case "UPC_E":
                bitmap = QRCodeUtil.createUPC_EImage("1234560", BarcodeFormat.UPC_E, 500, 300, null, null);
                tvCodeContent.setText("1234560");
                imageView.setImageBitmap(bitmap);
                break;
            case "UPC_A":
                bitmap = QRCodeUtil.createUPC_AImage("12345678321", BarcodeFormat.UPC_A, 500, 300, null, null);
                tvCodeContent.setText("12345678321");
                imageView.setImageBitmap(bitmap);
                break;
            case "ITF":
                bitmap = QRCodeUtil.createITFImage("0053611912", BarcodeFormat.ITF, 500, 300, null, null);
                tvCodeContent.setText("0053611912");
                imageView.setImageBitmap(bitmap);
                break;

            case "CODE_39":
                bitmap = QRCodeUtil.createcode39Image("ABC123", BarcodeFormat.CODE_39, 500, 300, null, null);
                tvCodeContent.setText("ABC123");
                imageView.setImageBitmap(bitmap);
                break;
            case "CODE_93":
                bitmap = QRCodeUtil.createcode93Image("1234567890", BarcodeFormat.CODE_93, 500, 300, null, null);
                tvCodeContent.setText("1234567890");
                imageView.setImageBitmap(bitmap);
                break;

        }


    }

    private void initData() {

        tvCodeContent = findViewById(R.id.code_content);
        imageView = findViewById(R.id.img);
        textView = findViewById(R.id.text);
        imageSurfaceView = findViewById(R.id.imgsur);
        editText = findViewById(R.id.edit_text);
        btnCreateColorCode = findViewById(R.id.create_colorcode);
        btnCreateCode = findViewById(R.id.create_code);
        btnTop = findViewById(R.id.btn3);
        btnIntroduce = findViewById(R.id.btn_introduce);
        btnIntent = findViewById(R.id.btn_intent);
        btnLeftRight = findViewById(R.id.btn_left);
        btnIntroduce.setOnClickListener(this);
        btnIntent.setOnClickListener(this);
        btnCreateColorCode.setOnClickListener(this);
        btnTop.setOnClickListener(this);
        btnCreateCode.setOnClickListener(this);
        btnLeftRight.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_left:
                if (isScaleY) {
                    imageView.setScaleX(-1);
                    isScaleY = false;
                } else {
                    imageView.setScaleX(1);
                    isScaleY = true;
                }



                break;
            case R.id.btn3:
                if (isScaleY) {
                    imageView.setScaleY(-1);
                    isScaleY = false;
                } else {
                    imageView.setScaleY(1);
                    isScaleY = true;
                }
                break;
            case R.id.btn_intent:
                imageSurfaceView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                if (Degree > 360) {
                    Degree = 0;
                }
                Degree = Degree + 90;
                imageView.setRotation(Degree);
                break;

            case R.id.btn_introduce:
                showIntroduceMsg();
                break;
            case R.id.create_code:
                s = editText.getText().toString();
                Log.d(TAG, "s:" + s);
                if (s.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please input content", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    showCoad(choseCode, " ");
                    imageView.setImageBitmap(bitmap);
                }

                break;

            case R.id.create_colorcode:
                s = editText.getText().toString(); if (s.isEmpty()) {
                Toast.makeText(getApplicationContext(), "please input content", Toast.LENGTH_SHORT).show();
                return;
            } else {

                showCoad(choseCode, "color");
                imageView.setImageBitmap(bitmap);
            }
                break;

        }
    }

    private void showDialog(String s) {
        //弹出提示框
        CommomDialogUtils commomDialogUtils = new CommomDialogUtils(this, R.style.dialog, s, new CommomDialogUtils.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                dialog.dismiss();
            }
        });
        commomDialogUtils.setCancelable(false);
        commomDialogUtils.setTitle(choseCode + "条码介绍").show();
    }

    private void showIntroduceMsg() {
        String message = " ";
        switch (choseCode) {
            case "QR_CODE":
                message = this.getResources().getString(R.string.QR_CODE);
                //bitmap = QRCodeUtil.createQRImage("hello world", BarcodeFormat.QR_CODE, 500, 300, null, null);
                showDialog(message);
                break;
            case "CODE_128":
                message = this.getResources().getString(R.string.CODE_128);

                showDialog(message);
                //  bitmap = QRCodeUtil.createMultiFormatImage("hello world", BarcodeFormat.CODE_128, 500, 300, null, null);

                break;
            case "EAN_13":
                //  bitmap = QRCodeUtil.createEAN13WriterImage("6901234567892", BarcodeFormat.EAN_13, 500, 300, null, null);
                message = this.getResources().getString(R.string.EAN_13);

                showDialog(message);
                break;
            case "EAN_8":

                //  bitmap = QRCodeUtil.createEAN8WriterImage("47112346", BarcodeFormat.EAN_8, 500, 300, null, null);
                message = this.getResources().getString(R.string.EAN_8);

                showDialog(message);
                break;

            case "CODABAR":
                message = this.getResources().getString(R.string.CODABAR);

                showDialog(message);
                //  bitmap = QRCodeUtil.createMultiFormatImage("1234456", BarcodeFormat.CODABAR, 500, 300, null, null);

                break;
            case "UPC_E":
                // bitmap = QRCodeUtil.createUPC_EImage("1234560", BarcodeFormat.UPC_E, 500, 300, null, null);
                message = this.getResources().getString(R.string.UPC_E);

                showDialog(message);
                break;
            case "UPC_A":
                // bitmap = QRCodeUtil.createUPC_AImage("12345678321", BarcodeFormat.UPC_A, 500, 300, null, null);
                message = this.getResources().getString(R.string.UPC_A);

                showDialog(message);
                break;
            case "ITF":
                //  bitmap = QRCodeUtil.createITFImage("0053611912", BarcodeFormat.ITF, 500, 300, null, null);
                message = this.getResources().getString(R.string.ITF);

                showDialog(message);
                break;

            case "CODE_39":
                //   bitmap = QRCodeUtil.createcode39Image("ABC123", BarcodeFormat.CODE_39, 500, 300, null, null);
                message = this.getResources().getString(R.string.CODE_39);

                showDialog(message);
                break;
            case "CODE_93":
                // bitmap = QRCodeUtil.createcode93Image("1234567890", BarcodeFormat.CODE_93, 500, 300, null, null);
                message = this.getResources().getString(R.string.CODE_93);

                showDialog(message);
                break;

        }
    }

    private void showCoad(String choseCode, String codeType) {
        switch (choseCode) {
            case "QR_CODE":
                if (codeType.equals("color")) {
                    try {
                        bitmap = QRCodeUtil.makeQRImage(null, s, 500, 300);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                } else {
                    bitmap = QRCodeUtil.createQRImage(s, BarcodeFormat.QR_CODE, 500, 300, null, null);
                }
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;
            case "CODE_128":
                //  bitmap = QRCodeUtil.createMultiFormatImage("hello world", BarcodeFormat.CODE_128, 500, 300, null, null);
                bitmap = QRCodeUtil.createMultiFormatImage(s, BarcodeFormat.CODE_128, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;
            case "EAN_13":
                //  bitmap = QRCodeUtil.createEAN13WriterImage("6901234567892", BarcodeFormat.EAN_13, 500, 300, null, null);
                bitmap = QRCodeUtil.createEAN13WriterImage(s, BarcodeFormat.EAN_13, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;
            case "EAN_8":
                //  bitmap = QRCodeUtil.createEAN8WriterImage("47112346", BarcodeFormat.EAN_8, 500, 300, null, null);
                bitmap = QRCodeUtil.createEAN8WriterImage(s, BarcodeFormat.EAN_8, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;

            case "CODABAR":
                //  bitmap = QRCodeUtil.createMultiFormatImage("1234456", BarcodeFormat.CODABAR, 500, 300, null, null);
                bitmap = QRCodeUtil.createMultiFormatImage(s, BarcodeFormat.CODABAR, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;
            case "UPC_E":
                // bitmap = QRCodeUtil.createUPC_EImage("1234560", BarcodeFormat.UPC_E, 500, 300, null, null);
                bitmap = QRCodeUtil.createUPC_EImage(s, BarcodeFormat.UPC_E, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;
            case "UPC_A":
                // bitmap = QRCodeUtil.createUPC_AImage("12345678321", BarcodeFormat.UPC_A, 500, 300, null, null);
                bitmap = QRCodeUtil.createUPC_AImage(s, BarcodeFormat.UPC_A, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;
            case "ITF":
                //  bitmap = QRCodeUtil.createITFImage("0053611912", BarcodeFormat.ITF, 500, 300, null, null);
                bitmap = QRCodeUtil.createITFImage(s, BarcodeFormat.ITF, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;

            case "CODE_39":
                //   bitmap = QRCodeUtil.createcode39Image("ABC123", BarcodeFormat.CODE_39, 500, 300, null, null);
                bitmap = QRCodeUtil.createcode39Image(s, BarcodeFormat.CODE_39, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;
            case "CODE_93":
                // bitmap = QRCodeUtil.createcode93Image("1234567890", BarcodeFormat.CODE_93, 500, 300, null, null);
                bitmap = QRCodeUtil.createcode93Image(s, BarcodeFormat.CODE_93, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;
            case "DATA_MATRIX":
                //  bitmap = QRCodeUtil.createDATA_MATRIXImage("ABC123450", BarcodeFormat.DATA_MATRIX, 500, 300, null, null);
                bitmap = QRCodeUtil.createDATA_MATRIXImage(s, BarcodeFormat.DATA_MATRIX, 500, 300, null, null);
                imageView.setImageBitmap(bitmap);
                tvCodeContent.setText(s);
                break;
        }
    }

}