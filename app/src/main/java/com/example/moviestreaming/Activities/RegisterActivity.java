package com.example.moviestreaming.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviestreaming.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    TextView loginTxt;

    EditText username, email, phone, password;
    AppCompatButton register;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        init();
        setOnClick();

    }

    private void init() {
        requestQueue = Volley.newRequestQueue(this);

        loginTxt = findViewById(R.id.loginTxt);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
    }

    private void setOnClick() {

        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTxt = username.getText().toString();
                String emailTxt = email.getText().toString();
                String passwordTxt = password.getText().toString();
                String phoneTxt = phone.getText().toString();

                if (usernameTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty() || phoneTxt.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                } else {
                    registerAccount(usernameTxt, emailTxt, phoneTxt, passwordTxt);
                }

            }
        });
    }

    private void registerAccount(String usernameTxt, String emailTxt, String phoneTxt, String passwordTxt) {
        String url = "http://moviestreamingapi.mywebcommunity.org/register.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Register Successful"))
                    Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.e("REsponse" , response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> params = new HashMap<>();
                params.put("username", usernameTxt);
                params.put("email", emailTxt);
                params.put("phone", phoneTxt);
                params.put("password", passwordTxt);

                return params;
            }
        };

        requestQueue.add(request);
    }
}