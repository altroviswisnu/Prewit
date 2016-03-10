package com.altrovis.prewit;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.altrovis.prewit.Bussines.Login.LoginAsyncTask;

public class ActivityLogin extends AppCompatActivity {

    Button buttonLogin;
    ImageView imageViewLogo;
    RelativeLayout relativeLayoutForm;
    EditText editTextUsername;
    EditText editTextPassword;
    ActivityLogin login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        inisialisasiLayout();
        goToPageHome();

        setAnimation();
    }

    public void inisialisasiLayout()
    {
        buttonLogin = (Button)findViewById(R.id.ButtonLogin);
        imageViewLogo = (ImageView)findViewById(R.id.ImageViewLogo);
        relativeLayoutForm = (RelativeLayout)findViewById(R.id.RelativeLayoutForm);
        editTextUsername = (EditText) findViewById(R.id.EditTextUsername);
        editTextPassword = (EditText) findViewById(R.id.EditTextPassword);
        login = this;
    }

    public void setAnimation()
    {
        Animation animation = AnimationUtils.loadAnimation(ActivityLogin.this, R.anim.fadein);
        imageViewLogo.startAnimation(animation);

        Animation animation2 = AnimationUtils.loadAnimation(ActivityLogin.this, R.anim.fadeinslow);
        relativeLayoutForm.startAnimation(animation2);
    }

    public void goToPageHome()
    {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                new LoginAsyncTask(login, username, password).execute();
            }
        });
    }
}
