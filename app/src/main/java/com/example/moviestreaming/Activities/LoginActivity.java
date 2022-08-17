package com.example.moviestreaming.Activities;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    TextView registerTxt;
    EditText email, phone, password;
    AppCompatButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();
        setOnClick();

    }

    private void init() {
        requestQueue = Volley.newRequestQueue(this);
        registerTxt = findViewById(R.id.registerTxt);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
    }


    private void setOnClick() {
        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = email.getText().toString();
                String phoneTxt = phone.getText().toString();
                String passwordTxt = password.getText().toString();
                if (emailTxt.isEmpty() || phoneTxt.isEmpty() || passwordTxt.isEmpty())
                    Toast.makeText(LoginActivity.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                else
                    loginAccount(emailTxt, phoneTxt, passwordTxt);
            }
        });
    }

    private void loginAccount(String emailTxt, String phoneTxt, String passwordTxt) {
        String url = "http://moviestreamingapi.mywebcommunity.org/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("login Successful"))
                    Toast.makeText(LoginActivity.this, "You Are Logged In", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("email", emailTxt);
                params.put("phone", phoneTxt);
                params.put("password", passwordTxt);
                return params;
            }
        };
        requestQueue.add(request);
    }
}