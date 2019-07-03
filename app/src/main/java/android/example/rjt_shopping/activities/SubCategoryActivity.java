package android.example.rjt_shopping.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.adapters.CategoryAdapter;
import android.example.rjt_shopping.adapters.SubCategoryAdapter;
import android.example.rjt_shopping.app.Endpoint;
import android.example.rjt_shopping.helpers.Session;
import android.example.rjt_shopping.model.Category;
import android.example.rjt_shopping.model.CategoryList;
import android.example.rjt_shopping.model.SubCategory;
import android.example.rjt_shopping.network.VolleySingleton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;


public class SubCategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    String cat_id;
    ArrayList<SubCategory> mlist=new ArrayList<>();
    SubCategoryAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        toolbar=findViewById(R.id.sub_cat_tool_bar);
        recyclerView=findViewById(R.id.sub_cat_recycler_view);
        String sub_bar_title=getIntent().getExtras().getString("CAT_NAME");
        cat_id=getIntent().getExtras().getString("CAT_ID");

        toolbar.setTitle(sub_bar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        connect();
    }

    private void connect() {

        String url= Endpoint.GetSubCategory(cat_id,Session.API_KEY,Session.USER_ID);
        Log.i("MyTag",url);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                CategoryList categorylist=gson.fromJson(response.toString(),CategoryList.class);
                mlist=categorylist.getSubmlist();

               // Log.i("MyTag","sub cat: "+mlist.get(0).getScid()+mlist.get(0).getScname());
                adapter=new SubCategoryAdapter(SubCategoryActivity.this,mlist,cat_id);
                layoutManager=new LinearLayoutManager(SubCategoryActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyTag","error here:"+error.getMessage());
            }
        });
        VolleySingleton.getInstance().addRequestQue(request);
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
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_cart:
                break;
            case R.id.menu_profile:
                startActivity(new Intent(this, ProfileAcitivity.class));
                break;
        }
        return true;
    }
}
