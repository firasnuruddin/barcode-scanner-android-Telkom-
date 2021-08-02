package com.example.telkomregionaliv;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scanBtn,cekBtn,inputBtn;
    private TextView formatTxt, contentTxt;
    FirebaseDatabase database ;
    DatabaseReference reference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    Toast.makeText(MainActivity.this,"firebase sucsess",Toast.LENGTH_LONG).show();
        scanBtn = (Button)findViewById(R.id.scan_button);

        inputBtn = (Button)findViewById(R.id.input_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);


        scanBtn.setOnClickListener(this);
        inputBtn.setOnClickListener(this);
    }

    public void onClick(View v){
        reference = FirebaseDatabase.getInstance().getReference().child("Barang");
        switch (v.getId()) {
            case R.id.scan_button:
            if (v.getId() == R.id.scan_button) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                scanIntegrator.initiateScan();
            }
                break;

            case R.id.input_button:
            if (v.getId()== R.id.input_button) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Barang");

                String format = formatTxt.getText().toString();
                String content = contentTxt.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(format, content);
                reference.child(content).setValue(helperClass);
            }
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}