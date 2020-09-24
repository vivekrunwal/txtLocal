package com.example.android.txtlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText _txtapi,_txtmess,_txtsender,_txtnum;
    Button _btnsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        _txtapi = (EditText)findViewById(R.id.txtapi);

//        _txtsender = (EditText)findViewById(R.id.txtsender);

        _txtmess = (EditText)findViewById(R.id.txtmess);
        _txtnum = (EditText)findViewById(R.id.txtphone);
        _btnsend = (Button)findViewById(R.id.btnsend);

        _btnsend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
//hVGH5ZZh+08-RTPiciB20lKpzAKbh1mNaAtxrqeLnQ  H1ROsVZWH4g-LL3AtGlLP4vHEVXZTsIJNGRmqCNpHR
                    // Construct data
                    String apiKey = "apikey=" +"hVGH5ZZh+08-RTPiciB20lKpzAKbh1mNaAtxrqeLnQ";
                    String message = "&message=" + _txtmess.getText().toString();
                    String sender = "&sender=" + "TXTLCL" ;
                    String numbers = "&numbers=" + _txtnum.getText().toString();
                    // Send data

                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers +sender+message;

                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");

                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        //stringBuffer.append(line);
                        Toast.makeText(getApplicationContext(), "Message send "+line, Toast.LENGTH_LONG).show();
                        Log.v("Error",line);
                    }

                    rd.close();

                  //  return stringBuffer.toString();
                } catch (Exception e) {
                    //System.out.println("Error SMS "+e);
                   // return "Error "+e;
                    Toast.makeText(getApplicationContext(), "The error message is "+e, Toast.LENGTH_LONG).show();
            Log.v("Error",e.toString());
                }
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }
}