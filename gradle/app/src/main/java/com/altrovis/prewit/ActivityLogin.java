package com.altrovis.prewit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ActivityLogin extends AppCompatActivity {

    Button buttonLogin;
    ImageView imageViewLogo;
    RelativeLayout relativeLayoutForm;

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
                Intent intent = new Intent(ActivityLogin.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
