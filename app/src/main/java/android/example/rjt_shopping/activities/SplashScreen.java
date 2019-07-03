package android.example.rjt_shopping.activities;

import android.content.Intent;
import android.example.rjt_shopping.R;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME=3000;
    ImageView image;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        image=findViewById(R.id.splash_image);
        text=findViewById(R.id.splash_text);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.fadein);
        image.startAnimation(animation);
        text.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIME);

    }
}
