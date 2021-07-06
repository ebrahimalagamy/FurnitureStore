package com.example.furniturestore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    TextView sign_up_field,forget_password;
    EditText email_field,password_field;
    Button btn_login;
    String email,password;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initButton();

        if (auth.getCurrentUser() != null)
        {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
           // finish();
        }
    }

    private void initButton()
    {
        sign_up_field.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,Sign_Up_Activity.class)));
        btn_login.setOnClickListener(view -> {
            email=email_field.getText().toString();
            password=password_field.getText().toString();

            if (TextUtils.isEmpty(email))
            {
                Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_SHORT).show();
                email_field.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password))
            {
                Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_SHORT).show();
                password_field.requestFocus();
                return;
            }

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Wait ...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();

            auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(task ->
                    {
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            finish();
                        }else
                        {
                            Toast.makeText(MainActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }


                    });
        });
    }

    private void initView()
    {
        sign_up_field=findViewById(R.id.sign_up_field);
        email_field=findViewById(R.id.email_field);
        password_field=findViewById(R.id.password_field);
        forget_password=findViewById(R.id.forget_password);
        btn_login=findViewById(R.id.btn_login);
        auth=FirebaseAuth.getInstance();
    }
}
