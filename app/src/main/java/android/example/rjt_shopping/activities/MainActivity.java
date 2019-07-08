package android.example.rjt_shopping.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.example.rjt_shopping.R;
import android.example.rjt_shopping.adapters.CategoryAdapter;
import android.example.rjt_shopping.adapters.ViewPagerAdapter;
import android.example.rjt_shopping.app.Endpoint;
import android.example.rjt_shopping.fragments.CartFragment;
import android.example.rjt_shopping.helpers.Session;
import android.example.rjt_shopping.model.Category;
import android.example.rjt_shopping.model.CategoryList;
import android.example.rjt_shopping.network.VolleySingleton;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CategoryAdapter adapter;
    ViewPagerAdapter viewPagerAdapter;
    public static ArrayList<Category> mlist;
    SharedPreferences sharedPreferences;
    ArrayList<String> imageurl;
    Timer timer;
    int current_position = 0;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabDots);

        mlist = new ArrayList<>();
        imageurl = new ArrayList<>();

        connect();

        recyclerView = findViewById(R.id.category_recycler_view);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        adapter = new CategoryAdapter(MainActivity.this, mlist);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



    }

    private void setToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    private void connect() {
        Session session=new Session(this);
        session.getUserIdandKey();

        String url = Endpoint.GetCategory(session.API_KEY,session.USER_ID);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("MyTag", response.toString());
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
                CategoryList categoryList=gson.fromJson(response.toString(),CategoryList.class);

                mlist=categoryList.getMlist();
                adapter.setData(mlist);

                for(int i=0;i<mlist.size();i++){
                    imageurl.add(mlist.get(i).getCimagerl());
                }

//                recyclerView = findViewById(R.id.category_recycler_view);
//                layoutManager = new LinearLayoutManager(MainActivity.this);
//                adapter = new CategoryAdapter(MainActivity.this, mlist);
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setAdapter(adapter);

                viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
                for (int i = 0; i < mlist.size(); i++) {
                    viewPagerAdapter.addFragment(mlist.get(i).getCimagerl());
                }
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);

                createSlide();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyTag", "error" + error.getMessage());
                Toast.makeText(MainActivity.this, "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequestQue(request);
    }

    private void createSlide() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (current_position == mlist.size()) {
                    current_position = 0;
                }
                viewPager.setCurrentItem(current_position++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 250, 2500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_order_history:
                startActivity(new Intent(this,OrderHistoryActivity.class));
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_cart:
                startActivity(new Intent(this, CartActivity.class));
                break;
            case R.id.menu_profile:
                startActivity(new Intent(this, ProfileAcitivity.class));
                break;
        }
        return true;
    }
}
