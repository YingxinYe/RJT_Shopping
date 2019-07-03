package android.example.rjt_shopping.fragments;


import android.example.rjt_shopping.adapters.ProductListAdapter;
import android.example.rjt_shopping.app.Endpoint;
import android.example.rjt_shopping.helpers.Session;
import android.example.rjt_shopping.model.CategoryList;
import android.example.rjt_shopping.model.Product;
import android.example.rjt_shopping.network.VolleySingleton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.rjt_shopping.R;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment implements ProductListAdapter.setGoToDetailPage {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProductListAdapter adapter;
    ArrayList<Product> mlist=new ArrayList<>();
    Bundle detail_bundle;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_list, container, false);
        recyclerView=view.findViewById(R.id.product_recycler_view);


        connect();
        return view;
    }

    private void connect() {
        Bundle bundle=getArguments();
        String url= Endpoint.GetProductList(bundle.getString("CID"),bundle.getString("SCID"), Session.API_KEY,Session.USER_ID);
        Log.i("MyTag","get product: "+url);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GsonBuilder builder=new GsonBuilder();
                Gson gson=builder.create();
                CategoryList categoryList=gson.fromJson(response.toString(),CategoryList.class);
                mlist=categoryList.getPlist();

                layoutManager=new GridLayoutManager(getContext(),2);
                adapter=new ProductListAdapter(getContext(),mlist);
                //adapter.setGoToDetailPage(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyTag","Get Product error: "+error.getMessage());
            }
        });
        VolleySingleton.getInstance().addRequestQue(request);
    }

    @Override
    public void goToDetailPage(View v) {
        Log.i("MyTag", "You get here");
        DetailProductFragment detailProductFragment=new DetailProductFragment();
        detail_bundle=new Bundle();
        detail_bundle.putSerializable("DETAIL_PRODUCT",mlist);
        detailProductFragment.setArguments(detail_bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.product_fragment_container,detailProductFragment).addToBackStack(null).commit();
    }
}
