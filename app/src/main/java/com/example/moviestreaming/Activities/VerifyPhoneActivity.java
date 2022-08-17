package com.example.moviestreaming.Activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.view.PinView;
import com.example.moviestreaming.R;

import org.json.JSONObject;

public class VerifyPhoneActivity extends AppCompatActivity {


    AppCompatButton send, check, sendAgain;
    RelativeLayout verify_parent;
    LinearLayout check_code_layout;
    EditText phone;
    PinView pinView;


    static String Phone = "";
    String url = "https://api.kavenegar.com/v1/6A335174704E68456C665A424C50566E41705874647873516970333559723757644649492B4B386B342F673D/verify/lookup.json?";
    private int random_verify_code;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);


        init();
        setListener();

    }


    private void init() {
        requestQueue = Volley.newRequestQueue(this);

        send = findViewById(R.id.send_code);
        check = findViewById(R.id.check_code);
        sendAgain = findViewById(R.id.send_again);
        phone = findViewById(R.id.phone);
        pinView = findViewById(R.id.firstPinView);

        verify_parent = findViewById(R.id.verify_parent);
        check_code_layout = findViewById(R.id.check_code_layout);

    }

    private void setListener() {

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString().length() != 11)
                    Toast.makeText(VerifyPhoneActivity.this, "The phone number should be 11 digit", Toast.LENGTH_SHORT).show();
                else {
                    Phone = phone.getText().toString().trim();
                    verify_parent.setVisibility(View.GONE);
                    check_code_layout.setVisibility(View.VISIBLE);
                }
                sendCodeVerify();
            }
        });
        sendAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify_parent.setVisibility(View.VISIBLE);
                check_code_layout.setVisibility(View.GONE);
            }
        });


        check.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                int code = Integer.parseInt(pinView.getText().toString());
                if (code == 1111) {
                    pinView.setLineColor(Color.GREEN);
                } else {
                    pinView.setLineColor(Color.RED);
                }


            }
        });
    }

    private void sendCodeVerify() {
        random_verify_code = (int) (Math.random() * 10000);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + "receptor=" + Phone + "8&token=" + random_verify_code + "&template=movieStreamingApp", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(request);
    }
}