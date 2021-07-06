package com.example.furniturestore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Sign_Up_Activity extends AppCompatActivity {
    EditText username_field, email_field, password_field, confirm_password_field;
    String username_text, email_text, password_text, confirm_password_text;
    ProgressDialog progressDialog;
    TextView sign_in;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up_);

        initViews();
        auth = FirebaseAuth.getInstance();
    }

    private void initViews() {
        username_field = findViewById(R.id.username_field);
        email_field = findViewById(R.id.email_field);
        password_field = findViewById(R.id.password_field);
        confirm_password_field = findViewById(R.id.confirm_passwprd_field);
        sign_in = findViewById(R.id.signin);
    }

    public void register(View view) {
        username_text = username_field.getText().toString();
        email_text = email_field.getText().toString().trim();
        password_text = password_field.getText().toString();
        confirm_password_text = confirm_password_field.getText().toString();

        if (TextUtils.isEmpty(username_text)) {
            Toast.makeText(getApplicationContext(), "Enter your name", Toast.LENGTH_SHORT).show();
            username_field.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email_text)) {
            Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_SHORT).show();
            email_field.requestFocus();
            return;
        }

        if (password_text.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password is too short", Toast.LENGTH_SHORT).show();
            password_field.requestFocus();
            return;
        }

        if (!confirm_password_text.equals(password_text)) {
            Toast.makeText(getApplicationContext(), "Password is not matching", Toast.LENGTH_SHORT).show();
            confirm_password_field.requestFocus();
            return;
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        auth.createUserWithEmailAndPassword(email_text, password_text)
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Sign_Up_Activity.this, "SignUp Unsuccessful", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(Sign_Up_Activity.this, HomeActivity.class));
                        progressDialog.dismiss();
                        finish();


                    }

                });
        sign_in.setOnClickListener(v -> startActivity(new Intent(Sign_Up_Activity.this, MainActivity.class)));

    }
}
