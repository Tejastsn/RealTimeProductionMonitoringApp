package com.example.realtime_production_data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
private EditText Email;
private EditText Password;
private Button Loginbtn;
private FirebaseAuth mAuth;
private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        Email=(EditText)findViewById(R.id.etEmailAddress);
        Password=(EditText)findViewById(R.id.etPassword);
        Loginbtn=(Button)findViewById(R.id.btnLogin);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                  startActivity(new Intent(MainActivity.this,Dashboard.class));

                }
            }
        };

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    private void startSignIn(){
        String email=Email.getText().toString();
        String password=Password.getText().toString();
        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "Feilds are empty", Toast.LENGTH_LONG).show();
        } else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Invalid Credential Contact Company Adminstrator", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }
}