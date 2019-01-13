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
import com.google.firebase.iid.FirebaseInstanceId;


public class SignIn extends AppCompatActivity {

 private Button signIn, SignUp;
 private EditText Email,Password;
 private ProgressDialog progressDialog;
 private FirebaseAuth mAuth;
 private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_in);




        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In......");
        progressDialog.setTitle("Authentication");
        progressDialog.setCanceledOnTouchOutside(false);

                Email = findViewById(R.id.email_address);
                Password = findViewById(R.id.passwords);
                mAuth = FirebaseAuth.getInstance();



        signIn = findViewById(R.id.login);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = Email.getText().toString();
                String Pass = Password.getText().toString();
                String token = FirebaseInstanceId.getInstance().getToken();

if(!email.equals("") && !Pass.equals("")) {
    progressDialog.show();
    mAuth.signInWithEmailAndPassword(email, Pass).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {


            if (task.isSuccessful()) {
                //progressDialog.show();
                Toast.makeText(getApplicationContext(), "Signed In Successfully!", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
            else
            {
                progressDialog.show();
                Toast.makeText(getApplicationContext(), "Not Signed In, Please check your email and password or your Internet connection.", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        }
    });
}

            else
            {
                Toast.makeText(getApplicationContext(),"Please Fill the email and password!",Toast.LENGTH_SHORT).show();
            }
                        }
        });

        SignUp = findViewById(R.id.createaccount);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getApplicationContext(),SignUp.class);
                Bundle bundle =  new Bundle();
                i.putExtra("email_address",Email.getText().toString());
                i.putExtra("password",Password.getText().toString());
                startActivity(i);
            }
        });
    }

}
