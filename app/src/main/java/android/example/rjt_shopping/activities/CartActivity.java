package android.example.rjt_shopping.activities;

import android.content.Intent;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.database.DBHelper;
import android.example.rjt_shopping.fragments.CartFragment;
import android.example.rjt_shopping.fragments.EmptyCartFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class CartActivity extends AppCompatActivity {

    Toolbar toolbar;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.cart_toolbar);
        toolbar.setTitle("Shopping Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(this);
        if (dbHelper.readAlldata().size() == 0) {
            getSupportFragmentManager().beginTransaction().add(R.id.cart_fragment_container, new EmptyCartFragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.cart_fragment_container, new CartFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
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
}
