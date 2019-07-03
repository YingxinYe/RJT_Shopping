package android.example.rjt_shopping.activities;

import android.content.Intent;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.fragments.ProductListFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class ProductListActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    String cid;
    String scid;
    String cat_name;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        cid=getIntent().getExtras().getString("CAT_ID");
        scid=getIntent().getExtras().getString("SUB_CAT_ID");
        cat_name=getIntent().getExtras().getString("SUB_CAT_NAME");

        fragmentManager=getSupportFragmentManager();
        ProductListFragment productListFragment=new ProductListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("CID",cid);
        bundle.putString("SCID",scid);
        productListFragment.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.product_fragment_container,productListFragment).commit();

        setToolBar(cat_name);

    }

    private void setToolBar(String name) {
        toolbar=findViewById(R.id.product_tool_bar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_profile:
                startActivity(new Intent(ProductListActivity.this,ProfileAcitivity.class));
                break;
            case R.id.menu_cart:
                break;
        }return true;
    }
}
