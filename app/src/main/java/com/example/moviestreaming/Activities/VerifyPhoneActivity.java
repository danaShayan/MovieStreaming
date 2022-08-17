package com.example.moviestreaming.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.chaos.view.PinView;
import com.example.moviestreaming.R;

public class VerifyPhoneActivity extends AppCompatActivity {


    AppCompatButton send, check, sendAgain;
    RelativeLayout verify_parent;
    LinearLayout check_code_layout;

    EditText phone;


    PinView pinView;


    static String Phone = "";
    String url = "https://api.kavenegar.com/v1/6A335174704E68456C665A424C50566E41705874647873516970333559723757644649492B4B386B342F673D/verify/lookup.json?receptor=09922376098&token=1234&template=movieStreamingApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);


        init();
        setListener();

    }


    private void init() {

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
                Toast.makeText(VerifyPhoneActivity.this, code+"", Toast.LENGTH_SHORT).show();
                if (code == 1111) {
                    pinView.setLineColor(R.color.green);
                } else {
                    pinView.setLineColor(R.color.red);
                }

            }
        });
    }
}