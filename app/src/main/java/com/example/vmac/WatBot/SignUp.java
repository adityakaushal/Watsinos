package com.example.vmac.WatBot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private EditText uFname, uLname;
    private Button createAccount;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);



        createAccount = findViewById(R.id.signUP);
        mEmail = findViewById(R.id.email_address);
        mPassword = findViewById(R.id.passwords);
        uFname = findViewById(R.id.Lastname);
        uLname = findViewById(R.id.firstname);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up......");

        Bundle extras= getIntent().getExtras();
        if(extras!=null)
        {
            String email= getIntent().getStringExtra("email_address");
            String pass = getIntent().getStringExtra("password");
            mEmail.setText(email);
            mPassword.setText(pass);

        }

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_address = mEmail.getText().toString();
                String _password = mPassword.getText().toString();
                String fname = uFname.getText().toString();
                String lname = uLname.getText().toString();

                if(!email_address.equals("") && !_password.equals(""))
                {
                            mAuth.createUserWithEmailAndPassword(email_address,_password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.show();

                if(!task.isSuccessful())
                    {

                        Toast.makeText(getApplicationContext(),"Account not created!",Toast.LENGTH_SHORT).show();
                    }

                else
                    {
                    progressDialog.show();
                    Toast.makeText(getApplicationContext(),"Account created Successfully!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this,MainActivity.class));
                    finish();
                }
                        }
                    });
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Please fill the email and password!",Toast.LENGTH_SHORT).show();
                }
            }
        });


                mAuthListener =new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null)
                {
                    //Toast.makeText(getApplicationContext(),"User Signed In",Toast.LENGTH_SHORT).show();
                       //User is Signed In...
                }
                else
                {
                    //Toast.makeText(getApplicationContext(),"User Signed Out",Toast.LENGTH_SHORT).show();
                    //User is not Signed In...
                }
            }
        };
    }



    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }



     }

