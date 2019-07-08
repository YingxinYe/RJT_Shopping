package android.example.rjt_shopping.activities;

import android.content.Intent;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.fragments.PayAddressFragment;
import android.example.rjt_shopping.fragments.PayMoneyFragment;
import android.example.rjt_shopping.fragments.PayReviewFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;



public class PaymentProcessActivity extends AppCompatActivity implements PayAddressFragment.AddressOnClickListner, PayMoneyFragment.BillingOnClickListner {

    Toolbar toolbar;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_process);

        toolbar=findViewById(R.id.pay_toolbar);
        toolbar.setTitle("Process Payment");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportFragmentManager().beginTransaction().add(R.id.pay_fragment_container,new PayAddressFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_order_history:
                startActivity(new Intent(this,OrderHistoryActivity.class));
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_cart:
                Intent i=new Intent(this, CartActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                break;
            case R.id.menu_profile:
                startActivity(new Intent(this, ProfileAcitivity.class));
                break;
        }
        return true;
    }


    @Override
    public void setOnClickListener(String name, String address, String phone) {
        bundle=new Bundle();
        bundle.putString("SHIP_NAME",name);
        bundle.putString("SHIP_ADDRESS",address);
        bundle.putString("SHIP_PHONE",phone);

    }

    @Override
    public void setBillingOnClickListener(String address) {
        bundle.putString("BILLING_ADDRESS",address);

        PayReviewFragment payReviewFragment=new PayReviewFragment();
        payReviewFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.pay_fragment_container,payReviewFragment).addToBackStack(null).commit();

    }

}
