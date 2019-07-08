package android.example.rjt_shopping.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.example.rjt_shopping.R;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileAcitivity extends AppCompatActivity {

    ImageView imageView;
    Button btn_log_out,btn_check_order;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView name;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acitivity);

        init();

        sharedPreferences=getSharedPreferences("USER",MODE_PRIVATE);

        name.setText("Hi, "+sharedPreferences.getString("FIRST_NAME",null)+" "+sharedPreferences.getString("LAST_NAME",null));

        toolbar=findViewById(R.id.profile_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor=sharedPreferences.edit();
                editor.putString("APIKEY",null);
                editor.putString("FIRST_NAME",null);
                editor.putString("LAST_NAME",null);
                editor.putString("EMAIL",null);
                editor.putString("MOBILE",null);
                editor.putString("USER_ID",null);
                editor.commit();
                startActivity(new Intent(ProfileAcitivity.this,LoginActivity.class));
            }
        });
    }

    private void init() {
        name=findViewById(R.id.profile_name);
        btn_log_out=findViewById(R.id.profile_button);
        btn_check_order=findViewById(R.id.profile_check_order);
        imageView=findViewById(R.id.profile_icon);
        Glide.with(this).load("https://1.share.photo.xuite.net/a734010628/1131b5d/4849038/185004239_x.jpg").circleCrop().into(imageView);
        btn_check_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileAcitivity.this,OrderHistoryActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_order_history:
                startActivity(new Intent(this,OrderHistoryActivity.class));
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_cart:
                break;
            case R.id.menu_profile:
                startActivity(new Intent(this,ProfileAcitivity.class));
                break;
        }
        return true;
    }
}
