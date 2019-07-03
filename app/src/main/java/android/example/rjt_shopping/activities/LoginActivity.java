package android.example.rjt_shopping.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.example.rjt_shopping.fragments.LoginFragment;
import android.example.rjt_shopping.R;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences=getSharedPreferences("USER",MODE_PRIVATE);
        String apikey=sharedPreferences.getString("APIKEY",null);
        if(apikey!=null){
            startActivity(new Intent(this,MainActivity.class));
        }else{
            fragmentManager=getSupportFragmentManager();
            transaction=fragmentManager.beginTransaction();
            transaction.add(R.id.fragment_container,new LoginFragment()).commit();
        }
    }
}
