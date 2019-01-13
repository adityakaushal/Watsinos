package com.example.vmac.WatBot;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashActivity extends Activity {
    private ImageView imageView;
    private FirebaseAuth mAuth;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
mAuth=FirebaseAuth.getInstance();
        imageView = findViewById(R.id.watsinos);
        Animation animation= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.rotate);
        Animation anim1 =AnimationUtils.loadAnimation(SplashActivity.this,R.anim.anti_rotate);

            imageView.startAnimation(animation);

            imageView.startAnimation(anim1);


       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               startActivity(new Intent(getApplicationContext(),SignIn.class));
               finish();
           }
       },2000);
   }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            Intent i = new Intent(getApplicationContext(),SignIn.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"User Not Signed In",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}

